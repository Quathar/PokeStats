package com.iothar.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.api.model.Sets
import com.iothar.android.graphics.SetsViewHolder

class SetsAdapter(
    setsList: List<Sets>,
    onSetsClickListener: OnSetsClickListener
) : RecyclerView.Adapter<SetsViewHolder>() {

    // <<-INTERFACE->>
    interface OnSetsClickListener {
        fun onSetsClick(sets: Sets)
    }

    // <<-FIELDS->>
    private val _setsList = setsList
    private val _onSetsClickListener = onSetsClickListener

    // <<-OVERRIDE->>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_sets_item, parent, false)
        return SetsViewHolder(
            view,
            _onSetsClickListener
        )
    }

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) {
        holder.bind(_setsList[position])
    }

    override fun getItemCount(): Int {
        return _setsList.size
    }

}