package com.example.cryptoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class connectionErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.connection_error_layout)
        supportActionBar?.hide()




        var connectionimage = findViewById<ImageView>(R.id.imageconnection)

        var textViewOops = findViewById<TextView>(R.id.textViewConnection)

        var detailsconnection = findViewById<TextView>(R.id.details)

        var restartbutton = findViewById<Button>(R.id.restart)



        restartbutton.setOnClickListener(){
            var intentMain = Intent(this, SplashScreenActivity::class.java)
            startActivity(intentMain)
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            finish()

        }
    }
}