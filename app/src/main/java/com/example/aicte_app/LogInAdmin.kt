package com.example.aicte_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.ProgressBar
//import com.example.aicte_app.daos.AdminDao
//import com.example.aicte_app.models.Admin
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.SignInButton
//import com.google.android.gms.common.api.ApiException
//import com.google.android.gms.tasks.Task
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.GoogleAuthProvider
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.firestore.auth.User
//import com.google.firebase.ktx.Firebase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.tasks.await
//import kotlinx.coroutines.withContext

class LogInAdmin : AppCompatActivity() {

    private lateinit var adminUid:EditText
    private lateinit var adminPass:EditText
    private lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_admin)

        adminUid=findViewById(R.id.editUID)
        adminPass=findViewById(R.id.editPass)
        btn=findViewById(R.id.sub)

         btn.setOnClickListener{
             if(adminUid.text.toString()=="989" && adminPass.text.toString()=="***")
             {
                 val intent = Intent(this@LogInAdmin, HomeActivity::class.java)
                 startActivity(intent)
             }
             else
             {
                 Toast.makeText(this,"Wrong Credentials Of Admin",Toast.LENGTH_LONG).show()
             }

         }




    }
}

