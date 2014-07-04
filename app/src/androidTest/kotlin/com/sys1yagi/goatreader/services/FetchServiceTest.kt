package com.sys1yagi.goatreader.services

import android.test.AndroidTestCase
import com.squareup.otto.Bus
import android.content.Context
import android.test.mock.MockContext
import kotlin.test.*
import android.test.ApplicationTestCase
import android.app.Application
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import com.activeandroid.query.Select
import com.sys1yagi.goatreader.testtools.DatabaseTestCase
import android.test.InstrumentationTestCase
import com.sys1yagi.goatreader.testtools.AssetsUtils
import com.sys1yagi.goatreader.tools.Logger
import com.sys1yagi.goatreader.models.Feed

public class FetchServiceTest() : InstrumentationTestCase() {

    class object {
        val DB_NAME = "test2.db"
    }

    override fun setUp() {
        val configuration = Configuration.Builder(getInstrumentation()?.getContext())
                .setDatabaseName(DB_NAME)
        ?.setDatabaseVersion(1)
        ?.create()
        ActiveAndroid.initialize(configuration)
    }

    override fun tearDown() {
        getInstrumentation()?.getContext()?.deleteDatabase(DB_NAME)
        ActiveAndroid.dispose()
    }

    public fun testParseRss() {
        //        val rss = AssetsUtils.readJson(getInstrumentation()?.getContext(), "rss.xml")
        //        assertNotNull(rss)
        //        val fetchService = FetchService()
        //        fetchService.parse(10L, rss)
        //        assertEquals(30, Select().from(javaClass<Feed>())?.count())
    }

}
