package com.example.ninhaoshijie.logic.network

import com.example.ninhaoshijie.CommonApplication
import com.example.ninhaoshijie.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
  @GET("v2/place?token=${CommonApplication.TOKEN}&lang=zh_CN")
  fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}