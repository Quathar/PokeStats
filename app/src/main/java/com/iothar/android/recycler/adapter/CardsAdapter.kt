package com.iothar.android.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.R
import com.iothar.android.api.model.Cards
import com.iothar.android.recycler.holder.CardsViewHolder

class CardsAdapter(
    private val cardsList: List<Cards>,
    private val onCardsClickListener: OnCardsClickListener
) : RecyclerView.Adapter<CardsViewHolder>() {

    // <<-INTERFACE->>
    interface OnCardsClickListener {
        fun onCardsClick(cards: Cards)
    }

    // <<-OVERRIDE->>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cards, parent, false)
        return CardsViewHolder(view, onCardsClickListener)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) = holder.bind(cardsList[position])

    override fun getItemCount(): Int = cardsList.size

}