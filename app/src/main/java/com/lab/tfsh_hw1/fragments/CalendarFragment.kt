package com.lab.tfsh_hw1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lab.tfsh_hw1.R
import com.lab.tfsh_hw1.recyclerViewEntities.CalendarAdapter

/**
 * Фрагмент для отображения событий календаря в виде RecyclerView
 */
class CalendarFragment : Fragment() {
    private var events: ArrayList<String>? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        recyclerView = view.findViewById(R.id.events_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this.activity)
        setupCalendarAdapter()
        if (events != null) {
            view.findViewById<TextView>(R.id.text_no_events).visibility = View.INVISIBLE
        }
        return view

    }

    private fun setupCalendarAdapter() {
        val calendarAdapter = events?.let { CalendarAdapter(it) }
        recyclerView?.adapter = calendarAdapter
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        events = args?.getStringArrayList("events")
    }

    companion object {
        fun newInstance(eventNames: ArrayList<String>?) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("events", eventNames)
                }
            }
    }
}
