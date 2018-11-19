package erick.mobile.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

@Entity(tableName = "Dog")
data class DogLocalModel(
    @PrimaryKey var id: String,

    var url: String? = null,

    var breeds: List<BreedLocalModel>
)