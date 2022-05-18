package com.mvvm.example.models.userLogin

import com.google.gson.annotations.SerializedName

data class ModelLogin(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("expires_at")
	val expiresAt: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null
)