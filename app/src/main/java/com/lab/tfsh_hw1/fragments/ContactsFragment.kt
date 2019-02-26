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
import com.lab.tfsh_hw1.recyclerViewEntities.ContactsAdapter

/**
 * Фрагмент для отображения контактов в виде RecyclerView
 */
class ContactsFragment : Fragment() {
    private var contacts: ArrayList<String>? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)

        recyclerView = view.findViewById(R.id.contacts_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this.activity)
        setupContactAdapter()
        if (contacts != null) {
            view.findViewById<TextView>(R.id.text_no_contacts).visibility = View.INVISIBLE
        }
        return view
    }

    private fun setupContactAdapter() {
        val contactsAdapter = contacts?.let { ContactsAdapter(it) }
        recyclerView?.adapter = contactsAdapter
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        contacts = args?.getStringArrayList("contacts")
    }

    companion object {
        fun newInstance(contactNames: ArrayList<String>?) =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("contacts", contactNames)
                }
            }
    }
}
