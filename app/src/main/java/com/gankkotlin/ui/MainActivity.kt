package com.gankkotlin.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.gankkotlin.R
import com.gankkotlin.databinding.ActivityMainBinding
import com.gankkotlin.ui.common.base.BaseActivity
import com.gankkotlin.ui.common.widget.TabPagerAdapter
import com.gankkotlin.ui.document.DocumentFragment
import com.gankkotlin.ui.localvideos.LocalVideosFragment
import com.gankkotlin.ui.openeyes.OpenEyesFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mAdapter by lazy { TabPagerAdapter(supportFragmentManager) }
    private val mFragments by lazy { arrayOf(DocumentFragment.newInstance(), OpenEyesFragment.newInstance(), LocalVideosFragment.newInstance()) }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        binding.viewPager.currentItem = item.order
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mAdapter.addFragments(mFragments)
        binding.viewPager.adapter = mAdapter
    }

}
