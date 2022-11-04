package com.example.aicte_app.models

class User {

    var username: String? = null

    var password: String? = null

    var phone:String? = null


    private constructor() {

    }
    constructor(username: String?,password:String?,phone:String?) {
        this.username = username
        this.password = password
        this.phone=phone
    }

}