package com.example.androidibm.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.androidibm.R
import com.example.androidibm.contract.DetailContract
import com.example.androidibm.databinding.ActivityDetailBinding
import com.example.androidibm.model.Event
import com.example.androidibm.model.User
import com.example.androidibm.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.dialog_checkin.view.*


class DetailActivity : AppCompatActivity(), DetailContract.View {

    private var inflate: View? = null
    lateinit var binding: ActivityDetailBinding
    lateinit var presenter: DetailPresenter
    lateinit var dialog: androidx.appcompat.app.AlertDialog
    lateinit var idEvent: String
    lateinit var event: Event


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setHeader()

        idEvent = intent.extras?.get("idEvent") as String
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        presenter = DetailPresenter(this)
        presenter.getEvent(idEvent)
    }

    private fun setHeader() {
        title = "Detalhe"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun setDetail(event: Event) {
        binding.event = event
        this.event = event
        setEventImage(event.image!!)
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showload(show: Int) {
        progress_detail.visibility = show
    }

    override fun setEventImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(image_event_detail)
    }

    override fun showCheckinDialog() {
        inflate = layoutInflater
            .inflate(R.layout.dialog_checkin, window.decorView as ViewGroup, false)
        dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setView(inflate)
            .create()
        inflate!!.button_send_checkin.setOnClickListener {
            presenter.doCheckin(
                User(
                    idEvent,
                    inflate!!.field_name.text.toString(),
                    inflate!!.field_email.text.toString()
                )
            )
        }
        dialog.show()

    }


    override fun goToShare(event: Event) {
        val intent = Intent(Intent.ACTION_SEND)
        val shareBody =
            "Evento: ${event.title}\nDescrição: ${event.description}\nPreço: ${event.price}"
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_SUBJECT,
            getString(R.string.share_subject)
        )
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(intent, getString(R.string.share_using)))
    }

    override fun showErroField() {
        inflate!!.field_name.error = "Preencha o campo nome"
        inflate!!.field_email.error = "Preencha o campo e-mail"
    }

    override fun cancelDialogCheckin() {
        dialog.cancel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_checkin -> {
                showCheckinDialog()
            }
            R.id.navigation_shared -> {
                goToShare(event)

            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}