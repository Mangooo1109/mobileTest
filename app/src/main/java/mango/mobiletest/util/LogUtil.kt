package mango.mobiletest.util

import android.util.Log

/**
 * @author: Mango
 * @date: 2026/5/31
 * @description:
 */
object LogUtil {
    private const val TAG = "MobileTest"
    fun info(msg: String){
        Log.i(TAG, msg)
    }

    fun error(msg: String){
        Log.e(TAG, msg)
    }
}