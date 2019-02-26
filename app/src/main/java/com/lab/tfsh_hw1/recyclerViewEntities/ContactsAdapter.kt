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
class ContactsAdapter(private val contacts: ArrayList<String>) :
    RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(contactLayoutHolder: ContactsAdapter.ContactViewHolder, contactNum: Int) {
        val str: String? = contacts[contactNum]
        if (str != null)
            contactLayoutHolder.textView?.text = str
    }

    override fun onCreateViewHolder(contactLayout: ViewGroup, viewType: Int): ContactsAdapter.ContactViewHolder {
        val view = LayoutInflater
            .from(contactLayout.context)
            .inflate(R.layout.contact_view, contactLayout, false)
        return ContactViewHolder(view)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView? = itemView.findViewById(R.id.contact_name)
    }
}