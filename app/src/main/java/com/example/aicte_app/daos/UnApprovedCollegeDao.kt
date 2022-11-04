package com.example.aicte_app.daos

import com.google.firebase.firestore.FirebaseFirestore

class UnApprovedCollegeDao {

    private val db= FirebaseFirestore.getInstance()
    val unApprovedCollegeCollections = db.collection("unapproved_colleges");
}