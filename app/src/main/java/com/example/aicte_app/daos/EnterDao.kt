package com.example.aicte_app.daos

import com.google.firebase.firestore.FirebaseFirestore

class EnterDao {

    private val db= FirebaseFirestore.getInstance()
    val enterCollections = db.collection("enterpreneur grant for");
}