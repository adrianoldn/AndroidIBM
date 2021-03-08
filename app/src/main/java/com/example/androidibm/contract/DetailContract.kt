package com.example.androidibm.contract

import com.example.androidibm.model.Event
import com.example.androidibm.model.User

class DetailContract {
    interface View {
        fun setDetail(event: Event)
        fun showMessage(msg: String)
        fun showload (show: Int)
        fun setEventImage (url: String)
        fun showCheckinDialog ()
        fun goToShare(event: Event)
        fun showErroField()
        fun cancelDialogCheckin()

    }

    interface Presenter {
        fun getEvent(id: String)
        fun doCheckin (user: User)
    }
}