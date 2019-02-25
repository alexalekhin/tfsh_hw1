package com.lab.tfsh_hw1.recyclerViewEntities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lab.tfsh_hw1.R

/**
 * Адаптер к RecyclerView для отображения событий календаря
 */
class CalendarAdapter(arrayList: ArrayList<String>) : RecyclerView.Adapter<CalendarAdapter.EventViewHolder>() {
    private val events: ArrayList<String>? = arrayList

    override fun getItemCount() = events!!.size

    override fun onBindViewHolder(p0: CalendarAdapter.EventViewHolder, p1: Int) {
        val str: String? = events?.get(p1)
        if (str != null)
            p0.textView?.text = str
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CalendarAdapter.EventViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.event_view, p0, false)
        return EventViewHolder(view)
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView? = itemView.findViewById(R.id.event_name)
    }
}