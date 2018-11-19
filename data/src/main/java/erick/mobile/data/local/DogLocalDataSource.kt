package erick.mobile.data.local

import erick.mobile.data.local.dao.DogDao
import erick.mobile.data.local.model.DogLocalModel
import io.reactivex.Observable

class DogLocalDataSource(private val eventDao: DogDao) {
    fun findByType(): Observable<List<DogLocalModel>> = eventDao.getAll().toObservable()
    fun insertAll(dogs: List<DogLocalModel>) = eventDao.insertAll(*dogs.toTypedArray())
    fun deleteByType() = eventDao.deleteByType()
}