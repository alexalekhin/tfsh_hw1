package com.lab.tfsh_hw1.activities

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.lab.tfsh_hw1.DataContractParcelable
import com.lab.tfsh_hw1.R
import com.lab.tfsh_hw1.services.SimpleIntentService

/**
 * Вспомогательная Activity, порождающая Service и получающая из него результаты
 */
class AuxiliaryActivity : AppCompatActivity() {

    private var dataReceiver: DataReceiver? = DataReceiver()

    private inner class DataReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            var contactsInfo: ArrayList<String>? = null
            var eventsInfo: ArrayList<String>? = null
            if (intent.hasExtra("contacts")) {
                contactsInfo = intent.getStringArrayListExtra("contacts")

            }
            if (intent.hasExtra("events")) {
                eventsInfo = intent.getStringArrayListExtra("events")
            }

            onResult(DataContractParcelable(contactsInfo!!, eventsInfo!!))
        }
    }


    override fun onStart() {
        super.onStart()
        registerEventsReceiver()

    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(dataReceiver!!)
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auxiliary)
        setupButtons()
    }

    private fun setupButtons() {
        val startServiceBtn = findViewById<Button>(R.id.start_intent_service)
        startServiceBtn.setOnClickListener {
            startService()
        }
    }

    private fun startService() {
        val intent = Intent()
        intent.setClass(this, SimpleIntentService::class.java)
        startService(intent)
    }

    fun onResult(data: DataContractParcelable) {
        val intent = Intent().putExtra(MainActivity.CONFIRM_DATA, data)
        setResult(Activity.RESULT_OK, intent)
        //Возвращемся в основную Activity
        finish()
    }

    private fun registerEventsReceiver() {
        dataReceiver = DataReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(SimpleIntentService.DATA_INFO)
        LocalBroadcastManager.getInstance(this).registerReceiver(dataReceiver!!, intentFilter)
    }
}
