package com.iothar.android.recycler.sets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.R
import com.iothar.android.api.model.Sets

class SetsAdapter(
    private val setsList: List<Sets>,
    private val onSetsClickListener: OnSetsClickListener
) : RecyclerView.Adapter<SetsViewHolder>() {

    // <<-INTERFACE->>
    interface OnSetsClickListener {
        fun onSetsClick(sets: Sets)
    }

    // <<-METHODS->>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sets, parent, false)
        return SetsViewHolder(view, onSetsClickListener)
    }

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) = holder.bind(setsList[position])

    override fun getItemCount(): Int = setsList.size
}