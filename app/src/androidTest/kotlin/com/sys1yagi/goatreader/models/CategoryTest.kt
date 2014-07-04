package com.sys1yagi.goatreader.models

import com.sys1yagi.goatreader.testtools.DatabaseTestCase
import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Select
import kotlin.test.*

public class CategoryTest() : DatabaseTestCase() {
    public fun testCreateItem() {
        val category = Category.create("category")
        assertNotNull(category)
        assertEquals("category", category.name)
    }

    public fun testSaveItem() {
        val category = Category.create("category")
        assertNotNull(category)
        assertEquals("category", category.name)
        ActiveAndroid.beginTransaction()
        val id = category.save()
        ActiveAndroid.setTransactionSuccessful()
        ActiveAndroid.endTransaction()

        assertEquals(1, Select().from(javaClass<Category>())?.count())
        val record: Category? = Select().from(javaClass<Category>())?.where("id=" + id)?.executeSingle()
        assertNotNull(record)
        assertEquals("category", record?.name)
    }
}
