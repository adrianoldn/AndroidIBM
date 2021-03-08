package com.example.androidibm.repository

import com.example.testecathodev.network.AppRetrofit
import com.example.androidibm.model.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainRepository (var api: AppRetrofit) {



    fun fetchEvents(callback: (events: List<Event>?)  -> Unit?, callbackErro: (erro: String?) -> Unit?) {
        val fetchEvents = api.testeService().fetchEvents()
        fetchEvents
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ events ->
               callback.invoke(
                   events
               )
            }, { _ ->
                callbackErro.invoke("Erro inesperado, tente novamente!")
            })
    }
}