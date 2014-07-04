package com.sys1yagi.goatreader.testtools

import android.test.ApplicationTestCase
import android.app.Application
import com.activeandroid.Configuration
import com.activeandroid.ActiveAndroid

open class DatabaseTestCase : ApplicationTestCase<Application>(javaClass<Application>()) {

    class object {
        val DB_NAME = "test.db"
    }

    override fun setUp() {
        val configuration = Configuration.Builder(getContext())
                .setDatabaseName(DB_NAME)
                ?.setDatabaseVersion(1)
                ?.create()

        ActiveAndroid.initialize(configuration)
    }

    override fun tearDown() {
        getContext()?.deleteDatabase(DB_NAME)
        ActiveAndroid.dispose()
    }

}
