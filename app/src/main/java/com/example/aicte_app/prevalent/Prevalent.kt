package com.example.aicte_app.prevalent

import com.example.aicte_app.models.User

class Prevalent {

    constructor() {
    }

    companion object {

        lateinit var currentOnlineUser: User
        val userNameKey = "UserName"
        val userPasswordKey = "UserPassword"
        val userPhoneKey = "UserEmail"

    }

}
