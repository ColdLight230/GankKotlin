package com.gankkotlin.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.gankkotlin.R
import com.gankkotlin.databinding.ActivityMainBinding
import com.gankkotlin.ui.change_skin.MyProfileFragment
import com.gankkotlin.ui.common.base.BaseActivity
import com.gankkotlin.ui.common.widget.TabPagerAdapter
import com.gankkotlin.ui.gank.GankFragment
import com.gankkotlin.ui.localvideos.LocalVideosFragment
import com.gankkotlin.ui.openeyes.OpenEyesFragment


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mAdapter by lazy { TabPagerAdapter(supportFragmentManager) }
    private val mFragments by lazy { arrayOf(GankFragment.newInstance(), OpenEyesFragment.newInstance(), LocalVideosFragment.newInstance(), MyProfileFragment.newInstance()) }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        binding.viewPager.currentItem = item.order
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.title = "首页"

        //这句一定要在下面几句之前调用，不然就会出现点击无反应
        setSupportActionBar(binding.toolbar)
        //ActionBarDrawerToggle配合Toolbar，实现Toolbar上菜单按钮开关效果。
        binding.toolbar.setNavigationIcon(R.drawable.ic_select_all_black_24dp)


        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // 为ViewPager添加页面改变事件
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                // 将当前的页面对应的底部标签设为选中状态
                binding.navigation.selectedItemId = binding.navigation.menu.getItem(position).itemId
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        mAdapter.addFragments(mFragments)
        binding.viewPager.offscreenPageLimit = mAdapter.count
        binding.viewPager.adapter = mAdapter

        dynamicAddView(binding.toolbar, "background", R.color.colorPrimary)
        dynamicAddView(binding.navigation, "background", R.color.colorPrimary)
    }

}
