package erick.mobile.domain.gateway

import erick.mobile.domain.entity.Dog
import io.reactivex.Observable

interface InventoryGateway {

    fun getDogs(size: String, limit: Int, refresh: Boolean = false): Observable<List<Dog>>

}