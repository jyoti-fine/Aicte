package com.example.aicte_app

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.aicte_app.models.User
import com.example.aicte_app.prevalent.Prevalent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.paperdb.Paper

class UserLogInActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog
    lateinit var checkBox:com.rey.material.widget.CheckBox
    lateinit var parentDbName:String
    lateinit var forgetPassword: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)



        progressDialog = ProgressDialog(this)
        parentDbName="User"

        checkBox =findViewById(R.id.remember_me_chkb)

        forgetPassword=findViewById(R.id.forget_password_link)
        forgetPassword.setOnClickListener{

            val intent = Intent(this@UserLogInActivity, UserResetPasswordActivity::class.java)
            intent.putExtra("check","login")
            startActivity(intent)
            finish()

        }

        Paper.init(this);

        val loginButton: Button =findViewById(R.id.user_login_btn)

        loginButton.setOnClickListener {
            loginUser()
        }

        forgetPassword = findViewById(R.id.forget_password_link)
        forgetPassword.setOnClickListener{

            val intent = Intent(this@UserLogInActivity, UserResetPasswordActivity::class.java)
            intent.putExtra("check","login")
            startActivity(intent)
            finish()

        }

        val adminBtn:TextView=findViewById(R.id.admin)

        adminBtn.setOnClickListener{

            val intent = Intent(this@UserLogInActivity, LogInAdmin::class.java)
            startActivity(intent)
            finish()

        }



    }

    private fun loginUser()
    {
        val userPhoneInput: EditText =findViewById(R.id.user_login_phone)
        val passwordInput:EditText=findViewById(R.id.user_login_password)

        val phone =userPhoneInput.text.toString()
        val password=passwordInput.text.toString()

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_LONG)
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_LONG)
        }
        else
        {
            progressDialog.setTitle("Login Account")
            progressDialog.setMessage("Please wait, while we are checking the credentials.")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            allowAccessToAccount(phone, password)
        }
    }

    private fun allowAccessToAccount(phone: String, password: String)
    {

        if(checkBox.isChecked)
        {

            Paper.book().write(Prevalent.userNameKey,phone)
            Paper.book().write(Prevalent.userPasswordKey,password)
        }

        val rootRef = FirebaseDatabase.getInstance().reference

        rootRef.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {

                    val usersData = dataSnapshot.child(parentDbName).child(phone).getValue(User::class.java)

                    if (usersData != null) {
                        if (usersData.password.equals(password))
                        {
                            if (parentDbName == "User")
                            {
                                Toast.makeText(this@UserLogInActivity, "Logged in Successfully...", Toast.LENGTH_LONG).show()
                                progressDialog.dismiss()

                                val intent = Intent(this@UserLogInActivity, HomeActivity::class.java)
                                Prevalent.currentOnlineUser=usersData
                                startActivity(intent)
                                finish()
                            }


                        }
                        else
                        {                           //password incorrect
                            progressDialog.dismiss()
                            Toast.makeText(this@UserLogInActivity, "Password is incorrect. ", Toast.LENGTH_LONG).show()
                        }
                    }

                }
                else
                {
                    Toast.makeText(this@UserLogInActivity, "The account with this $phone doesn't exist", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()

                }


            }

            override fun onCancelled(error: DatabaseError)
            {

            }


        })
    }
}