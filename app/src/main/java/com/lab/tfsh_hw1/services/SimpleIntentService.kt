package com.lab.tfsh_hw1.services

import android.app.IntentService
import android.content.Intent
import android.provider.CalendarContract
import android.provider.ContactsContract
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.lab.tfsh_hw1.DataContractParcelable


class SimpleIntentService : IntentService("SimpleIntentService") {

    override fun onHandleIntent(intent: Intent?) = sendData(DataContractParcelable(getContacts(), getEvents()))

    private fun sendData(data: DataContractParcelable?) {
        val intent = Intent(DATA_INFO)
        intent.putExtra("contacts", data!!.contactNames)
        intent.putExtra("events", data.eventNames)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun getContacts(): ArrayList<String>? {

        val projection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY

        val c = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(projection),
            null,
            null,
            null
        )
        return if (c != null) {
            val result = ArrayList<String>()
            c.moveToFirst()
            while (c.moveToNext()) {
                result.add(c.getString(c.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)))
                c.moveToNext()
            }
            c.close()
            result
        } else {
            null
        }
    }

    private fun getEvents(): ArrayList<String>? {

        val projection = CalendarContract.Events.TITLE

        val c = contentResolver.query(
            CalendarContract.Events.CONTENT_URI,
            arrayOf(projection),
            null,
            null,
            null
        )
        return if (c != null) {
            val result = ArrayList<String>()
            c.moveToFirst()
            while (c.moveToNext()) {
                result.add(c.getString(c.getColumnIndex(CalendarContract.Events.TITLE)))
                c.moveToNext()
            }
            c.close()
            result

        } else {
            null
        }
    }

    companion object {
        const val DATA_INFO = "data"
    }


}
