package com.example.andriodkotlinchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.andriodkotlinchat.utils.MySharedPreferences
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        Timer().schedule(3000){

            val accessToken: String= MySharedPreferences().getAccessToken(applicationContext)
            if(accessToken.isEmpty()){
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
            }else{
                startActivity(Intent(applicationContext,HomeActivity::class.java))
            }
            finish()
        }
    }
}