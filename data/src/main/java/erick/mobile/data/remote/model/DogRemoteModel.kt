package erick.mobile.data.remote.model

import com.squareup.moshi.Json

data class DogRemoteModel(

	@Json(name="id")
	val id: String? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="breeds")
	val breeds: List<BreedRemoteModel?>? = null
)