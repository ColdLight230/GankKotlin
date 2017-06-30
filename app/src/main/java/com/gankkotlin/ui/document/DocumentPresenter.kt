package com.gankkotlin.ui.document

import com.data.bean.Article
import com.data.bean.ArticleWithContent
import com.data.bean.Category
import com.data.bean.Page
import com.data.repo.GankRepository
import com.gankkotlin.extension.onUI
import com.gankkotlin.ui.common.base.BasePresenter
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/8
 */
class DocumentPresenter(val mView: DocumentContract.View) : BasePresenter(), DocumentContract.Presenter {

    val mPage = Page(10, 1)
    val mDatas = ArrayList<ArticleWithContent>()

    val onNext by lazy<((ArticleWithContent) -> Unit)> {
        { article: ArticleWithContent -> mDatas.add(article) }
    }

    val onComplete by lazy {
        { mPage.index += 1 }
    }

    val onError by lazy {
        { t: Throwable ->
            t.printStackTrace()
            mView.onError(t)
        }
    }

    val zipFunc by lazy {
        BiFunction<ArticleWithContent, Article, ArticleWithContent> { t1, t2 ->
            t1?.meizi = t2
            t1
        }
    }

    override fun refresh() {
        val temp = mPage.index
        val refreshOnError = { t: Throwable ->
            mPage.index = temp
            onError.invoke(t)
        }
        mPage.index = 1 // 重置页数
        getContentObservable(true)
                .zipWith(getMeiziObservable(), zipFunc)
                .bindToLifecycle(this)
                .onUI()
                .subscribe(onNext, refreshOnError) {
                    mView.onRefreshComplete()
                    onComplete.invoke()
                }
    }

    override fun loadMore() {
        getContentObservable()
                .zipWith(getMeiziObservable(), zipFunc)
                .bindToLifecycle(this)
                .onUI()
                .subscribe(onNext, onError) {
                    mView.onLoadMoreComplete(mPage.total(), mPage.size)
                    onComplete.invoke()
                }
    }

    private fun getMeiziObservable(): Observable<Article> {
        return GankRepository.getByCategory(Category.福利, mPage)
                .flatMap { Observable.fromIterable(it) }
    }

    private fun getContentObservable(clearData: Boolean = false): Observable<ArticleWithContent> {
        return GankRepository.getByPage(mPage)
                .flatMap {
                    if (clearData) mDatas.clear()
                    Observable.fromIterable(it)
                }
    }
}