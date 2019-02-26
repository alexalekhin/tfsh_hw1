package com.lab.tfsh_hw1.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.lab.tfsh_hw1.R


/**
 * Стартовый фрагмент
 */
class MainFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        setupButtons(view)
        return view
    }

    private fun setupButtons(view: View) {
        // Получаем кнопку для перехода на основное задание
        val startActivityBtn = view.findViewById<Button>(R.id.start_activity_button)
        // Прикрепляем к ней слушателя
        startActivityBtn.setOnClickListener {
            listener?.onStartButtonClicked()
        }

        val eventsBtn = view.findViewById<Button>(R.id.events_button)
        eventsBtn.setOnClickListener {
            listener?.setViewPagerFragment(1)
        }

        val contactsBtn = view.findViewById<Button>(R.id.contacts_button)
        // Прикрепляем к ней слушателя
        contactsBtn.setOnClickListener {
            listener?.setViewPagerFragment(2)

        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        fun newInstance(): MainFragment =
            MainFragment()
    }
}

