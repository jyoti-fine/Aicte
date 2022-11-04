package com.example.aicte_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class AboutActivity : AppCompatActivity() {

    // Button insta;
    private lateinit var img: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var nam: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        img = findViewById(R.id.imageView2);
        img2 = findViewById(R.id.imageView);
        img3 = findViewById(R.id.imageView3);
        nam = findViewById(R.id.name);


        img.setOnClickListener{
            gotoUrl("https://jecrcfoundation.com/");
        }

        img2.setOnClickListener{
            gotoUrl("https://jecrcfoundation.com/");
        }

        img3.setOnClickListener{
            gotoUrl("https://jecrcfoundation.com/");
        }

        nam.setOnClickListener {
            gotoUrl("https://www.youtube.com/")
        }
    }

    private fun gotoUrl(s: String) {
        val uri: Uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}