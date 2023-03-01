package com.iothar.android.recycler.holder

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.R
import com.iothar.android.api.model.Cards
import com.iothar.android.recycler.adapter.CardsAdapter

class CardsViewHolder(
    view: View,
    private val onCardsClickListener: CardsAdapter.OnCardsClickListener
) : RecyclerView.ViewHolder(view) {

    private val _image = view.findViewById<ImageButton>(R.id.card_image)

    fun bind(cards: Cards) {
        _image.setOnClickListener { onCardsClickListener.onCardsClick(cards) }
        Glide.with(_image.context)
            .load(cards.images.small)
            .into(_image)
    }

}