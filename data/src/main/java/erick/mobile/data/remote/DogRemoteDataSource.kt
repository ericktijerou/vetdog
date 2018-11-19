package erick.mobile.data.remote

import erick.mobile.data.remote.api.TheDogService
import erick.mobile.data.remote.model.DogRemoteModel
import io.reactivex.Observable

class DogRemoteDataSource(private val theDogService: TheDogService) {
    fun findBySize(size: String, limit: Int): Observable<List<DogRemoteModel>> = theDogService.getDogs(size, limit)
}