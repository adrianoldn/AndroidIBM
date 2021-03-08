package com.example.androidibm.presenter

import android.view.View
import com.example.testecathodev.network.AppRetrofit
import com.example.androidibm.contract.MainContract
import com.example.androidibm.repository.MainRepository

class MainPresenter( val view: MainContract.View): MainContract.Presenter {

    val repository = MainRepository(AppRetrofit())

    override fun getEvents() {
        view.showLabelAnswer(View.GONE)
        view.showLoadDialog(View.VISIBLE)

        repository.fetchEvents({
            view.setEventList(it!!)
            view.showLoadDialog(View.GONE)

        },{
            view.showLabelAnswer(View.VISIBLE)
            view.setButtonRetry()
            view.showLoadDialog(View.GONE)
        })

    }
}