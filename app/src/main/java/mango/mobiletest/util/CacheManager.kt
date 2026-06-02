package mango.mobiletest.util

import android.content.Context

/**
 * @author: Mango
 * @date: 2026/6/1
 * @description:
 */
object CacheManager {
    private const val SP_NAME = "booking_cache"
    private const val KEY_BOOKING_CACHE_TIME = "booking_cache_time"
    private const val KEY_BOOKING_CACHE_DATA = "booking_cache_data"
    //一小时有效
    private const val CACHE_VALID_DURATION = 60 * 60 * 1000

    fun saveCache(context: Context, json: String) {
        val sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putLong(KEY_BOOKING_CACHE_TIME, System.currentTimeMillis())
        edit.putString(KEY_BOOKING_CACHE_DATA, json)
        edit.apply()
    }

    fun loadCache(context: Context): String? {
        val sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        val cacheTime = sp.getLong(KEY_BOOKING_CACHE_TIME, 0L)
        if (System.currentTimeMillis() - cacheTime > CACHE_VALID_DURATION){
            return null
        }
        val json = sp.getString(KEY_BOOKING_CACHE_DATA, null)
        return json
    }

}