package com.sys1yagi.goatreader.tools

import android.content.Context
import com.sys1yagi.goatreader.extensions.*
import android.app.PendingIntent
import android.content.Intent
import java.util.Calendar
import android.app.AlarmManager

class Cron() {
    class object {
        val REQUEST_CODE = 0x2221
        val ACTION = "com.sys1yagi.goatreader.action.CRON"
        val INTERVAL = 1000L * 60L * 15L
        //        val INTERVAL = 1000L * 5L

        fun start(context: Context) {
            val intent = Intent(ACTION)
            intent.addCategory(Intent.CATEGORY_DEFAULT)

            val calendar: Calendar = Calendar.getInstance()!!;
            calendar.setTimeInMillis(System.currentTimeMillis());
            val triggerAtMillis = calendar.getTimeInMillis()

            val sender = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, 0)
            val manager = context.getAlarmManager()
            manager.cancel(sender)
            manager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, INTERVAL, sender)
        }
    }
}
