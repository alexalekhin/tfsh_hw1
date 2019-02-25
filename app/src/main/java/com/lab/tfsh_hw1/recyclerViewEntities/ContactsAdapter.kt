package com.lab.tfsh_hw1.recyclerViewEntities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lab.tfsh_hw1.R

/**
 * Адаптер к RecyclerView для отображения контактов
 */
class ContactsAdapter(arrayList: ArrayList<String>) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {
    private val contacts: ArrayList<String>? = arrayList

    override fun getItemCount() = contacts!!.size

    override fun onBindViewHolder(p0: ContactsAdapter.ContactViewHolder, p1: Int) {
        val str: String? = contacts?.get(p1)
        if (str != null)
            p0.textView?.text = str
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContactsAdapter.ContactViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.contact_view, p0, false)
        return ContactViewHolder(view)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView? = itemView.findViewById(R.id.contact_name)
    }
}