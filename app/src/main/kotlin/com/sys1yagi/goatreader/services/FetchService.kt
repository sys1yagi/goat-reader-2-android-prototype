package com.sys1yagi.goatreader.services

import android.app.Service
import android.content.Intent
import com.sys1yagi.goatreader.tools.Logger
import java.util.concurrent.Executors
import com.activeandroid.query.Select
import com.sys1yagi.goatreader.models.Feed
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Date
import org.apache.http.HttpStatus
import java.text.SimpleDateFormat
import java.util.Locale
import org.jsoup.parser.Parser
import com.sys1yagi.goatreader.models.Item
import java.util.regex.Pattern
import com.sys1yagi.goatreader.models.LastRequestTime

public class FetchService() : Service() {
    class object {
        val TAG = javaClass<FetchService>().getSimpleName()!!
        val EXECUTOR = Executors.newFixedThreadPool(1)
        val FORMAT = SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss z", Locale.ENGLISH)
        val RSS_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val IMAGE_LINK_PATTERN = Pattern.compile("<img src=\"(http[^\"]+\\.jpg)\"")!!
        val REQUEST_INTERVAL = 1000L * 60L * 15L
    }

    override fun onBind(p0: android.content.Intent): android.os.IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Logger.d(TAG, "onStartCommand : " + startId)

        EXECUTOR?.execute(object : Runnable {
            override fun run() {
                if (LastRequestTime.getLastRequestTimeSpan() > REQUEST_INTERVAL) {
                    loadRss()
                    LastRequestTime.saveLastRequestTime()

                }
                stopSelfResult(startId)
                Logger.d(TAG, "onStartCommand end: " + startId)
            }
        })
        return Service.START_STICKY
    }

    fun loadRss() {
        val startTime = System.currentTimeMillis()
        val feed: Feed? = Select().from(javaClass<Feed>())?.orderBy(Feed.UPDATED_AT + " asc")?.executeSingle()
        if ( feed == null) {
            return
        }
        var lastTime = feed.updatedAt?.getTime()
        if (lastTime == null) {
            lastTime = 0L
        }

        val xml = load(feed?.url, lastTime!!)
        feed.updatedAt = Date()
        feed.save()
        if (xml == null) {
            return
        }
        parse(feed.getId(), xml)

        Logger.d(TAG, "${feed?.url} modified. time : ${System.currentTimeMillis() - startTime}")
    }

    public fun parse(feedId: Long?, xml: String?) {
        val document: Document? = Jsoup.parse(xml, "", Parser.xmlParser())
        val elements = document?.select("item")
        elements?.forEach {
            val item = Item()
            item.feedId = feedId
            item.isBad = false
            item.isFav = false
            item.isRead = false
            it.children()?.forEach {
                when (it.tagName()) {
                    "title" -> {
                        item.title = it.text()
                    }
                    "link" -> {
                        item.link = it.text()
                    }
                    "description" -> {
                        item.description = it.text()
                    }
                    "content:encoded" -> {
                        val m = IMAGE_LINK_PATTERN.matcher(it.text().toString())
                        if (m.find()) {
                            item.imageLink = m.group(1);
                        }
                    }
                    "dc:date" -> {
                        item.createdAt = RSS_DATE_FORMAT.parse(it.text())
                    }
                }
            }
            item.save()
        }
    }

    fun load(url: String?, lastTime: Long): String? {
        val conn: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        conn.addRequestProperty("If-Modified-Since", FORMAT.format(Date(lastTime)))
        conn.connect()

        if (conn.getResponseCode() == HttpStatus.SC_NOT_MODIFIED) {
            Logger.d(TAG, "${url} is not modified.")
            return null
        }
        val br = BufferedReader(InputStreamReader(conn.getInputStream()!!, "utf-8"))
        val sb = StringBuilder()
        while (br.ready()) {
            sb.append(br.readLine())
        }
        return sb.toString()
    }
}
