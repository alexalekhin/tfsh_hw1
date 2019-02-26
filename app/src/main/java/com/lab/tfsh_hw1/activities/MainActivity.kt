package com.lab.tfsh_hw1.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.lab.tfsh_hw1.DataContractParcelable
import com.lab.tfsh_hw1.R
import com.lab.tfsh_hw1.StatePagerAdapter
import com.lab.tfsh_hw1.fragments.CalendarFragment
import com.lab.tfsh_hw1.fragments.ContactsFragment
import com.lab.tfsh_hw1.fragments.MainFragment
import com.lab.tfsh_hw1.fragments.OnFragmentInteractionListener

/**
 * Основная Activity, в которой отображаются результаты работы сервиса
 */
class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
    private var viewPager: ViewPager? = null
    private var viewPagerAdapter: StatePagerAdapter? = null
    var result: DataContractParcelable? = null
    // Массив необходимых разрешений, которые будут запрошены у пользователя
    private val permissions = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALENDAR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.viewPager = findViewById(R.id.base_container)
        // Проверяем возможность восстановить результаты работы, если они доступны
        if (savedInstanceState?.getParcelable<DataContractParcelable>("result") == null) {
            setupViewPager(this.viewPager)
        } else {
            restoreViewPager(this.viewPager, savedInstanceState)
        }
        if (!(checkPermission(this, Manifest.permission.READ_CONTACTS)
                    && checkPermission(this, Manifest.permission.READ_CALENDAR))
        ) { // нет разрешений
            requestPermission(this, permissions, REQUEST_RUNTIME_PERMISSION)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("result", result)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = StatePagerAdapter(supportFragmentManager).apply {
            addFragment(MainFragment.newInstance(), "MainFragment")
            addFragment(CalendarFragment.newInstance(null), "CalendarFragment")
            addFragment(ContactsFragment.newInstance(null), "ContactsFragment")
        }
        viewPager?.adapter = adapter
        viewPagerAdapter = adapter
    }

    /**
     * Восстанавливаем вёрстку по ранее полученным данным
     */
    private fun restoreViewPager(viewPager: ViewPager?, savedInstanceState: Bundle?) {
        this.result = savedInstanceState?.getParcelable("result")
        val adapter = StatePagerAdapter(supportFragmentManager).apply {
            addFragment(MainFragment.newInstance(), "MainFragment")
            addFragment(CalendarFragment.newInstance(result?.eventNames as ArrayList<String>?), "CalendarFragment")
            addFragment(ContactsFragment.newInstance(result?.contactNames as ArrayList<String>?), "ContactsFragment")
        }
        viewPager?.adapter = adapter
        viewPagerAdapter = adapter
    }

    override fun setViewPagerFragment(fragmentNum: Int) {
        this.viewPager?.currentItem = fragmentNum
        if (result == null) {
            Toast.makeText(this, "Use \"START AUXILIARY ACTIVITY\" first!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onStartButtonClicked() {
        startAuxiliaryActivityForResult(this)
    }

    /**
     * Выполняем действия с результатами из вспомогательной Activity
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CONFIRMATION_REQUEST_CODE -> when (resultCode) {
                Activity.RESULT_OK -> {
                    result = data?.getParcelableExtra(CONFIRM_DATA)
                    viewPagerAdapter?.removeFragmentAt(2)
                    viewPagerAdapter?.removeFragmentAt(1)
                    // Заполняем  календарь
                    viewPagerAdapter
                        ?.addFragment(
                            CalendarFragment.newInstance(result?.eventNames as ArrayList<String>?),
                            "CalendarFragment"
                        )
                    // Заполняем  контакты
                    viewPagerAdapter
                        ?.addFragment(
                            ContactsFragment.newInstance(result?.contactNames as ArrayList<String>?),
                            "ContactsFragment"
                        )
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(
                        this,
                        "Service wasn't started! Data isn't updated.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_RUNTIME_PERMISSION) {
            if (!(grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            ) {
                Toast.makeText(
                    this,
                    "Permissions for CALENDAR and CONTACTS are not granted!\nCheck application settings.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }

    private fun requestPermission(thisActivity: Activity, Permission: Array<String>, Code: Int) {
        if (ContextCompat.checkSelfPermission(thisActivity, Permission[0]) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(thisActivity, Permission[1]) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(thisActivity, Permission, Code)
    }

    private fun checkPermission(
        context: Context,
        Permission: String
    ) = ContextCompat.checkSelfPermission(context, Permission) == PackageManager.PERMISSION_GRANTED

    companion object {
        // Строка для извлечения резлуьтатов работы
        const val CONFIRM_DATA = "data"
        // Код запроса данных от вспомогательной  Activity
        private const val CONFIRMATION_REQUEST_CODE = 0
        private const val REQUEST_RUNTIME_PERMISSION = 123

        /**
         * Метод запуска вспомогательной Activity с ожиданием результата
         */
        fun startAuxiliaryActivityForResult(activity: Context) {
            val intent = Intent(activity, AuxiliaryActivity::class.java)
            (activity as Activity).startActivityForResult(intent, CONFIRMATION_REQUEST_CODE)
        }
    }
}
