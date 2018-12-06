package erick.mobile.domain.interactor

import erick.mobile.domain.Schedulers
import erick.mobile.domain.UseCase
import erick.mobile.domain.entity.Dog
import erick.mobile.domain.gateway.InventoryGateway
import io.reactivex.Observable

class DogGetByIdUseCase(schedulers: Schedulers, private val inventoryGateway: InventoryGateway) :
    UseCase<String, Dog>(schedulers) {

    override fun buildObservable(params: String?): Observable<Dog> {
        return inventoryGateway.getDogById(params!!)
    }
}