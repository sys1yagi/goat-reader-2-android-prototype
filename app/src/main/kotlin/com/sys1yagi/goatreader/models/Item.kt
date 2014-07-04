package com.sys1yagi.goatreader.models

import com.activeandroid.annotation.Table
import com.activeandroid.Model
import com.activeandroid.annotation.Column
import java.util.Date

Table(name = Item.TABLE_NAME)
public class Item() : Model() {
    class object {
        val TABLE_NAME = "Items"
        val FEED_ID = "feed_id"
        val TITLE = "title"
        val DESCRIPTION = "description"
        val LINK = "link"
        val IMAGE_LINK = "image_link"
        val CREATED_AT = "creted_at"
        val IS_READ = "is_read"
        val IS_FAV = "is_fav"
        val IS_BAD = "is_bad"
        val TAG_IDS = "tag_ids"

        fun create(feedId: Long, title: String, description: String, link: String, imageLink: String?): Item {
            val date = Date()
            val item: Item = Item()
            item.feedId = feedId
            item.title = title
            item.description = description
            item.link = link
            item.imageLink = imageLink
            item.createdAt = date
            item.isRead = false
            item.isFav = false
            item.isBad = false
            item.tagIds = null
            return item
        }
    }
    Column(name = FEED_ID)
    var feedId: Long? = null
    Column(name = TITLE)
    var title: String? = null
    Column(name = DESCRIPTION)
    var description: String? = null
    Column(name = LINK, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    var link: String? = null
    Column(name = IMAGE_LINK)
    var imageLink: String? = null
    Column(name = CREATED_AT)
    var createdAt: Date? = null
    Column(name = IS_READ)
    var isRead: Boolean? = null
    Column(name = IS_FAV)
    var isFav: Boolean? = null
    Column(name = IS_BAD)
    var isBad: Boolean? = null
    Column(name = TAG_IDS)
    var tagIds: String? = null
}
