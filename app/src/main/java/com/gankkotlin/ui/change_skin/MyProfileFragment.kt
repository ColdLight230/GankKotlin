package com.gankkotlin.ui.change_skin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gankkotlin.R
import com.gankkotlin.databinding.FragmentMyProfileBinding
import com.gankkotlin.ui.common.widget.TabLazyFragment
import com.gyf.barlibrary.ImmersionBar
import xuliang.me.loader.SkinManager

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/8
 */
class MyProfileFragment : TabLazyFragment<FragmentMyProfileBinding>(R.layout.fragment_my_profile) {
    override fun initData() {
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reset.setOnClickListener {
            SkinManager.getInstance().restoreDefaultTheme()
//            ImmersionBar.with(this).statusBarColor(R.color.colorPrimaryDark).statusBarDarkFont(true).init()
        }
        binding.black.setOnClickListener {
            SkinManager.getInstance().nightMode()
//            ImmersionBar.with(this).statusBarColor(R.color.colorPrimaryDark_night).statusBarDarkFont(false).init()
        }
        binding.brown.setOnClickListener {

        }
        dynamicAddView(binding.picture, "background", R.mipmap.nav_icon)

    }

    companion object {
        fun newInstance() = MyProfileFragment()
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}