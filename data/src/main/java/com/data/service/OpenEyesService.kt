package com.data.service

import com.data.DataLayer
import com.data.bean.openeyes.EyesPageData
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/30
 */
object OpenEyesService {
    val API_HOST_URL = "http://baobab.kaiyanapp.com/api/"
    val api: EyesAPIs

    init {
        val eyesAdapter = Retrofit.Builder()
                .baseUrl(API_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(DataLayer.okHttpClient)
                .build()
        api = eyesAdapter.create(EyesAPIs::class.java)
    }
}

interface EyesAPIs {
    @GET("v4/tabs/selected")
    fun getOpenEyesDatas(@Query("num") num: Int,
                         @Query("page") page: Int): Observable<EyesPageData>

}