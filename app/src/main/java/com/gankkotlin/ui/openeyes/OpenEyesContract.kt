package com.gankkotlin.ui.openeyes

import com.gankkotlin.ui.common.base.IBasePresenter
import com.gankkotlin.ui.common.base.IBaseView

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/30
 */
interface OpenEyesContract {

    interface View : IBaseView {
        fun onRefreshComplete()
        fun onLoadMoreComplete()
    }

    interface Presenter : IBasePresenter {
        fun refresh()
        fun loadMore()
    }

}