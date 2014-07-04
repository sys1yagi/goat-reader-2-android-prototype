package com.sys1yagi.goatreader.extensions

import android.content.Context
import android.app.AlarmManager

public fun Context.getAlarmManager(): AlarmManager {
    return getSystemService(Context.ALARM_SERVICE) as AlarmManager
}
