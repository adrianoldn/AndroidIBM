package com.example.androidibm.presenter

import android.view.View
import com.example.testecathodev.network.AppRetrofit
import com.example.androidibm.contract.DetailContract
import com.example.androidibm.model.User
import com.example.androidibm.repository.DetailRepository

class DetailPresenter(val view: DetailContract.View) : DetailContract.Presenter {

    val repository = DetailRepository(AppRetrofit())
    lateinit var user: User

    override fun getEvent(id: String) {
        view.showload(View.VISIBLE)
        repository.getEvent(id, {
            view.setDetail(it)
        }, {
            view.showMessage(it)
        })
        view.showload(View.GONE)
    }

    override fun doCheckin(user: User) {

        if (!user.name.isNullOrBlank() && !user.email.isNullOrBlank()) {
            repository.doCheckin(
                user
            ) {
                if (it == "200") {
                    view.showMessage("Checkin Realizado!")
                } else {
                    view.showMessage("Erro Inesperado, tente novamente!")
                }

            }
            view.cancelDialogCheckin()
        }else{
            view.showErroField()
        }
    }

}