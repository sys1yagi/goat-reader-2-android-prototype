package com.sys1yagi.goatreader.tools

import android.test.AndroidTestCase
import com.squareup.otto.Bus
import android.content.Context
import kotlin.test.assertNotNull
import android.test.mock.MockContext
import kotlin.test.assertFalse

public class ContextBusTest() : AndroidTestCase() {

    public fun testGetBus() {
        val context: Context = getContext()!!
        val bus: Bus? = ContextBus.getInstance(context)
        assertNotNull(bus)
    }

    public fun testGetBusTwoContexts() {
        val context: Context = getContext()!!
        val bus: Bus? = ContextBus.getInstance(context)
        assertNotNull(bus)

        val mock: MockContext = MockContext()
        val bus2: Bus? = ContextBus.getInstance(mock)
        assertNotNull(bus2)

        assertFalse(bus.equals(bus2))
    }
}
