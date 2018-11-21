package erick.mobile.data.gateway.mapper

import erick.mobile.data.local.model.BreedLocalModel
import erick.mobile.data.local.model.DogLocalModel
import erick.mobile.domain.entity.Breed
import erick.mobile.domain.entity.Dog

class InventoryMapper {
    fun toEntity(dog: DogLocalModel) =
        Dog(dog.id, dog.url, toListEntity(dog.breeds))

    fun toEntity(breed: BreedLocalModel) = Breed(
        breed.id,
        breed.lifeSpan,
        breed.breedGroup,
        breed.temperament,
        breed.name,
        breed.weight,
        breed.height
    )

    fun toListEntity(breeds: List<BreedLocalModel?>?) : List<Breed> {
        val newBreeds = mutableListOf<Breed>()
        if (breeds != null) {
            for (breedModel : BreedLocalModel? in breeds) {
                newBreeds.add(toEntity(breedModel!!))
            }
        }
        return newBreeds
    }
}