package com.example.weatheraap
import WeatherApp
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//6154bba6c98b17ff8db027e6908a7b2d
//6154bba6c98b17ff8db027e6908a7b2d
//https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
class MainActivity2 : AppCompatActivity() {
    private var cityName: TextView? = null
    private var temp: TextView? = null
    private var max_temp:TextView?=null
    private var min_temp:TextView?=null
    private var Humidity:TextView?=null
    private var WindSpeed:TextView?=null
    private var date:TextView?=null
    private var Condition:TextView?=null
    private var sun_rise:TextView?=null
    private var sun_set:TextView?=null
    private var Sea:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val ImageButton=findViewById<ImageView>(R.id.imageView2)
        cityName = findViewById<TextView>(R.id.cityname)
        temp = findViewById<TextView>(R.id.temp)
        max_temp = findViewById<TextView>(R.id.max_temp)
        min_temp=findViewById<TextView>(R.id.min_temp)
        Humidity=findViewById<TextView>(R.id.humidity)
        WindSpeed=findViewById<TextView>(R.id.wind)
        Condition=findViewById<TextView>(R.id.condition)
        sun_rise=findViewById<TextView>(R.id.sunrise)
        sun_set=findViewById<TextView>(R.id.sunset)
        Sea=findViewById<TextView>(R.id.sea)

        //date=findViewById<TextView>(R.id.date)
        val currentDate = LocalDate.now()

        // Format the current date to get the day of the week
        val dayOfWeek = currentDate.format(DateTimeFormatter.ofPattern("EEEE"))
        date?.text=currentDate.toString()
        date?.text=dayOfWeek.toString()




        println("Day of the week: $dayOfWeek")
        fetchWeatherData()
        ImageButton.setOnClickListener {
            Toast.makeText(this@MainActivity2, "Image clicked", Toast.LENGTH_SHORT).show()
            fetchWeatherData()
        }

    }


    private fun fetchWeatherData() {

        val retrofit=Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        val service=retrofit.create(ApiinterFace::class.java)

        val response: retrofit2.Call<WeatherApp> = service.getWeatherData("Noida","6e1456c623120f8db67b5d150252ee51")

        response.enqueue(object : Callback<WeatherApp> {
            override fun onResponse(call: retrofit2.Call<WeatherApp>, response: retrofit2.Response<WeatherApp>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp.toString()
                    temp?.text = temperature
                    cityName?.text = responseBody.name.toString()
                    max_temp?.text = responseBody.main.temp_max.toString()            //?.  = null safty excepction
                    min_temp?.text = responseBody.main.temp_min.toString()
                    Humidity?.text = responseBody.main.humidity.toString()
                    WindSpeed?.text = responseBody.wind.speed.toString()
                    Condition?.text=responseBody.main.pressure.toString()
                    sun_rise?.text=responseBody.sys.sunrise.toString()
                    sun_set?.text=responseBody.sys.sunset.toString()
                    Sea?.text=responseBody.main.sea_level.toString()

                    Log.d("TAG", "onResponse: $temperature")

                }
            }

            override fun onFailure(call: retrofit2.Call<WeatherApp>, t: Throwable) {
                Toast.makeText(this@MainActivity2,"ffyygyuy",Toast.LENGTH_SHORT).show()
                Log.d("TAG", "onResponse: $t")

            }
        })
    }
}