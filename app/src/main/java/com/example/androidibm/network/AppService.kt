package com.example.testecathodev.network

import com.example.androidibm.model.CheckinResponse
import com.example.androidibm.model.Event
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*


interface AppService {

    @GET("events")
    fun fetchEvents(): Observable<List<Event>>

    @GET("events/{userId}")
    fun fetchEvent(
        @Path("userId") userId: String
    ): Observable<Event>

    @POST("checkin")
    fun checkin(
        @Body user: Any
    ): Observable<CheckinResponse>






}