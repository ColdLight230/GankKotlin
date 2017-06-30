package com.gankkotlin.ui.common.base

import android.support.annotation.CheckResult
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/8
 */
abstract class BasePresenter : IBasePresenter, LifecycleProvider<FragmentEvent> {


    private val lifecycleSubject = BehaviorSubject.create<FragmentEvent>()
    @CheckResult
    override fun lifecycle(): Observable<FragmentEvent> {
        return lifecycleSubject.hide()
    }
    @CheckResult
    override fun <T> bindUntilEvent(event: FragmentEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent<T, FragmentEvent>(lifecycleSubject, event)
    }
    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindFragment<T>(lifecycleSubject)
    }

    override fun onAttach() {
        lifecycleSubject.onNext(FragmentEvent.ATTACH)
    }

    override fun onCreate() {
        lifecycleSubject.onNext(FragmentEvent.CREATE)
    }

    override fun onViewCreated() {
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW)
    }

    override fun onStart() {
        lifecycleSubject.onNext(FragmentEvent.START)
    }

    override fun onResume() {
        lifecycleSubject.onNext(FragmentEvent.RESUME)
    }

    override fun onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE)
    }

    override fun onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP)
    }

    override fun onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW)
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY)
    }

    override fun onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH)
    }
}