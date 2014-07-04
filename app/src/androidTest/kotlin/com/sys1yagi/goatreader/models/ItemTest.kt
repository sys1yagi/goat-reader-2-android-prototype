package com.sys1yagi.goatreader.models

import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Select
import com.sys1yagi.goatreader.testtools.DatabaseTestCase
import kotlin.test.*

public class ItemTest() : DatabaseTestCase() {

    public fun testCreateItem() {
        val item = Item.create(10, "title", "description", "link", "imageLink")
        assertNotNull(item)
        assertEquals(10L, item.feedId)
        assertEquals("title", item.title)
        assertEquals("description", item.description)
        assertEquals("link", item.link)
        assertEquals("imageLink", item.imageLink)
    }

    public fun testSaveItem() {
        val item = Item.create(10, "title", "description", "link", "imageLink")
        assertNotNull(item)
        assertEquals(10L, item.feedId)
        assertEquals("title", item.title)
        assertEquals("description", item.description)
        assertEquals("link", item.link)
        assertEquals("imageLink", item.imageLink)
        ActiveAndroid.beginTransaction()
        val id = item.save()
        ActiveAndroid.setTransactionSuccessful()
        ActiveAndroid.endTransaction()

        assertEquals(1, Select().from(javaClass<Item>())?.count())
        val record: Item? = Select().from(javaClass<Item>())?.where("id=" + id)?.executeSingle()
        assertNotNull(record)
        assertEquals("title", record?.title)
        assertEquals("description", record?.description)
        assertEquals("link", record?.link)
        assertEquals("imageLink", record?.imageLink)
    }
}
