package com.sys1yagi.goatreader.tools

import android.content.Context
import com.squareup.otto.Bus
import java.util.HashMap

public class ContextBus {
    class object {
        var map: MutableMap<Context, Bus> = HashMap();
        fun getInstance(context: Context): Bus? {
            var instance: Bus? = map.get(context)
            if (instance == null) {
                instance = Bus()
                map.put(context, instance!!)
            }
            return instance
        }
    }
}