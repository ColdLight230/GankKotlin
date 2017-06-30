package com.gankkotlin.ui.common.widget

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/30
 */
class TabPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val mFragments: MutableList<Fragment> by lazy { ArrayList<Fragment>() }
    val mTitles: MutableList<String> by lazy { ArrayList<String>() }

    override fun getCount(): Int = mFragments.size

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getPageTitle(position: Int): CharSequence? = mTitles[position]
    
    fun addFragments(fragments: Array<out Fragment>) = mFragments.addAll(fragments)

    fun addTitles(titles: Array<out String>) = mTitles.addAll(titles)

}