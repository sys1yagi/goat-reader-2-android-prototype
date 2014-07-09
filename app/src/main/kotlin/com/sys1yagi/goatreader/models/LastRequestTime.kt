package com.sys1yagi.goatreader.models

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Select

Table(name = "LastRequestTime")
public class LastRequestTime() : Model() {

    Column(name = "lastRequestTime")
    private var lastRequestTime: Long = 0

    class object {

        private fun getInstance(): LastRequestTime {
            var record = Select().from(javaClass<LastRequestTime>())?.executeSingle<LastRequestTime>()
            if (record == null) {
                record = LastRequestTime()
                record!!.save()
            }
            return record!!
        }

        public fun getLastRequestTimeSpan(): Long {
            return System.currentTimeMillis() - getLastRequestTime()
        }

        public fun getLastRequestTime(): Long {
            return getInstance().lastRequestTime
        }

        public fun saveLastRequestTime() {
            val record = getInstance()
            record.lastRequestTime = System.currentTimeMillis()
            record.save()
        }
    }
}
