package com.gankkotlin.ui.gank

import com.gankkotlin.ui.common.base.IBasePresenter
import com.gankkotlin.ui.common.base.IBaseView

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/8
 */
interface GankContract {
    interface View : IBaseView {
        fun onRefreshComplete()
        fun onLoadMoreComplete(positionStart: Int, itemCount: Int)
    }

    interface Presenter : IBasePresenter {
        fun refresh()
        fun loadMore()
    }
}