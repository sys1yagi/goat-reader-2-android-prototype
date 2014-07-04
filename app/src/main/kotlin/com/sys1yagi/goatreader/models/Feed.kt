package com.sys1yagi.goatreader.models

import com.activeandroid.Model
import com.activeandroid.annotation.Table
import com.activeandroid.annotation.Column
import java.util.Date

Table(name = Feed.TABLE_NAME)
public class Feed() : Model() {
    class object {
        val TABLE_NAME = "Feeds"
        val TITLE = "title"
        val URL = "url"
        val CATEGORY_ID = "category_id"
        val CREATED_AT = "created_at"
        val UPDATED_AT = "updated_at"

        fun create(title: String, url: String): Feed {
            val date = Date()
            val feed = Feed()
            feed.title = title
            feed.url = url
            feed.createdAt = date
            feed.updatedAt = date
            return feed
        }

    }
    Column(name = TITLE)
    var title: String? = null
    Column(name = URL)
    var url: String? = null
    Column(name = CATEGORY_ID)
    var categoryId: Long? = null
    Column(name = CREATED_AT)
    var createdAt: Date? = null
    Column(name = UPDATED_AT)
    var updatedAt: Date? = null

}
