package erick.mobile.data.local.util

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import erick.mobile.data.local.model.BreedLocalModel
import java.util.*

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun timestampToDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<BreedLocalModel> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<BreedLocalModel>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<BreedLocalModel>): String {
        return gson.toJson(someObjects)
    }
}