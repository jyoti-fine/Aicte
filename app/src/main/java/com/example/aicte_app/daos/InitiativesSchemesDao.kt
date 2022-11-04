package com.example.aicte_app.daos

import com.google.firebase.firestore.FirebaseFirestore

class InitiativesSchemesDao {

    private val db= FirebaseFirestore.getInstance()
    val intiative_SchemeCollections = db.collection("initiatives_schemes");
}