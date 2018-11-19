package erick.mobile.data.remote.model

import com.squareup.moshi.Json

data class HeightRemoteModel(

	@Json(name="metric")
	val metric: String? = null,

	@Json(name="imperial")
	val imperial: String? = null
)