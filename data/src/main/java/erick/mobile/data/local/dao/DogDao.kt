package erick.mobile.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import erick.mobile.data.local.model.DogLocalModel
import io.reactivex.Maybe

@Dao
interface DogDao {

    @Query("SELECT * FROM Dog")
    fun getAll(): Maybe<List<DogLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dogs: DogLocalModel)

    @Query("DELETE FROM Dog")
    fun deleteByType()

    @Query("SELECT * FROM Dog WHERE id = :id")
    fun getById(id: Int): Maybe<DogLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dog: DogLocalModel)
}