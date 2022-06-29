package com.example.volleyconheaders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setup()
    }
    fun setup(){
        // Con Volley
        val textView = findViewById<TextView>(R.id.textView)
        val queue = Volley.newRequestQueue(this)
        val url = "https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList"
        val stringRequest = object: StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var jsonArray = JSONArray(response.toString())
                var finalString: String = ""
                for (i in 0 until jsonArray.length()) {
                    var code = jsonArray.getJSONObject(i).getString("code").toString()
                    var name = jsonArray.getJSONObject(i).getString("name").toString()

                    finalString += " Nombre:$name \n Codigo:$code \n\n\n"
                }
                textView.text = finalString
            },
            Response.ErrorListener {textView.text = "That didn't work!"})
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Public-Merchant-Id"] = "b731f14fb3f64274a1d5411575c624fe"
                return headers
            }
        }
        queue.add(stringRequest)
    }
}