package erick.mobile.data.local

import erick.mobile.data.local.dao.DogTypeDao
import erick.mobile.data.local.model.DogTypeLocalModel
import io.reactivex.Observable

class DogTypeLocalDataSource(private val dogTypeDao: DogTypeDao) {

    fun getAll(): Observable<List<DogTypeLocalModel>> = dogTypeDao.getAll().toObservable()

    fun getById(id: String): Observable<DogTypeLocalModel> = dogTypeDao.getById(id).toObservable()

    fun insertAll(events: List<DogTypeLocalModel>) = dogTypeDao.insertAll(*events.toTypedArray())
}