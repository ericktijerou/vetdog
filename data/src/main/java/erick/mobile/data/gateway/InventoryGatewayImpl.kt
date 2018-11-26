package erick.mobile.data.gateway

import erick.mobile.data.gateway.mapper.InventoryMapper
import erick.mobile.data.repository.DogRepository
import erick.mobile.domain.entity.Dog
import erick.mobile.domain.gateway.InventoryGateway
import io.reactivex.Observable

class InventoryGatewayImpl(
    private val dogRepository: DogRepository
) : InventoryGateway {

    private val mapper = InventoryMapper()

    override fun getDogs(size: String, limit: Int, refresh: Boolean): Observable<List<Dog>> =

        dogRepository.findBySize(size, limit, refresh)
            .doOnError { println("Dog By Type($size) Error") }
            .map { it.map { mapper.toEntity(it) } }
}
