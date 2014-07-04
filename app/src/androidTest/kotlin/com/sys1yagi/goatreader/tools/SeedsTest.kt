package com.sys1yagi.goatreader.tools

import kotlin.test.*
import com.sys1yagi.goatreader.testtools.DatabaseTestCase
import com.activeandroid.query.Select
import com.sys1yagi.goatreader.models.Feed

public class SeedsTest() : DatabaseTestCase() {

    public fun testInitDb() {
        assertEquals(0, Select().from(javaClass<Feed>())!!.count())
        Seeds.initDb()
        assertEquals(1, Select().from(javaClass<Feed>())!!.count())
    }
}
