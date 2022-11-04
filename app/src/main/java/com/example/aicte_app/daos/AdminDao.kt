package com.example.aicte_app.daos

import com.example.aicte_app.models.Admin
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdminDao {

    private val db = FirebaseFirestore.getInstance()
    private val adminsCollection = db.collection("admin")  //users collection


}
