package com.gankkotlin.ui.articledetail

import android.databinding.BaseObservable
import android.os.Parcel
import android.os.Parcelable
import com.data.bean.DailyData

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/28
 */
const val NO_PIC: Int = 0
const val ONE_PIC: Int = 1

data class ArticleDetailViewModel(val type: Int = NO_PIC,
                                  val content: DailyData) : BaseObservable(), Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ArticleDetailViewModel> = object : Parcelable.Creator<ArticleDetailViewModel> {
            override fun createFromParcel(source: Parcel): ArticleDetailViewModel = ArticleDetailViewModel(source)
            override fun newArray(size: Int): Array<ArticleDetailViewModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readParcelable<DailyData>(DailyData::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(type)
        dest.writeParcelable(content, 0)
    }
}
