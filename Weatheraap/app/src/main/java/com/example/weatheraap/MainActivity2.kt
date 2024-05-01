package com.example.weatheraap

import WeatherApp
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//6154bba6c98b17ff8db027e6908a7b2d
//6154bba6c98b17ff8db027e6908a7b2d
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val ImageButton=findViewById<ImageView>(R.id.imageView2)
        ImageButton.setOnClickListener {
            Toast.makeText(this@MainActivity2, "Image clicked", Toast.LENGTH_SHORT).show()
            fetchWeatherData()
        }

    }


    private fun fetchWeatherData() {

        val retrofit=Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        val service=retrofit.create(ApiinterFace::class.java)

        val response=service.getWeatherData("bulandshahr","6154bba6c98b17ff8db027e6908a7b2d","metric")

        response.enqueue(object : Callback<WeatherApp> {
            override fun onResponse(call: Call<WeatherApp>, response: Response<WeatherApp>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp.toString()
                    Log.d(TAG, "onResponse: $temperature")
                }
            }

            override fun onFailure(call: Call<WeatherApp>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}