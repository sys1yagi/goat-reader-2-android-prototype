package com.sys1yagi.goatreader.activities

import android.os.Bundle
import com.sys1yagi.goatreader.tools.ContextBus
import android.support.v7.app.ActionBarActivity
import com.sys1yagi.goatreader.fragments.TopListFragment
import com.sys1yagi.goatreader.tools.Cron
import com.sys1yagi.goatreader.extensions.*
import android.support.v4.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import com.sys1yagi.goatreader.R
import android.view.View
import android.widget.ListView
import android.view.MenuItem

public class MainActivity() : ActionBarActivity() {

    var drawerToggle: ActionBarDrawerToggle? = null
    val drawerLayout: DrawerLayout by viewInjector(R.id.drawer_layout)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Cron.start(this)
        ContextBus.getInstance(this)?.register(this) ;

        setContentView(R.layout.activity_main)
        setupActionBar()
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    ?.beginTransaction()
                    ?.add(R.id.content_frame, TopListFragment.newInstance())
                    ?.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ContextBus.getInstance(this)?.unregister(this);
    }

    fun setupActionBar() {
        val actionBar = getSupportActionBar()
        actionBar?.setLogo(getResources()?.getDrawable(R.drawable.ic_theme_with_shadow))
        drawerToggle = object : ActionBarDrawerToggle(
                this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
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

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState);
        drawerToggle?.syncState();
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle?.onOptionsItemSelected(item)!!) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }



    //    Subscribe fun onMoge(a: String) {
    //      //otto
    //    }


}
