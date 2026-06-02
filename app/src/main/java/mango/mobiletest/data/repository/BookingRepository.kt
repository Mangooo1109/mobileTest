package mango.mobiletest.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import mango.mobiletest.data.model.BookingData
import mango.mobiletest.util.CacheManager
import mango.mobiletest.util.LogUtil

/**
 * @author: Mango
 * @date: 2026/5/31
 * @description:
 */
class BookingRepository(private val context: Context) {
    private val gson: Gson = Gson()

    suspend fun loadBookingData(refresh: Boolean = false): BookingData? {
        val data: BookingData?

        withContext(Dispatchers.IO) {
            if (refresh) {
                data = refreshBookingData()
            } else {
                val localCache = CacheManager.loadCache(context)
                if (localCache.isNullOrEmpty()) {
                    data = refreshBookingData()
                } else {
                    data = gson.fromJson<BookingData>(localCache, BookingData::class.java)
                    LogUtil.info("data from cache: $data")
                }
            }

        }

        return data
    }

    private fun refreshBookingData(): BookingData? {
        val data: BookingData?
        val json =
            context.assets.open("booking.json").bufferedReader().use { it.readText() }
        CacheManager.saveCache(context, json)
        data = gson.fromJson<BookingData>(json, BookingData::class.java)
        LogUtil.info("data refresh: $data")
        return data
    }

}