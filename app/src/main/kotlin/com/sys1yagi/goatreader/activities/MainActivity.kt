package com.sys1yagi.goatreader.activities

import android.os.Bundle
import com.sys1yagi.goatreader.tools.ContextBus
import com.sys1yagi.goatreader.R
import android.util.Log
import android.support.v7.app.ActionBarActivity
import android.widget.Button
import com.sys1yagi.goatreader.extensions.*
import com.squareup.otto.Subscribe
import android.widget.FrameLayout
import com.sys1yagi.goatreader.fragments.TopListFragment

public class MainActivity() : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContextBus.getInstance(this)?.register(this);

        setContentView(R.layout.activity_main)
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

    //    Subscribe fun onMoge(a: String) {
    //      //otto
    //    }


}
