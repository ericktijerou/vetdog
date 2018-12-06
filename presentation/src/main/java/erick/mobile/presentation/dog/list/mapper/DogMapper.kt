package erick.mobile.presentation.dog.list.mapper

import android.content.Context
import erick.mobile.domain.entity.Breed
import erick.mobile.domain.entity.Dog
import erick.mobile.presentation.dog.list.model.BreedModel
import erick.mobile.presentation.dog.list.model.DogModel

class DogMapper(private val context: Context) {

    fun toModel(dog: Dog): DogModel {
        return DogModel(dog.id, dog.url, toModel(dog.breeds!![0]!!))
    }

    fun toModel(breed: Breed): BreedModel {
        return BreedModel(breed.id, breed.lifeSpan, breed.breedGroup, breed.temperament, breed.name, breed.bredFor, breed.weight, breed.height)
    }
}