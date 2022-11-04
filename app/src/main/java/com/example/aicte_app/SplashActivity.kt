package com.example.aicte_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val iHome = Intent(this@SplashActivity, UserCredentialActivity::class.java)
        Handler().postDelayed({ startActivity(iHome) }, 1800)
    }
}