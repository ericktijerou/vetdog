package erick.mobile.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import erick.mobile.data.local.model.DogTypeLocalModel
import io.reactivex.Maybe

@Dao
interface DogTypeDao {

    @Query("SELECT * FROM EventType WHERE id = :id")
    fun getById(id: String): Maybe<DogTypeLocalModel>

    @Query("SELECT * FROM EventType")
    fun getAll(): Maybe<List<DogTypeLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg eventTypes: DogTypeLocalModel)
}