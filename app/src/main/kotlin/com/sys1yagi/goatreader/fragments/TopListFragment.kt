package com.sys1yagi.goatreader.fragments

import android.support.v4.app.Fragment
import com.sys1yagi.goatreader.extensions.*
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.sys1yagi.goatreader.R
import com.activeandroid.query.Select
import com.sys1yagi.goatreader.models.Item
import android.widget.ListView
import com.sys1yagi.goatreader.views.ItemListAdapter
import android.content.Intent
import android.net.Uri
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback
import com.sys1yagi.goatreader.tools.Logger

class TopListFragment : Fragment() {

    class object {

        val TAG = javaClass<TopListFragment>().getSimpleName()!!

        fun newInstance(): TopListFragment {
            val fragment = TopListFragment()
            return fragment
        }
    }

    val listView: ListView by viewInjector(R.id.item_list)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_toplist, null);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListView()
    }
    fun setupListView() {
        val adapter = ItemListAdapter(getActivity()!!, { a, remain ->
            var from = Select().from(javaClass<Item>())
                    ?.where("${Item.IS_READ}=?", false)

            remain?.forEach {
                from = from?.where("id!=?", it.getId())
            }
            from?.orderBy("${Item.CREATED_AT} desc")
                    ?.limit(30)
                    ?.execute<Item>()
                    ?.forEach {
                        a.add(it)
                    }
        })

        val swipeDismissAdaper = SwipeDismissAdapter(adapter, OnDismissCallback {
            listView, positions ->
            positions?.forEach {
                val item = adapter.getItem(it)
                item!!.isRead = true
                item!!.save()
                adapter.remove(item);
            }
        })
        swipeDismissAdaper.setAbsListView(listView)
        listView.setAdapter(swipeDismissAdaper)
        listView.setOnItemClickListener {
            parent, view, position, id ->
            val item = adapter.getItem(position)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item?.link!!))
            getActivity()?.startActivity(intent)
        }
    }
}
