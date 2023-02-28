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
    private val _onSetsClickListener: SetsAdapter.OnSetClickListener
) : RecyclerView.ViewHolder(view) {

    // <<-FIELDS->>
    private val _logo = view.findViewById<ImageButton>(R.id.set_logo)
    private var _sets: Sets? = null

    init {
        _logo.setOnClickListener {
            if (_sets != null) _onSetsClickListener.onSetsClick(_sets!!)
        }
    }

    fun bind(sets: Sets) {
        _sets = sets
        Glide.with(_logo.context)
            .load(_sets!!.images.logo)
            .into(_logo)
    }

}