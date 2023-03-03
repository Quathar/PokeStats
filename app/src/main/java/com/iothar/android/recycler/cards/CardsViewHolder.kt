package com.iothar.android.recycler.cards

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.R
import com.iothar.android.api.model.Cards

class CardsViewHolder(
    view: View,
    private val onCardsClickListener: CardsAdapter.OnCardsClickListener
) : RecyclerView.ViewHolder(view) {

    // <<-FIELDS->>
    private lateinit var _cards: Cards
    private val _image = view.findViewById<ImageButton>(R.id.card_image_small)

    // <<-METHODS->>
    init {
        _image.setOnClickListener { onCardsClickListener.onCardsClick(_cards) }
    }

    fun bind(cards: Cards) {
        _cards = cards
        Glide.with(_image.context)
            .load(_cards.images.small)
            .into(_image)
    }

}