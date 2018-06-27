package com.gankkotlin.ui.common.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gankkotlin.extension.toast
import xuliang.me.base.SkinBaseFragment
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/8
 */
abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes val layoutId: Int) : SkinBaseFragment(), IBaseView {

    lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : Fragment> checkAndAddFragment(trans: FragmentTransaction,
                                                     containerId: Int,
                                                     tag: String,
                                                     fragmentClass: Class<T>,
                                                     args: Bundle? = null): T {

        val className = fragmentClass.canonicalName

        var fragment = childFragmentManager.findFragmentByTag(tag) as T?
        if (fragment?.isDetached ?: true) {
            fragment = Fragment.instantiate(activity, className, args) as T

            trans.add(containerId, fragment, tag)
        }

        return fragment!!
    }

    override fun onError(throwable: Throwable) {
        when (throwable) {
            is SocketTimeoutException -> toast("服务器连接超时")
            is ConnectException -> toast("网络连接异常")
            else -> toast("未知错误")

        }
    }
}