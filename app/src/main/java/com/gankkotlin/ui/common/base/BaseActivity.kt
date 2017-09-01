package com.gankkotlin.ui.common.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.gankkotlin.extension.fixInputMethodManagerLeak

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutId: Int) : AppCompatActivity() {

    val context: Context by lazy { this }
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
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
        fixInputMethodManagerLeak(this)
    }

}