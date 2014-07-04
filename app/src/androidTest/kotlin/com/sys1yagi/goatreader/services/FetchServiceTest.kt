package com.sys1yagi.goatreader.services

import kotlin.test.*
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import com.activeandroid.query.Select
import android.test.InstrumentationTestCase
import com.sys1yagi.goatreader.testtools.AssetsUtils
import com.sys1yagi.goatreader.models.Item

public class FetchServiceTest() : InstrumentationTestCase() {

    class object {
        val DB_NAME = "test.db"
    }

    override fun setUp() {
        val configuration = Configuration.Builder(getInstrumentation()?.getTargetContext())
                .setDatabaseName(DB_NAME)
                ?.setDatabaseVersion(1)
                ?.create()
        ActiveAndroid.initialize(configuration)
    }

    override fun tearDown() {
        getInstrumentation()?.getTargetContext()?.deleteDatabase(DB_NAME)
        ActiveAndroid.dispose()
    }

    public fun testParseRss() {
        val rss = AssetsUtils.readJson(getInstrumentation()?.getContext(), "rss.xml")
        assertNotNull(rss)
        val fetchService = FetchService()
        fetchService.parse(10L, rss)
        assertEquals(30, Select().from(javaClass<Item>())?.count())
    }

}
