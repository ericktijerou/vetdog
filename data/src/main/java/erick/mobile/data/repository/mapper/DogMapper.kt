package erick.mobile.data.repository.mapper

import erick.mobile.data.local.model.BreedLocalModel
import erick.mobile.data.local.model.DogLocalModel
import erick.mobile.data.remote.model.BreedRemoteModel
import erick.mobile.data.remote.model.DogRemoteModel

class DogMapper {
    fun toLocal(dog: DogRemoteModel) = DogLocalModel(dog.id!!,  dog.url, toListLocal(dog.breeds))

    fun toLocal(breed: BreedRemoteModel) = BreedLocalModel(breed.id!!, breed.lifeSpan, breed.breedGroup, breed.temperament, breed.name, breed.bredFor, breed.weight!!.metric!!, breed.height!!.metric!!)

    fun toListLocal(breeds: List<BreedRemoteModel?>?) : List<BreedLocalModel> {
        val newBreeds = mutableListOf<BreedLocalModel>()
        if (breeds != null) {
            for (breedModel : BreedRemoteModel? in breeds) {
                newBreeds.add(toLocal(breedModel!!))
            }
        }
        return newBreeds
    }

    fun toLocal(items: List<DogRemoteModel>) = items.map { toLocal(it) }
}