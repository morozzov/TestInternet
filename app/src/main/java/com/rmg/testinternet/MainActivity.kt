package com.rmg.testinternet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var textViewData: TextView
    private lateinit var buttonLoadData: Button
    private lateinit var httpRequestUtil: HttpRequestUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpRequestUtil = HttpRequestUtil(applicationContext)

        textViewData = findViewById(R.id.textViewData)
        buttonLoadData = findViewById(R.id.buttonLoadData)
//        buttonLoadData.setOnClickListener{
//            httpRequestUtil.makeRequest("https://jsonplaceholder.typicode.com/posts/1",::updateTextViewData)
//        }

        buttonLoadData.setOnClickListener {
            httpRequestUtil.makeExtRequest(
                Request.Method.GET,
                "https://api.weather.yandex.ru/v2/forecast?lat=53.243562&lon=34.363425",
                ::updateTextViewDataWeather,
                hashMapOf("X-Yandex-API-Key" to "984ba2dd-9aa4-411b-9b0e-93439e91d1aa")
            )
        }
    }

    private fun updateTextViewData(data : String) {
        var postData = Gson().fromJson(data, PostData::class.java)
        textViewData.text = "title: ${postData.title}\nbody: ${postData.body}"
    }

    private fun updateTextViewDataWeather(data : String) {
        var weatherData = WeatherData()
        weatherData.parseJson(data)

        textViewData.text = "Сейчас: ${weatherData.date}\n" +
                "температура: ${weatherData.temperature}\n" +
                "погодные условия: ${weatherData.condition}"
    }
}