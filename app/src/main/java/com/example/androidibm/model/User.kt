package com.example.androidibm.model

class User (
    val eventId : String,
    val name : String,
    val email: String
){

    override fun toString(): String {
        return "User(eventId='$eventId', name='$name', email='$email')"
    }
}