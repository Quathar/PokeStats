package com.iothar.android.recycler.sets

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.R
import com.iothar.android.api.model.Sets

class SetsViewHolder(
    view: View,
    private val onSetsClickListener: SetsAdapter.OnSetsClickListener
) : RecyclerView.ViewHolder(view) {

    // <<-FIELDS->>
    private lateinit var _sets: Sets
    private val _view   = view.findViewById<CardView>(R.id.set_view)
    private val _logo   = view.findViewById<ImageView>(R.id.set_logo)
    private val _symbol = view.findViewById<ImageView>(R.id.set_symbol)
    private val _name   = view.findViewById<TextView>(R.id.set_name)

    // <<-METHODS->>
    init {
        _view.setOnClickListener { onSetsClickListener.onSetsClick(_sets) }
    }

    fun bind(sets: Sets) {
        _sets = sets
        Glide.with(_logo.context)
            .load(sets.images.logo)
            .into(_logo)
        Glide.with(_symbol.context)
            .load(sets.images.symbol)
            .into(_symbol)
        _name.text = _sets.name
    }

}