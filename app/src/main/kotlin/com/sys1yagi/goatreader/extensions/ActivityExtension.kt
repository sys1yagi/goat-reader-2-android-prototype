package com.sys1yagi.goatreader.extensions

import android.view.View
import android.app.Activity
import com.sys1yagi.goatreader.tools.Logger

public trait Injector<T> {
    fun get(thisRef: Any, prop: PropertyMetadata): T
}

public fun <T : View> Activity.viewInjector(resId: Int): Injector<T> = object : Injector<T> {

    private var view: T? = null

    override fun get(thisRef: Any, prop: PropertyMetadata): T {
        if (view == null) {
            view = getView(resId)
        }

        return view!!
    }

    private fun <T : View> Activity.getView(resId: Int): T = findViewById(resId) as T

}
