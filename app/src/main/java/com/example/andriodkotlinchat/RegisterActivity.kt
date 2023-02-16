package com.example.andriodkotlinchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.andriodkotlinchat.models.GeneralResponse
import com.example.andriodkotlinchat.utils.Utility
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.Charset

class RegisterActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var phone: EditText
    lateinit var password: EditText
    lateinit var register: Button
    lateinit var login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        title = "Register"
        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        password = findViewById(R.id.password)

        login = findViewById(R.id.login)
        login.setOnClickListener{
            startActivity(Intent(Intent(this, LoginActivity::class.java)))
            finish()
        }

        register = findViewById(R.id.register)
        register.setOnClickListener {
            val queue = Volley.newRequestQueue(applicationContext)
            val url: String = Utility.apiUrl+"/registration"
            val requestBody: String ="name="+name.text.toString()+"&phone="+URLEncoder.encode(phone.text.toString(),"UTF-8")+"&password="+password.text.toString()

            val stringRequest: StringRequest = object: StringRequest(Method.POST,url,Response.Listener{response->
               val generalResponse:  GeneralResponse = Gson().fromJson(response,GeneralResponse::class.java)

                Utility.showAlert(this,"Register",generalResponse.message,)
            }, Response.ErrorListener { error->
                Log.i("mylogerr",error.toString())
            }){
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
            queue.add(stringRequest)
        }
    }
}