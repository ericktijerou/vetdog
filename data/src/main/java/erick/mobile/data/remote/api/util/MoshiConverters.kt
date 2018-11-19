package erick.mobile.data.remote.api.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class MoshiConverters {

    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())

    @FromJson
    fun timestampToDate(value: String?): Date? = if (value == null) null else format.parse(value)

    @ToJson
    fun dateToTimestamp(date: Date?): String? = date?.let { format.format(date) }
}