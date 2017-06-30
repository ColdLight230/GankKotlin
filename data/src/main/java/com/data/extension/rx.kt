package com.data.extension

import com.data.bean.ApiResult
import com.data.exception.ServerException
import io.reactivex.Observable

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
fun <T> Observable<ApiResult<T>>.handleResponse() = flatMap {
    when(it.error){
        false -> Observable.just(it.results)
        true -> Observable.error<T>(ServerException(it.message))
    }
}