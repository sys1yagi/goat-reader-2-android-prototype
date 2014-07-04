package com.sys1yagi.goatreader.extensions

import android.view.View
import android.app.Activity

public trait Injector<T> {
    fun get(thisRef: Any, prop: PropertyMetadata): T
}

public fun <T : View> Activity.viewInjector(resId: Int? = null): Injector<T> = object: Injector<T> {

    private var view: T? = null

    override fun get(thisRef: Any, prop: PropertyMetadata): T {
        if (view == null) {
            view = getView(resId, prop.name)
        }

        return view!!
    }

    private fun <T : View> Activity.getView(resId: Int?, name: String): T =
            (if (resId != null)
                findViewById(resId)
            else
                findViewByName(name)) as T

    private fun Activity.findViewByName(name: String): View? =
            try {
                val resIds = Class.forName(getPackageName() + ".R\$id")
                val resId = resIds?.getField(name)?.get(resIds) as Int
                findViewById(resId)
            } catch(e: Exception) {
                null
            }
}
