package mango.mobiletest.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author: Mango
 * @date: 2026/5/31
 * @description:时间转换
 */

fun Long.formatDuration(): String {
    val h = this / 3600
    val m = (this % 3600) / 60
    val s = this % 60
    return String.format(Locale.getDefault(), "%02d:%02d:%02d", h, m, s)
}

fun String.formatDate(): String {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val time = this.toLong()
        sdf.format(Date(time * 3000))
    } catch (e: Exception) {
        ""
    }
}

