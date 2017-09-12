package com.data.bean.openeyes

import android.os.Parcel
import android.os.Parcelable

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/30
 */

data class EyesPageData(var count: Int = 0,
                        var total: Int = 0,
                        var nextPageUrl: String?,
                        var date: Long = 0,
                        var nextPublishTime: Long = 0,
                        var refreshCount: Int = 0,
                        var lastStartId: Int = 0,
                        var itemList: List<ItemListBean>? = null)

data class ItemListBean(
        var type: String?,
        var data: DataBean?,
        var tag: String?
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ItemListBean> = object : Parcelable.Creator<ItemListBean> {
            override fun createFromParcel(source: Parcel): ItemListBean = ItemListBean(source)
            override fun newArray(size: Int): Array<ItemListBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readParcelable<DataBean>(DataBean::class.java.classLoader),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(type)
        dest.writeParcelable(data, 0)
        dest.writeString(tag)
    }
}

data class DataBean(
        var category: String? = null,
        var collected: Boolean = false,
        var consumption: ConsumptionBean? = null,
        var cover: CoverBean? = null,
        var dataType: String? = null,
        var date: Long = 0,
        var description: String? = null,
        var descriptionEditor: String? = null,
        var duration: Int = 0,
        var id: Int = 0,
        var idx: Int = 0,
        var library: String? = null,
        var playUrl: String? = null,
        var played: Boolean = false,
        var provider: ProviderBean? = null,
        var releaseTime: Long = 0,
        var slogan: String? = null,
        var thumbPlayUrl: String? = null,
        var title: String? = null,
        var type: String? = null,
        var webUrl: WebUrlBean? = null,
        var labelList: List<*>? = null,
        var playInfo: List<PlayInfoBean>? = null,
        var subtitles: List<*>? = null,
        var tags: List<TagsBean>? = null,
        var actionUrl: String? = null,
        var image: String? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<DataBean> = object : Parcelable.Creator<DataBean> {
            override fun createFromParcel(source: Parcel): DataBean = DataBean(source)
            override fun newArray(size: Int): Array<DataBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            1 == source.readInt(),
            source.readParcelable<ConsumptionBean>(ConsumptionBean::class.java.classLoader),
            source.readParcelable<CoverBean>(CoverBean::class.java.classLoader),
            source.readString(),
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readParcelable<ProviderBean>(ProviderBean::class.java.classLoader),
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readParcelable<WebUrlBean>(WebUrlBean::class.java.classLoader),
            ArrayList<Any?>().apply { source.readList(this, Any::class.java.classLoader) },
            source.createTypedArrayList(PlayInfoBean.CREATOR),
            ArrayList<Any?>().apply { source.readList(this, Any::class.java.classLoader) },
            source.createTypedArrayList(TagsBean.CREATOR),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(category)
        dest.writeInt((if (collected) 1 else 0))
        dest.writeParcelable(consumption, 0)
        dest.writeParcelable(cover, 0)
        dest.writeString(dataType)
        dest.writeLong(date)
        dest.writeString(description)
        dest.writeString(descriptionEditor)
        dest.writeInt(duration)
        dest.writeInt(id)
        dest.writeInt(idx)
        dest.writeString(library)
        dest.writeString(playUrl)
        dest.writeInt((if (played) 1 else 0))
        dest.writeParcelable(provider, 0)
        dest.writeLong(releaseTime)
        dest.writeString(slogan)
        dest.writeString(thumbPlayUrl)
        dest.writeString(title)
        dest.writeString(type)
        dest.writeParcelable(webUrl, 0)
        dest.writeList(labelList)
        dest.writeTypedList(playInfo)
        dest.writeList(subtitles)
        dest.writeTypedList(tags)
        dest.writeString(actionUrl)
        dest.writeString(image)
    }
}

data class ConsumptionBean(
        var collectionCount: Int = 0,
        var replyCount: Int = 0,
        var shareCount: Int = 0
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ConsumptionBean> = object : Parcelable.Creator<ConsumptionBean> {
            override fun createFromParcel(source: Parcel): ConsumptionBean = ConsumptionBean(source)
            override fun newArray(size: Int): Array<ConsumptionBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(collectionCount)
        dest.writeInt(replyCount)
        dest.writeInt(shareCount)
    }
}

data class CoverBean(
        var blurred: String? = null,
        var detail: String? = null,
        var feed: String? = null,
        var homepage: String? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CoverBean> = object : Parcelable.Creator<CoverBean> {
            override fun createFromParcel(source: Parcel): CoverBean = CoverBean(source)
            override fun newArray(size: Int): Array<CoverBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(blurred)
        dest.writeString(detail)
        dest.writeString(feed)
        dest.writeString(homepage)
    }
}

data class ProviderBean(

        var alias: String? = null,
        var icon: String? = null,
        var name: String? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ProviderBean> = object : Parcelable.Creator<ProviderBean> {
            override fun createFromParcel(source: Parcel): ProviderBean = ProviderBean(source)
            override fun newArray(size: Int): Array<ProviderBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(alias)
        dest.writeString(icon)
        dest.writeString(name)
    }
}

data class WebUrlBean(
        var forWeibo: String? = null,
        var raw: String? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<WebUrlBean> = object : Parcelable.Creator<WebUrlBean> {
            override fun createFromParcel(source: Parcel): WebUrlBean = WebUrlBean(source)
            override fun newArray(size: Int): Array<WebUrlBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(forWeibo)
        dest.writeString(raw)
    }
}

data class PlayInfoBean(
        var height: Int = 0,
        var name: String? = null,
        var type: String? = null,
        var url: String? = null,
        var width: Int = 0,
        var urlList: List<UrlListBean>? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PlayInfoBean> = object : Parcelable.Creator<PlayInfoBean> {
            override fun createFromParcel(source: Parcel): PlayInfoBean = PlayInfoBean(source)
            override fun newArray(size: Int): Array<PlayInfoBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            ArrayList<UrlListBean>().apply { source.readList(this, UrlListBean::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(height)
        dest.writeString(name)
        dest.writeString(type)
        dest.writeString(url)
        dest.writeInt(width)
        dest.writeList(urlList)
    }
}

data class UrlListBean(

        var name: String? = null,
        var size: Int = 0,
        var url: String? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<UrlListBean> = object : Parcelable.Creator<UrlListBean> {
            override fun createFromParcel(source: Parcel): UrlListBean = UrlListBean(source)
            override fun newArray(size: Int): Array<UrlListBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(size)
        dest.writeString(url)
    }
}

data class TagsBean(
        var actionUrl: String? = null,
        var id: Int = 0,
        var name: String? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TagsBean> = object : Parcelable.Creator<TagsBean> {
            override fun createFromParcel(source: Parcel): TagsBean = TagsBean(source)
            override fun newArray(size: Int): Array<TagsBean?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(actionUrl)
        dest.writeInt(id)
        dest.writeString(name)
    }
}

class EyesPage(var num: Int, var page: Int)

data class Video(val name: String, val duration: Long, val type: String,
                 val size: Long, val data: String, val imageUrl: String?, val desc: String?) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(source: Parcel): Video = Video(source)
            override fun newArray(size: Int): Array<Video?> = arrayOfNulls(size)
        }
    }


    constructor(source: Parcel) : this(
            source.readString(),
            source.readLong(),
            source.readString(),
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeLong(duration)
        dest.writeString(type)
        dest.writeLong(size)
        dest.writeString(data)
        dest.writeString(imageUrl)
        dest.writeString(desc)
    }
}

