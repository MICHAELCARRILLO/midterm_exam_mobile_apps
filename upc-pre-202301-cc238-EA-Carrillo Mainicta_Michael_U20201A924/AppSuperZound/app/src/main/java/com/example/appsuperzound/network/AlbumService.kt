package com.example.appsuperzound.network

import com.example.appsuperzound.models.ApiResponseDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AlbumService {
    @GET("mostloved.php?format=album")
    fun getAlbums(): Call<ApiResponseDetails>
}