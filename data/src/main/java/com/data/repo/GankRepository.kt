package com.data.repo

import com.data.bean.Article
import com.data.bean.ArticleWithContent
import com.data.bean.DailyData
import com.data.bean.Page
import com.data.extension.handleResponse
import com.data.service.GankService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
object GankRepository {
    /**
     * 根据当前页数和每页数量获取文章
     */
    fun getByPage(page: Page): Observable<List<ArticleWithContent>> =
            GankService.api.getByPage(page.size, page.index)
                    .subscribeOn(Schedulers.io())
                    .handleResponse()

    fun getByCategory(category: String, page: Page): Observable<List<Article>> = GankService.api.getByCategory(category, page.size, page.index)
            .subscribeOn(Schedulers.io())
            .handleResponse()

    fun getByDate(year: Int, month: Int, day: Int): Observable<Map<String, List<DailyData>>> =
            GankService.api.getByDate(year, month, day)
                    .subscribeOn(Schedulers.io())
                    .handleResponse()
}

