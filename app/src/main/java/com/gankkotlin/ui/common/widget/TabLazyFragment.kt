package com.gankkotlin.ui.common.widget

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.gankkotlin.ui.common.base.BaseFragment

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/30
 */
abstract class TabLazyFragment<B : ViewDataBinding>(@LayoutRes val lazyLayoutId: Int) : BaseFragment<B>(lazyLayoutId) {
    var mIsVisible: Boolean = false
    var mIsPrepared: Boolean = false
    var mIsFirstLoad: Boolean = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mIsPrepared = true
        lazyLoad()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        when (userVisibleHint) {
            true -> {
                mIsVisible = true
                lazyLoad()
            }
            false -> mIsVisible = false

        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        when (hidden) {
            true -> mIsVisible = false
            false -> {
                mIsVisible = true
                lazyLoad()
            }
        }
    }

    fun lazyLoad() {
        if (!mIsPrepared || !mIsVisible || !mIsFirstLoad) {
            return
        }
        mIsFirstLoad = false
        initData()
    }

    abstract fun initData()

}