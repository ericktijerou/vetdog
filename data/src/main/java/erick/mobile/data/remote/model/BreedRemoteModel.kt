package erick.mobile.data.remote.model

import com.squareup.moshi.Json

data class BreedRemoteModel(

	@Json(name="life_span")
	val lifeSpan: String? = null,

	@Json(name="breed_group")
	val breedGroup: String? = null,

	@Json(name="temperament")
	val temperament: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="bred_for")
	val bredFor: String? = null,

	@Json(name="weight")
	val weight: WeightRemoteModel? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="height")
	val height: HeightRemoteModel? = null
)