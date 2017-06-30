package com.gankkotlin.ui.articledetail

import com.gankkotlin.ui.common.base.IBasePresenter
import com.gankkotlin.ui.common.base.IBaseView
import java.util.*

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/24
 */
interface ArticleDetailContract {
    interface View : IBaseView {
        fun loadDailyDataComplete()
    }

    interface Presenter : IBasePresenter {
        fun loadDailyData(date: Date)
    }

}