package com.sys1yagi.goatreader.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.sys1yagi.goatreader.R
import com.sys1yagi.goatreader.models.Item
import android.widget.TextView
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

public class ItemListView(context: Context) : ArrayAdapter<Item>(context, -1) {

    class object {
        val FORMAT = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view: View? = convertView
        if (view == null) {
            view = View.inflate(getContext()!!, R.layout.listitem_item, null)
            view?.setTag(ViewHolder(view!!))
        }
        var holder: ViewHolder? = view?.getTag() as ViewHolder
        val item = getItem(position)
        if (item?.createdAt != null) {
            holder?.date?.setText(FORMAT.format(item?.createdAt))
        }
        holder?.title?.setText(item?.title)
        holder?.description?.setText(item?.description)
        holder?.link?.setText(item?.link)

        if (item?.imageLink != null) {
            holder?.icon?.setVisibility(View.VISIBLE)
            Picasso.with(getContext())?.load(item?.imageLink)?.into(holder?.icon)
        } else {
            holder?.icon?.setVisibility(View.GONE)
        }
        return view
    }
    class ViewHolder(root: View) {

        var date: TextView? = null
        var title: TextView? = null
        var description: TextView? = null
        var icon: ImageView? = null
        var link: TextView? = null

        {
            date = root.findViewById(R.id.date_text) as TextView
            title = root.findViewById(R.id.title_text) as TextView
            description = root.findViewById(R.id.description_text) as TextView
            icon = root.findViewById(R.id.icon_image) as ImageView
            link = root.findViewById(R.id.link_text) as TextView
        }
    }
}
