package com.sys1yagi.goatreader.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sys1yagi.goatreader.services.FetchService

class CronReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, javaClass<FetchService>())
        context.startService(i);
    }
}
