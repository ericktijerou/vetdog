package erick.mobile.data.local.inventory

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import erick.mobile.data.local.dao.DogDao
import erick.mobile.data.local.model.BreedLocalModel
import erick.mobile.data.local.model.DogLocalModel
import erick.mobile.data.local.util.Converters

@Database(entities = [DogLocalModel::class, BreedLocalModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {
        fun newInstance(context: Context): InventoryDatabase {
            return Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java).build()
        }
    }
}