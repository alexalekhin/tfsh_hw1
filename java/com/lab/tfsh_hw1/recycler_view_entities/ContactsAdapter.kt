package com.lab.tfsh_hw1.recycler_view_entities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lab.tfsh_hw1.R

/**
 * Адаптер к RecyclerView для отображения контактов
 */
class ContactsAdapter(arrayList: ArrayList<String>) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    private var contacts: ArrayList<String>? = arrayList

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = itemView.findViewById(R.id.contact_name)

    }

    override fun getItemCount(): Int {
        return contacts!!.size
    }

    override fun onBindViewHolder(p0: ContactsAdapter.ContactViewHolder, p1: Int) {
        val str: String? = contacts?.get(p1)
        if (str != null)
            p0.textView?.text = str
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContactsAdapter.ContactViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.contact_view, p0, false)
        return ContactViewHolder(view)
    }

}