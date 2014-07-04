package com.sys1yagi.goatreader.extensions

import android.view.View
import android.support.v4.app.Fragment
import android.content.Context
import android.app.AlarmManager

public fun Context.getAlarmManager(): AlarmManager {
    return getSystemService(Context.ALARM_SERVICE) as AlarmManager
}
