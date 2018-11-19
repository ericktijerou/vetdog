package erick.mobile.presentation.dog.list.mapper

import android.content.Context
import erick.mobile.domain.entity.Dog
import erick.mobile.presentation.dog.list.model.DogModel

class DogMapper(private val context: Context) {

    fun toModel(event: Dog): DogModel {
        return DogModel(event.id, event.breeds!![0]!!.name, event.url)
    }
}