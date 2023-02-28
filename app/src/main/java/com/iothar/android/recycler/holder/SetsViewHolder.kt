package com.iothar.android.recycler.holder

import android.view.View
import android.widget.ImageButton
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
    private val _logo = view.findViewById<ImageButton>(R.id.set_logo)

    fun bind(sets: Sets) {
        _logo.setOnClickListener { onSetsClickListener.onSetsClick(sets) }
        Glide.with(_logo.context)
            .load(sets.images.logo)
            .into(_logo)
    }

}