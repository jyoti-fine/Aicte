package com.example.aicte_app

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.example.aicte_app.models.User
import com.example.aicte_app.prevalent.Prevalent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.paperdb.Paper
import kotlin.math.log

class UserCredentialActivity : AppCompatActivity()
{
    private lateinit var signUpBtn: Button
    private lateinit var logInBtn: Button

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_credential)

        progressDialog = ProgressDialog(this)

        signUpBtn = findViewById(R.id.credential_join_btn)
        logInBtn = findViewById(R.id.credential_login_btn)

        Paper.init(this)

        signUpBtn.setOnClickListener {
            val intent = Intent(this@UserCredentialActivity, UserSignUpActivity::class.java)
            startActivity(intent)
            finish()

        }

        logInBtn.setOnClickListener {
            val intent = Intent(this@UserCredentialActivity, UserLogInActivity::class.java)
            startActivity(intent)
            finish()

        }

        try{
            val userNameKey:String=Paper.book().read(Prevalent.userNameKey)
            val userPasswordKey:String=Paper.book().read(Prevalent.userPasswordKey)

            if(userNameKey!="" && userPasswordKey!="")
            {
                if(!TextUtils.isEmpty(userNameKey) && !TextUtils.isEmpty(userPasswordKey))
                {
                    allowAccess(userNameKey,userPasswordKey)
                    progressDialog.setTitle("Logging...")
                    progressDialog.setMessage("Please wait...")
                    progressDialog.setCanceledOnTouchOutside(false)
                    progressDialog.show()
                }
            }}
        catch (e:NullPointerException)
        {

        }
    }

    private fun allowAccess(username: String, password: String) {
        val rootRef = FirebaseDatabase.getInstance().reference

        rootRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child("User").child(username).exists()) {

                    val usersData = dataSnapshot.child("User").child(username).getValue(User::class.java)

                    if (usersData != null) {
                        if (usersData.password.equals(password)) {

                            progressDialog.dismiss()

                            val intent = Intent(this@UserCredentialActivity, HomeActivity::class.java)
                            Prevalent.currentOnlineUser=usersData
                            startActivity(intent)
                            finish()
                        }
                        else {
                            progressDialog.dismiss()
                            Toast.makeText(this@UserCredentialActivity, "Password is incorrect. ", Toast.LENGTH_LONG).show()
                        }
                    }

                } else {
                    Toast.makeText(this@UserCredentialActivity, "The account with this $username doesn't exist", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()

                }


            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

    }
}