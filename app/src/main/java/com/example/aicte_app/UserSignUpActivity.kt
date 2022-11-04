package com.example.aicte_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class UserSignUpActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_sign_up)



        progressDialog = ProgressDialog(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "My Notification",
                "My Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }



        val createAccountButton: Button =findViewById(R.id.user_register_btn)

        createAccountButton.setOnClickListener {
            createAccount()
        }
    }


    private fun createAccount() {
        val usernameInput: EditText =findViewById(R.id.signup_username)
        val username = usernameInput.text.toString()
        val phoneInput : EditText=findViewById(R.id.signup_phone)
        val phone = phoneInput.text.toString()
        val passwordInput : EditText=findViewById(R.id.signup_password)
        val password = passwordInput.text.toString()

        if(username.isNullOrBlank())
        {
            Toast.makeText(this,"Please write your UserName...",Toast.LENGTH_LONG).show()
        }
        else if(phone.isNullOrBlank())
        {
            Toast.makeText(this,"Please write your Phone Number...",Toast.LENGTH_LONG).show()
        }
        else if(phone.length!=10)
        {
            Toast.makeText(this,"Please write a valid Phone Number",Toast.LENGTH_LONG).show()
        }
        else if(password.isNullOrBlank())
        {
            Toast.makeText(this,"Please write your Password...",Toast.LENGTH_LONG).show()
        }
        else
        {

            progressDialog.setTitle("Create Account")
            progressDialog.setMessage("Please wait, while we are checking the credentials.")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            validatephone(username,phone,password)


        }
    }

    private fun validatephone(username: String, phone: String, password: String)
    {
        val rootRef = FirebaseDatabase.getInstance().reference

        rootRef.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (!(dataSnapshot.child("User").child(phone).exists())) {

                    val userDataMap: MutableMap<String, Any> = HashMap()
                    userDataMap["username"] = username
                    userDataMap["phone"] = phone
                    userDataMap["password"] = password


                    rootRef.child("User").child(phone).updateChildren(userDataMap)
                        .addOnSuccessListener {

                            Toast.makeText(this@UserSignUpActivity, "Congratulations, your account is created successfully", Toast.LENGTH_LONG).show()




                            progressDialog.dismiss()

                            val builder = NotificationCompat.Builder(this@UserSignUpActivity, "My Notification")
                            builder.setContentTitle("THANKS FOR ENROLLING IN AICTE")
                            val message:String=getString(R.string.notification_message, username)
                            builder.setContentText(message)
                            builder.setSmallIcon(android.R.drawable.btn_star_big_on)
                            builder.setAutoCancel(true)
                            val managerCompat = NotificationManagerCompat.from(this@UserSignUpActivity)
                            managerCompat.notify(1, builder.build())

                            val intent = Intent(this@UserSignUpActivity, UserLogInActivity::class.java)
                            startActivity(intent)
                            finish()


                        }.addOnFailureListener {
                            progressDialog.dismiss()
                            Toast.makeText(this@UserSignUpActivity, "Something went gone. Please try again", Toast.LENGTH_LONG).show()      //12.29


                        }
                }
                else {

                    progressDialog.dismiss()
                    Toast.makeText(this@UserSignUpActivity, "This phone number $phone already exist", Toast.LENGTH_LONG).show()


                    val intent = Intent(this@UserSignUpActivity, UserCredentialActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


    }


}