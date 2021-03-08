package com.example.androidibm.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidibm.R
import com.example.androidibm.adapter.EventAdapter
import com.example.androidibm.contract.MainContract
import com.example.androidibm.model.Event
import com.example.androidibm.presenter.MainPresenter
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {


    lateinit var presenter: MainPresenter

    val adapterEvent by lazy {
        EventAdapter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeader()
        presenter = MainPresenter(this)
        presenter.getEvents()
    }

    private fun setHeader() {
        title = "Eventos"
    }


    override fun setEventList(events: List<Event>) {
        setupEventRecycler()
        adapterEvent.setList(events)
        setupButtonsClick()
    }

    private fun setupButtonsClick() {
        adapterEvent.apply {
            itemListener = object : EventAdapter.itemClickListener {
                override fun itemClick(
                    event: Event,
                    botaoLocal: Button,
                    botaoDetalhe: MaterialButton
                ) {
                    botaoLocal.setOnClickListener {
                        goToLocal(event.longitude!!, event.latitude!!)
                    }
                    botaoDetalhe.setOnClickListener {
                        goToDetail(event.id!!)
                    }
                }
            }
        }
    }

    private fun setupEventRecycler() {
        rv_events.setHasFixedSize(true)
        rv_events.layoutManager = LinearLayoutManager(this)
        rv_events.adapter = adapterEvent
    }


    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showLoadDialog(show: Int) {
        load_main.visibility = show

    }

    override fun goToLocal(longitude: Double, latitude: Double) {
        val uri =
            "http://maps.google.com/maps?daddr=$latitude,$longitude (Where the event is at)"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    override fun goToDetail(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idEvent", id)
        startActivity(intent)
    }

    override fun showLabelAnswer(show: Int) {
        label_answer.visibility = show
        button_retry.visibility = show
    }

    override fun setButtonRetry() {
       button_retry.setOnClickListener {
           presenter.getEvents()
       }
    }


}