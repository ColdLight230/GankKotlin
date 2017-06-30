package com.data.service

import com.data.DataLayer
import com.data.bean.ApiResult
import com.data.bean.Article
import com.data.bean.ArticleWithContent
import com.data.bean.DailyData
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
object GankService {

    val API_HOST_URL = DataLayer.apiHostUrl
    val api: APIs

    init {
        val restAdapter = Retrofit.Builder()
                .baseUrl(API_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create(DataLayer.gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(DataLayer.okHttpClient)
                .build()

        api = restAdapter.create(APIs::class.java)
    }
}


interface APIs {
    /**
     * 获取最新数据(含内容)
     */
    @GET("history/content/{num}/{index}")
    fun getByPage(@Path("num") num: Int,
                  @Path("index") index: Int)
            : Observable<ApiResult<List<ArticleWithContent>>>

    /**
     * 获取发过干货日期接口
     */
    @GET("day/history")
    fun getHistory(): Observable<ApiResult<List<String>>>

    /**
     * 获取分类数据
     */
    @GET("data/{category}/{num}/{index}")
    fun getByCategory(@Path("category") category: String,
                      @Path("num") num: Int,
                      @Path("index") index: Int)
            : Observable<ApiResult<List<Article>>>

    /**
     * 根据日期获取数据
     */
    @GET("day/{year}/{month}/{day}")
    fun getByDate(@Path("year") year: Int,
                  @Path("month") month: Int,
                  @Path("day") day: Int)
            : Observable<ApiResult<Map<String, List<DailyData>>>>

    /**
     * 获取类型的随机数据
     */
    @GET("random/data/{category}/{num}")
    fun random(@Path("category") category: String,
               @Path("num") num: Int)
            : Observable<List<Article>>

}

