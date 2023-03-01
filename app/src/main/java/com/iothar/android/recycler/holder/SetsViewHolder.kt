package com.iothar.android.recycler.holder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.R
import com.iothar.android.api.model.Sets
import com.iothar.android.recycler.adapter.SetsAdapter

class SetsViewHolder(
    view: View,
    private val onSetsClickListener: SetsAdapter.OnSetsClickListener
) : RecyclerView.ViewHolder(view) {

    // <<-FIELDS->>
    private val _view = view.findViewById<CardView>(R.id.set_view)
    private val _logo = view.findViewById<ImageView>(R.id.set_logo)
    private val _symbol = view.findViewById<ImageView>(R.id.set_symbol)
    private val _name = view.findViewById<TextView>(R.id.set_name)

    fun bind(sets: Sets) {
        _view.setOnClickListener { onSetsClickListener.onSetsClick(sets) }
        Glide.with(_logo.context)
            .load(sets.images.logo)
            .into(_logo)
        Glide.with(_symbol.context)
            .load(sets.images.symbol)
            .into(_symbol)
        _name.text = sets.name
    }

}