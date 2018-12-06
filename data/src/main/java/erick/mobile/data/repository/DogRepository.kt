package erick.mobile.data.repository

import erick.mobile.data.local.DogLocalDataSource
import erick.mobile.data.local.model.DogLocalModel
import erick.mobile.data.remote.DogRemoteDataSource
import erick.mobile.data.repository.mapper.DogMapper
import io.reactivex.Observable

class DogRepository(
    private val dogLocalDataSource: DogLocalDataSource,
    private val dogRemoteDataSource: DogRemoteDataSource,
    private val dogMapper: DogMapper) {

    fun findBySize(size: String, limit: Int, refresh: Boolean = false): Observable<List<DogLocalModel>> {

        val local = dogLocalDataSource.findByType()
            .filter { !it.isEmpty() }

        val remote = dogRemoteDataSource.findBySize(size, limit)
            .map { dogMapper.toLocal(it) }
            .doOnNext { dogLocalDataSource.insertAll(it) }

        return Observable.just(refresh)
            .doOnNext { if (it) dogLocalDataSource.deleteByType() }
            .flatMap {
                Observable.concat(local, remote)
                    .firstElement()
                    .toObservable()
            }
    }

    fun getById(id: String): Observable<DogLocalModel> {

        val local = dogLocalDataSource.getById(id)

        val remote = dogRemoteDataSource.getById(id)
            .map { dogMapper.toLocal(it) }
            .doOnNext { dogLocalDataSource.insert(it) }

        return Observable.concat(local, remote)
            .firstElement()
            .toObservable()
    }
}
