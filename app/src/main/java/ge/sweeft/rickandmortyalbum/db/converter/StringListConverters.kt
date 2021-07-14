package ge.sweeft.rickandmortyalbum.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class StringListConverters {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<String?>?): String? {
        return gson.toJson(someObjects)
    }
}