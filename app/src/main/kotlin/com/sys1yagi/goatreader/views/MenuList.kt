package com.sys1yagi.goatreader.views

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.view.View
import android.support.v4.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import com.sys1yagi.goatreader.R
import android.view.MenuItem
import android.widget.ListView
import com.sys1yagi.goatreader.models.Menu
import com.activeandroid.query.Select
import com.sys1yagi.goatreader.models.Item
import com.activeandroid.query.From

class MenuList() {
    var drawerToggle: ActionBarDrawerToggle? = null

    fun getCount(f: ((From?) -> From?)): Int {
        return f(Select()?.from(javaClass<Item>()))?.count()!!
    }

    fun setupList(activity: ActionBarActivity, listView: ListView) {
        val adapter: MenuListAdapter = MenuListAdapter(activity)
        adapter.add(Menu(R.drawable.ic_all, "All", getCount { f -> f }))
        adapter.add(Menu(R.drawable.ic_unread, "Unread", getCount { f -> f?.where("${Item.IS_READ}=?", false) }))
        adapter.add(Menu(R.drawable.ic_read, "Read", getCount { f -> f?.where("${Item.IS_READ}=?", true) }))
        adapter.add(Menu(R.drawable.ic_fav, "Fav", getCount { f -> f?.where("${Item.IS_FAV}=?", true) }))
        listView.setAdapter(adapter)

    }

    fun setupActionBar(activity: ActionBarActivity, drawerLayout: DrawerLayout) {
        val actionBar = activity.getSupportActionBar()

        drawerToggle = object : ActionBarDrawerToggle(
                activity, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerClosed(view: View?) {
                super.onDrawerClosed(view)
            }

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
            }
        }
        drawerLayout.setDrawerListener(drawerToggle);
        actionBar?.setDisplayHomeAsUpEnabled(true);
        actionBar?.setHomeButtonEnabled(true);
    }

    fun onPostCreate() {
        drawerToggle?.syncState();
    }

    fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return drawerToggle!!.onOptionsItemSelected(item!!)
    }

}
