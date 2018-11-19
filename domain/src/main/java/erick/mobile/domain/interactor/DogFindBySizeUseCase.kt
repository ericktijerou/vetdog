package erick.mobile.domain.interactor

import erick.mobile.domain.MissingUseCaseParameterException
import erick.mobile.domain.Schedulers
import erick.mobile.domain.UseCase
import erick.mobile.domain.entity.Dog
import erick.mobile.domain.gateway.InventoryGateway
import io.reactivex.Observable

class DogFindBySizeUseCase(schedulers: Schedulers, private val inventoryGateway: InventoryGateway) :
    UseCase<Pair<String, Boolean>, List<Dog>>(schedulers) {

    override fun buildObservable(params: Pair<String, Boolean>?): Observable<List<Dog>> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        val (size, refresh) = params
        return inventoryGateway.getDogs(size, 10, refresh)
    }
}