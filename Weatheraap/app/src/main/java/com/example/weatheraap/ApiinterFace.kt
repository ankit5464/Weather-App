package com.example.weatheraap

import WeatherApp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiinterFace {
    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("q") city:String,
        @Query("aapid") aapid:String,
        @Query("units") units:String
    ): Call<WeatherApp>
}