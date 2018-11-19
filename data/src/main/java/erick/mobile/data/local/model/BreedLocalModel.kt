package erick.mobile.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Breed")
data class BreedLocalModel(

    @PrimaryKey var id: Int,

    var lifeSpan: String,

    var breedGroup: String,

    var temperament: String,

    var name: String,

    var bredFor: String,

    var weight: String,

    var height: String
)