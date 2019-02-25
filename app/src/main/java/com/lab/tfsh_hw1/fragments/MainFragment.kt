package com.lab.tfsh_hw1.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lab.tfsh_hw1.R
import com.lab.tfsh_hw1.activities.MainActivity


/**
 * Стартовый фрагмент
 */
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        setupButtons(view)
        return view
    }

    private fun setupButtons(view: View) {
        //Получаем кнопку для перехода на основное задание
        val startActivityBtn = view.findViewById<Button>(R.id.start_activity_button)
        //Прикрепляем к ней слушателя
        startActivityBtn.setOnClickListener {
            MainActivity.startAuxiliaryActivityForResult(activity as Activity)
        }

        val eventsBtn = view.findViewById<Button>(R.id.events_button)
        eventsBtn.setOnClickListener {
            (activity as MainActivity).setViewPagerFragment(1)
            if ((activity as MainActivity).result == null) {
                Toast.makeText(activity, "Use \"START AUXILIARY ACTIVITY\" first!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val contactsBtn = view.findViewById<Button>(R.id.contacts_button)
        //Прикрепляем к ней слушателя
        contactsBtn.setOnClickListener {
            (activity as MainActivity).setViewPagerFragment(2)
            if ((activity as MainActivity).result == null) {
                Toast.makeText(activity, "Use \"START AUXILIARY ACTIVITY\" first!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    companion object {
        fun newInstance(): MainFragment =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}

