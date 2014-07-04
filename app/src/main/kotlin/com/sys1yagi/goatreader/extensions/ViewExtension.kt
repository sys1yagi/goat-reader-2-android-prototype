package com.sys1yagi.goatreader.extensions

import android.view.View

public fun <T : View> View.viewInjector(resId: Int): Injector<T> = object : Injector<T> {

    private var view: T? = null

    override fun get(thisRef: Any, prop: PropertyMetadata): T {
        if (view == null) {
            view = getView(resId)
        }

        return view!!
    }

    private fun <T : View> View.getView(resId: Int): T = findViewById(resId) as T
}
