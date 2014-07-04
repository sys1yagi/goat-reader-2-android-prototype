package com.sys1yagi.goatreader

import android.app.Application
import com.activeandroid.ActiveAndroid
import com.sys1yagi.goatreader.tools.Logger
import com.sys1yagi.goatreader.tools.Cron
import com.sys1yagi.goatreader.tools.Seeds

class GoatReaderApplication : Application() {

    override fun onCreate() {
        super<Application>.onCreate()
        init()
    }

    override fun onTerminate() {
        terminate()
    }

    fun init() {
        Logger.d("GoatReaderApplication", "init")
        ActiveAndroid.initialize(this)
        Seeds.initDb()
        Cron.start(this)
    }

    fun terminate() {
        ActiveAndroid.dispose()
    }


}
