package com.ipotoday.ipotoday.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun parseTime(time: Long): String {
        val format = SimpleDateFormat("yyyy.MM.dd")
        return format.format(Date(time))
    }
}