package com.azuka.jakartaweatherprediction


import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd HH:mm:ss",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")) : Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
//    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatToDate(dateFormat: String = "dd MMM yyyy", timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
//    formatter.timeZone = timeZone
    return formatter.format(this)
}

fun Date.formatToTime(timeFormat: String = "HH:mm", timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(timeFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}
