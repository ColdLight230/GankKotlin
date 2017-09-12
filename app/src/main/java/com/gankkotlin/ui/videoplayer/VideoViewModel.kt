package com.gankkotlin.ui.videoplayer

import android.os.Parcel
import android.os.Parcelable

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/12
 */
data class VideoViewModel(val title: String?, val url: String?, val imageUrl: String?) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<VideoViewModel> = object : Parcelable.Creator<VideoViewModel> {
            override fun createFromParcel(source: Parcel): VideoViewModel = VideoViewModel(source)
            override fun newArray(size: Int): Array<VideoViewModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(url)
        dest.writeString(imageUrl)
    }
}