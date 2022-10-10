package com.example.cineapp.services.objects.authentication

import com.google.gson.annotations.SerializedName

data class AuthenticationToken(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("expires_at")
    val expirationToken: String,
    @SerializedName("request_token")
    val token: String
)
