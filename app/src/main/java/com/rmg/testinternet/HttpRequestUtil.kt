package com.rmg.testinternet

import android.content.Context
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class HttpRequestUtil(var applicationContext: Context) {
    private var volleyQueue = Volley.newRequestQueue(applicationContext)

    fun getRequest(url : String, callBackSuccess : (String) -> Unit) {
        var request = StringRequest (
            url,
            callBackSuccess,
            {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        volleyQueue.add(request)
    }
}