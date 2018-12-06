package erick.mobile.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "EventType")
data class DogTypeLocalModel(
    @PrimaryKey var id: Int,
    var name: String)