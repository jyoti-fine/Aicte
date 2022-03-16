package com.example.aicte_app.daos

import com.google.firebase.firestore.FirebaseFirestore

class CollegeDao {

    private val db= FirebaseFirestore.getInstance()
    val collegeCollections = db.collection("colleges");
}