package com.mvvm.example.models.generalError

import com.google.gson.annotations.SerializedName

data class ModelError(

	@field:SerializedName("message")
	var message: String? = null,

	@field:SerializedName("errors")
	var errors: List<String?>? = arrayListOf()
)