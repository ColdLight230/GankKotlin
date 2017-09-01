package com.data.repo

import com.data.bean.openeyes.EyesPage
import com.data.bean.openeyes.EyesPageData
import com.data.service.OpenEyesService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/30
 */
object OpenEyesRepository {

    fun getOpenEyesDatas(eyesPage: EyesPage): Observable<EyesPageData> = OpenEyesService.api.getOpenEyesDatas(eyesPage.num, eyesPage.page)
            .subscribeOn(Schedulers.io())

}