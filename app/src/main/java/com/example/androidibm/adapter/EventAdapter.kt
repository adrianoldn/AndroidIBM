package com.example.androidibm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidibm.R
import com.example.androidibm.model.Event
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.item_event.view.*

class EventAdapter(val context: Context) : RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    private var eventList : MutableList<Event> = mutableListOf()
    var itemListener: itemClickListener? = null

    interface itemClickListener {
        fun itemClick(event:Event, botaoLocal: Button, botaoDetalhe: MaterialButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view, context)
    }

    override fun getItemCount(): Int {
      return eventList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]
        holder.bindView(event)
        holder.let {
            itemListener?.itemClick(event, it.itemView.button_local, it.itemView.button_detail)
        }
    }

    fun setList(event: List<Event>) {
        this.eventList.clear()
        this.eventList.addAll(event)
        notifyDataSetChanged()

    }


    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        fun bindView (event: Event){
            Glide.with(context)
                .load(event.image)
                .into(itemView.eventPhoto);
            val titulo = itemView.event_title
            val price = itemView.event_price
            titulo.text = event.title
            price.text = "R$ ${event.price}"
        }
    }


}