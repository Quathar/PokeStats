package com.iothar.android.recycler.holder

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.R
import com.iothar.android.api.model.Sets

class SetsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val logo = view.findViewById<ImageButton>(R.id.set_logo)

    fun bind(set: Sets) {
        Glide.with(logo.context)
            .load(set.images.logo)
            .into(logo)
    }
}