package erick.mobile.data.local

import erick.mobile.data.local.dao.DogDao
import erick.mobile.data.local.model.DogLocalModel
import io.reactivex.Observable

class DogLocalDataSource(private val dogDao: DogDao) {
    fun findByType(): Observable<List<DogLocalModel>> = dogDao.getAll().toObservable()
    fun insertAll(dogs: List<DogLocalModel>) = dogDao.insertAll(*dogs.toTypedArray())
    fun deleteByType() = dogDao.deleteByType()
}