package com.sys1yagi.goatreader.views

import android.content.Context
import android.widget.ArrayAdapter
import com.sys1yagi.goatreader.models.Menu
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.goatreader.R
import android.widget.ImageView
import android.widget.TextView

class MenuListAdapter(context: Context) : ArrayAdapter<Menu>(context, -1) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view: View? = convertView
        if (view == null) {
            view = createView()
        }
        val menu = getItem(position)
        (view?.findViewById(R.id.icon) as ImageView)?.setImageResource(menu?.iconResId!!)
        (view?.findViewById(R.id.title) as TextView)?.setText(menu?.title)
        (view?.findViewById(R.id.count_text) as TextView)?.setText("(${menu?.count})")
        return view
    }

    fun createView() = View.inflate(getContext()!!, R.layout.listitem_menu, null)

}
