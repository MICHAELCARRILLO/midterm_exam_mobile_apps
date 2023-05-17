package com.example.appsuperzound.models

import com.google.gson.annotations.SerializedName


class ApiResponseDetails (
    @SerializedName("loved")
    val albums: List<Album>
    )