package erick.mobile.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Breed")
data class BreedLocalModel(

    @PrimaryKey var id: Int,

    var lifeSpan: String? = null,

    var breedGroup: String? = null,

    var temperament: String? = null,

    var name: String? = null,

    var bredFor: String? = null,

    var weight: String? = null,

    var height: String? = null
)