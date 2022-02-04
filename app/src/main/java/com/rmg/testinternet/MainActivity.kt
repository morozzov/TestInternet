package com.rmg.testinternet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson

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
        buttonLoadData.setOnClickListener{
            httpRequestUtil.getRequest("https://jsonplaceholder.typicode.com/posts/1",::updateTextViewData)
        }
    }

    private fun updateTextViewData(data : String) {
        var postData = Gson().fromJson(data, PostData::class.java)
        textViewData.text = "title: ${postData.title}\nbody: ${postData.body}"
    }

}