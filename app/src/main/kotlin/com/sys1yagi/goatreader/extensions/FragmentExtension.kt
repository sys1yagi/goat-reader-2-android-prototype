package com.sys1yagi.goatreader.extensions

import android.view.View
import android.support.v4.app.Fragment

public fun <T : View> Fragment.viewInjector(resId: Int): Injector<T> = object : Injector<T> {

    private var view: T? = null

    override fun get(thisRef: Any, prop: PropertyMetadata): T {
        if (view == null) {
            view = getView(resId)
        }

        return view!!
    }

    private fun <T : View> Fragment.getView(resId: Int): T =
            getView()?.findViewById(resId) as T
}
