package erick.mobile.data.repository

import erick.mobile.data.local.DogTypeLocalDataSource
import erick.mobile.data.local.model.DogTypeLocalModel
import io.reactivex.Observable

class DogTypeRepository(
    private val dogTypeLocalDataSource: DogTypeLocalDataSource) {

    fun getById(id: String): Observable<DogTypeLocalModel> = dogTypeLocalDataSource.getById(id)
}