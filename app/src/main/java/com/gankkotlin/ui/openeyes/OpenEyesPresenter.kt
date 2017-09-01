package com.gankkotlin.ui.openeyes

import com.data.bean.openeyes.EyesPage
import com.data.bean.openeyes.ItemListBean
import com.data.repo.OpenEyesRepository
import com.gankkotlin.extension.onUI
import com.gankkotlin.ui.common.base.BasePresenter
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/30
 */
class OpenEyesPresenter(val mView: OpenEyesContract.View) : BasePresenter(), OpenEyesContract.Presenter {

    val page = EyesPage(2, 0)

    val data = ArrayList<ItemListBean>()
    val onNext by lazy<((ItemListBean) -> Unit)> {
        { item: ItemListBean ->
            data.add(item)
        }
    }

    val onError by lazy {
        { t: Throwable ->
            t.printStackTrace()
            mView.onError(t)
        }
    }
    val onComplete by lazy {
        { page.page += 2 }
    }

    override fun loadMore() {
        getContentObservable()
                .bindToLifecycle(this)
                .onUI()
                .subscribe(onNext, onError) {
                    mView.onLoadMoreComplete()
                    onComplete.invoke()
                }
    }

    override fun refresh() {
        page.page = 0
        getContentObservable(true)
                .bindToLifecycle(this)
                .onUI()
                .subscribe(onNext, onError) {
                    mView.onRefreshComplete()
                    onComplete.invoke()
                }
    }

    fun getContentObservable(clearData: Boolean = false): Observable<ItemListBean> {
        return OpenEyesRepository.getOpenEyesDatas(page)
                .flatMap {
                    if (clearData) data.clear()
                    Observable.fromIterable(it.itemList)
                            .filter { "video" == it.type }
                }
    }

}