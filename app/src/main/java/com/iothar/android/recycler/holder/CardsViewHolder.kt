package com.iothar.android.recycler.holder

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.R
import com.iothar.android.api.model.Cards
import com.iothar.android.api.model.Sets
import com.iothar.android.recycler.adapter.CardsAdapter

class CardsViewHolder(
    view: View,
    private val _onCardsClickListener: CardsAdapter.OnCardsClickListener
) : RecyclerView.ViewHolder(view) {

    private val _logo = view.findViewById<ImageButton>(R.id.card_image)
    private var _cards: Cards? = null

    init {
        _logo.setOnClickListener {
            if (_cards != null) _onCardsClickListener.onCardsClick(_cards!!)
        }
    }

    fun bind(card: Cards) {
        Glide.with(_logo.context)
            .load(card.images.small)
            .into(_logo)
    }

}