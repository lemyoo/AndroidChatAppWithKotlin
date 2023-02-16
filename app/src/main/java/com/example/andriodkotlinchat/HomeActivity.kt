package com.example.andriodkotlinchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.andriodkotlinchat.models.GeneralResponse
import com.example.andriodkotlinchat.utils.MySharedPreferences
import com.example.andriodkotlinchat.utils.Utility
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView

    lateinit var sharedPreference: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreference = MySharedPreferences()

        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            doLogout()
        }
        return true
    }

    private fun doLogout() {
        val queue = Volley.newRequestQueue(this)
        val url = Utility.apiUrl+"/logout"

        val stringRequest: StringRequest = object : StringRequest(Method.POST,url,Response.Listener { response->
            val generalResponse: GeneralResponse = Gson().fromJson(response, GeneralResponse::class.java)
            Log.i("mylog",generalResponse.status.toString())
            Utility.showAlert(this,"Logout",generalResponse.message, Runnable {
                if(generalResponse.status == "success"){
                    sharedPreference.removeAccessToken(this)
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }
            })
        }, Response.ErrorListener { error ->
            Log.i("mylogerr",error.toString())
        }){
            override fun getHeaders(): MutableMap<String,String>{
                val headers: HashMap<String,String> = HashMap()
                 headers["Authorisation"] = "Bearer "+sharedPreference.getAccessToken(applicationContext)
                return headers
            }
        }
        queue.add(stringRequest)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}