package com.gankkotlin.ui.articledetail

import com.data.bean.Category
import com.data.bean.DailyData
import com.data.repo.GankRepository
import com.gankkotlin.extension.onUI
import com.gankkotlin.ui.common.base.BasePresenter
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import java.util.*
import kotlin.collections.ArrayList

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/24
 */
class ArticleDetailPresenter(val mView: ArticleDetailContract.View) : BasePresenter(), ArticleDetailContract.Presenter {

    val dailyContent = ArrayList<ArticleDetailViewModel>()

    val onError by lazy {
        { t: Throwable ->
            t.printStackTrace()
            mView.onError(t)
        }
    }
    val onNext by lazy {
        { result: Map<String, List<DailyData>> ->
            result.forEach { key, value ->
                if (key == Category.福利) return@forEach
                value.forEach {
                    dailyContent.add(ArticleDetailViewModel(if (it.images != null && it.images!!.isNotEmpty()) ONE_PIC else NO_PIC, it))
                }
            }
        }
    }
    val onComplete by lazy { { mView.loadDailyDataComplete() } }

    override fun loadDailyData(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        GankRepository.getByDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                .bindToLifecycle(this)
                .onUI()
                .subscribe(onNext, onError) {
                    onComplete.invoke()
                }
    }
}