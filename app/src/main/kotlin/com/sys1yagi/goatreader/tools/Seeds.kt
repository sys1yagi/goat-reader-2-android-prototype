package com.sys1yagi.goatreader.tools

import com.sys1yagi.goatreader.models.Feed
import com.activeandroid.query.Select

public class Seeds() {
    class object {

        public fun initDb() {
            if (isInitialized()) {
                return
            }
            val feed = Feed()
            feed.title = "はてなブックマーク - 新着エントリー"
            feed.url = "http://b.hatena.ne.jp/entrylist?mode=rss"
            feed.save()
        }

        public fun isInitialized(): Boolean {
            return Select().from(javaClass<Feed>())!!.count() > 0
        }
    }
}
