package com.gankkotlin.ui.localvideos

import android.content.Context
import com.data.bean.openeyes.Video
import com.data.repo.LocalRepository
import com.gankkotlin.extension.onUI
import com.gankkotlin.ui.common.base.BasePresenter
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.schedulers.Schedulers

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/5
 */
class LocalVideosPresenter(val mView: LocalVideosContract.View) : BasePresenter(), LocalVideosContract.Presenter {

    val mData = arrayListOf<Video>()

    val onNext by lazy<(List<Video>) -> Unit> {
        { videos: List<Video> ->
            mData.clear()
            mData.addAll(videos)
        }
    }

    val onError by lazy {
        { t: Throwable ->
            t.printStackTrace()
            mView.onError(t)
        }
    }

    val onComplete by lazy { { mView.onLoadLocalVideosComplete() } }

    override fun loadLocalVideos(context: Context) {
        LocalRepository.getLocalVideos(context)
                .subscribeOn(Schedulers.io())
                .bindToLifecycle(this)
                .onUI()
                .subscribe(onNext, onError, onComplete)
    }
}