package com.iothar.android.graphics.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.R
import com.iothar.android.api.model.Sets

class SetsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(set: Sets) {
        view.findViewById<TextView>(R.id.sets_name).text = set.name
    }
}