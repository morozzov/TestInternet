package com.rmg.testinternet

import android.content.Context
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class HttpRequestUtil(var applicationContext: Context) {
    private var volleyQueue = Volley.newRequestQueue(applicationContext)

    fun makeRequest(url : String, callBackSuccess : (String) -> Unit) {
        var request = StringRequest (
            url,
            callBackSuccess,
            {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        volleyQueue.add(request)
    }

    fun makeExtRequest(
        httpMethod: Int,
        url: String,
        callBackSuccess: (String) -> Unit,
        httpHeaders: MutableMap<String, String>
    ) {
        var request = object : StringRequest(
            httpMethod,
            url,
            callBackSuccess,
            {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return httpHeaders
            }
        }

        volleyQueue.add(request)
    }
}