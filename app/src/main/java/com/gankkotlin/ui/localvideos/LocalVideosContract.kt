package com.gankkotlin.ui.localvideos

import android.content.Context
import com.gankkotlin.ui.common.base.IBasePresenter
import com.gankkotlin.ui.common.base.IBaseView

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/5
 */
interface LocalVideosContract {
    interface View : IBaseView {
        fun onLoadLocalVideosComplete()
    }

    interface Presenter : IBasePresenter {
        fun loadLocalVideos(context: Context)
    }
}