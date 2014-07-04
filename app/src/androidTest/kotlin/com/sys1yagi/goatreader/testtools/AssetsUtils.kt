package com.sys1yagi.goatreader.testtools

import android.app.Instrumentation
import org.apache.commons.io.IOUtils
import java.io.InputStream
import android.content.Context
import com.sys1yagi.goatreader.tools.Logger

public class AssetsUtils() {
    class object {

        private val TAG: String = javaClass<AssetsUtils>().getSimpleName()

        public fun readJson(context: Context?, fileName: String?): String? {
            if (context == null) {
                return null
            }
            try {
                val input = context.getAssets()?.open(fileName)
                val lines = IOUtils.readLines(input)
                //val sb: StringBuilder = StringBuilder()
                var s = ""
                lines?.forEach {
                    //Overload resolution ambiguity
                    //sb.append(it)

                    s += it
                }
                return s//sb.toString()
            } catch (e: Exception) {
                Logger.d(TAG, "" + e.getMessage())
            }

            return null
        }
    }
}
