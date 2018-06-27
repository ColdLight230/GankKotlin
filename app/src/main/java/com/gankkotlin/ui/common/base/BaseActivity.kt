package com.gankkotlin.ui.common.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import com.gankkotlin.R
import com.gankkotlin.extension.fixInputMethodManagerLeak
import com.gyf.barlibrary.ImmersionBar
import xuliang.me.base.SkinBaseActivity
import xuliang.me.loader.SkinManager

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) : SkinBaseActivity() {

    val context: Context by lazy { this }
    lateinit var binding: B
    private val immersionBar: ImmersionBar by lazy { ImmersionBar.with(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)

        if (SkinManager.getInstance().isNightMode) {
            SkinManager.getInstance().nightMode()
            immersionBar.statusBarColor(R.color.colorPrimaryDark_night).statusBarDarkFont(false).init()
        } else {
            SkinManager.getInstance().restoreDefaultTheme()
            immersionBar.statusBarColor(R.color.colorPrimaryDark).statusBarDarkFont(true).init()
        }
        immersionBar.init()
    }

    fun replaceFragment(fragment: Fragment, @IdRes containerId: Int, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
//        fixHWInputMethodManagerLeak(this)
        immersionBar.destroy()
        fixInputMethodManagerLeak(this)
    }

}