package com.sys1yagi.goatreader.tools

import android.util.Log
import com.sys1yagi.goatreader.BuildConfig

public class Logger() {
    class object {
        fun d(tag: String, message: String) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, message)
            }
        }
    }
}
