package com.example.androidibm.contract

import com.example.androidibm.model.Event

class MainContract {

    interface View {
        fun setEventList(events: List<Event>)
        fun showMessage(msg: String)
        fun showLoadDialog(show: Int)
        fun goToLocal (logitude: Double, latitude: Double)
        fun goToDetail(id: String)
        fun showLabelAnswer(show : Int)
        fun setButtonRetry ()
    }

    interface Presenter {
        fun getEvents()
    }
}