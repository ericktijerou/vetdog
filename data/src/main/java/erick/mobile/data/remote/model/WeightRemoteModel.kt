package erick.mobile.data.remote.model

import com.squareup.moshi.Json

data class WeightRemoteModel(

	@Json(name="metric")
	val metric: String? = null,

	@Json(name="imperial")
	val imperial: String? = null
)