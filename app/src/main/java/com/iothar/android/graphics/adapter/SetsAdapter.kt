package com.iothar.android.graphics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.R
import com.iothar.android.api.model.Sets
import com.iothar.android.graphics.holder.SetsViewHolder

class SetsAdapter(private val sets: List<Sets>) : RecyclerView.Adapter<SetsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sets, parent, false)
        return SetsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) {
        holder.bind(sets[position])
    }

    override fun getItemCount(): Int = sets.size
}