package com.example.aicte_app.daos

import com.google.firebase.firestore.FirebaseFirestore

class RulesRegulationsDao {
    private val db= FirebaseFirestore.getInstance()
    val rulesRegulationsCollections = db.collection("RulesAndRegulations");
}