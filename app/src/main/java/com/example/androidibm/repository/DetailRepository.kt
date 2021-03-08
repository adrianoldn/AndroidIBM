package com.example.androidibm.repository

import com.example.testecathodev.network.AppRetrofit
import com.example.androidibm.model.Event
import com.example.androidibm.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailRepository(val api: AppRetrofit) {

    fun getEvent(
        id: String,
        callbackResponse: (event: Event) -> Unit,
        callbackErro: (erro: String) -> Unit
    ) {
        val fetchEvent = api.testeService().fetchEvent(id)
        fetchEvent
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callbackResponse.invoke(it)
            }, {
                callbackErro.invoke("erro na requisição de evento")
            })

    }

    fun doCheckin(user: User, callbackResponse: (codigo: String) -> Unit){
        val checkin = api.testeService().checkin(user)
        checkin
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callbackResponse.invoke(it.code)
            },{
                callbackResponse.invoke(it.message!!)
            })
    }
}