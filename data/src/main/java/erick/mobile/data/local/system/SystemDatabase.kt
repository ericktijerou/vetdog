package erick.mobile.data.local.system

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import erick.mobile.data.local.dao.DogTypeDao
import erick.mobile.data.local.model.DogTypeLocalModel
import erick.mobile.data.local.util.Converters

@Database(entities = [DogTypeLocalModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SystemDatabase : RoomDatabase() {

    abstract fun dogTypeDao(): DogTypeDao

    companion object {
        fun newInstance(context: Context): SystemDatabase {
            return Room.databaseBuilder(context, SystemDatabase::class.java, "veterinary-system.db").build()
        }
    }
}