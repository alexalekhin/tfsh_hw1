package com.lab.tfsh_hw1


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import java.util.*

/**
 * Класс, предоставляющий адаптер для заполнения страниц внутри ViewPager.
 * Использует фрагменты для управления каждой страницей.
 */
class StatePagerAdapter internal constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    internal fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
        notifyDataSetChanged()
    }

    internal fun removeFragmentAt(position: Int) {
        fragmentList.removeAt(position)
        fragmentTitleList.removeAt(position)
        notifyDataSetChanged()

    }

    override fun getItem(i: Int) = fragmentList[i]

    override fun getCount() = fragmentList.size

    override fun getItemPosition(`object`: Any) = PagerAdapter.POSITION_NONE
}
