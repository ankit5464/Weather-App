package com.example.weatheraap

import WeatherApp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiinterFace {
    @GET("weather")
    fun getWeatherData(
        @Query("q") city:String,
        @Query("appid") aapid:String
    ): Call<WeatherApp>
}