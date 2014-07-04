package com.sys1yagi.goatreader.models

import kotlin.test.*
import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Select
import com.sys1yagi.goatreader.testtools.DatabaseTestCase

public class FeedTest() : DatabaseTestCase() {

    fun createFeedRecord(title: String, url: String): Long? {
        val feed = Feed.create(title, url)
        return feed.save()
    }

    public fun testCreateFeed() {
        val feed = Feed.create("title", "url")
        assertNotNull(feed)
        assertEquals("title", feed.title)
        assertEquals("url", feed.url)
    }

    public fun testSaveFeed() {
        val feed = Feed.create("title", "url")
        assertNotNull(feed)
        assertEquals("title", feed.title)
        assertEquals("url", feed.url)
        ActiveAndroid.beginTransaction()
        val id = feed.save()
        ActiveAndroid.setTransactionSuccessful()
        ActiveAndroid.endTransaction()

        assertEquals(1, Select().from(javaClass<Feed>())?.count())
        val record: Feed? = Select().from(javaClass<Feed>())?.where("id=" + id)?.executeSingle()
        assertNotNull(record)
        assertEquals("title", record?.title)
        assertEquals("url", record?.url)
    }

    public fun testDeleteFeed() {
        assertEquals(0, Select().from(javaClass<Feed>())?.count())
        val id = createFeedRecord("title1", "url1")
        val record: Feed? = Select().from(javaClass<Feed>())?.where("id=" + id)?.executeSingle()
        assertNotNull(record)
        assertEquals("title1", record?.title)
        assertEquals("url1", record?.url)

        record?.delete()

        val record2: Feed? = Select().from(javaClass<Feed>())?.where("id=" + id)?.executeSingle()
        assertNull(record2)
    }
}
