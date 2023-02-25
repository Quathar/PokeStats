package com.iothar.android.graphics

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.R
import com.iothar.android.SetsAdapter
import com.iothar.android.api.model.Sets


class SetsViewHolder(
    itemView: View,
    onSetsClickListener: SetsAdapter.OnSetsClickListener
) : RecyclerView.ViewHolder(itemView) {

    // <<-FIELDS->>
    private val _setsNameTextView = itemView.findViewById<TextView>(R.id.sets_name)
    private val _onSetsClickListener = onSetsClickListener
    private var _sets: Sets? = null

    init {
        _setsNameTextView.setOnClickListener {
            if (_sets != null) _onSetsClickListener.onSetsClick(_sets!!)
        }
    }

    // <<-METHOD->>
    fun bind(sets: Sets) {
        _sets = sets
        _setsNameTextView.text = _sets!!.name
    }

}
