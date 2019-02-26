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
class CalendarAdapter(private val events: ArrayList<String>) : RecyclerView.Adapter<CalendarAdapter.EventViewHolder>() {
    override fun getItemCount() = events.size

    override fun onBindViewHolder(eventLayoutHolder: CalendarAdapter.EventViewHolder, eventNum: Int) {
        val str: String? = events[eventNum]
        if (str != null)
            eventLayoutHolder.textView?.text = str
    }

    override fun onCreateViewHolder(eventLayout: ViewGroup, viewType: Int): CalendarAdapter.EventViewHolder {
        val view = LayoutInflater
            .from(eventLayout.context)
            .inflate(R.layout.event_view, eventLayout, false)
        return EventViewHolder(view)
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView? = itemView.findViewById(R.id.event_name)
    }
}