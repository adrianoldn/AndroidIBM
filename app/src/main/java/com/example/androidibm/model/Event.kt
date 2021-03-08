package com.example.androidibm.model

class Event(

    val people: List<Any>?,
    val date:Int?,
    val description: String?,
    val longitude : Double?,
    val latitude :Double?,
    val image: String?,
    val price : Double?,
    val title :String?,
    val id : String?
) {

    override fun toString(): String {
        return "Event(people=$people, date=$date, description=$description, longitude=$longitude, latitude=$latitude, image=$image, price=$price, title=$title, id=$id)"
    }
}