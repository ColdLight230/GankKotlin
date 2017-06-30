package com.data.bean

import android.os.Parcel
import android.os.Parcelable
import com.data.extension.createParcel
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/6
 */
data class ApiResult<out T>(val category: List<String>? = null,
                            val error: Boolean,
                            val results: T,
                            val message: String? = null)


data class Article(@SerializedName("_id") val id: String,
                   val desc: String,
                   val source: String,
                   val type: String,
                   val url: String,
                   val used: Boolean,
                   val who: String?,
                   val createdAt: Date,
                   val publishedAt: Date) : Parcelable {

    constructor(source: Parcel) : this(source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            1.toByte() == source.readByte(),
            source.readString(),
            source.readSerializable() as Date,
            source.readSerializable() as Date)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(desc)
        dest?.writeString(source)
        dest?.writeString(type)
        dest?.writeString(url)
        dest?.writeByte((if (used) 1 else 0).toByte())
        dest?.writeString(who)
        dest?.writeSerializable(createdAt)
        dest?.writeSerializable(publishedAt)
    }

    companion object {
        @Suppress("UNUSED")
        @JvmField val CREATOR = createParcel(::Article)
    }
}

data class ArticleWithContent(@SerializedName("_id") val id: String,
                              val content: String,
                              val publishedAt: Date,
                              val title: String,
                              var meizi: Article? = null) : Parcelable {

    constructor(source: Parcel) : this(source.readString(),
            source.readString(),
            source.readSerializable() as Date,
            source.readString(),
            source.readParcelable<Article?>(Article::class.java.classLoader))

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(content)
        dest?.writeSerializable(publishedAt)
        dest?.writeString(title)
        dest?.writeParcelable(meizi, 0)
    }

    companion object {
        @Suppress("UNUSED")
        @JvmField val CREATOR = createParcel(::ArticleWithContent)
    }
}

data class DailyData(
        @SerializedName("_id") val id: String,
        val createdAt: Date,
        val desc: String,
        val images: List<String>?,
        val publishedAt: Date,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<DailyData> = object : Parcelable.Creator<DailyData> {
            override fun createFromParcel(source: Parcel): DailyData = DailyData(source)
            override fun newArray(size: Int): Array<DailyData?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readSerializable() as Date,
    source.readString(),
    source.createStringArrayList(),
    source.readSerializable() as Date,
    source.readString(),
    source.readString(),
    source.readString(),
    1 == source.readInt(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeSerializable(createdAt)
        dest.writeString(desc)
        dest.writeStringList(images)
        dest.writeSerializable(publishedAt)
        dest.writeString(source)
        dest.writeString(type)
        dest.writeString(url)
        dest.writeInt((if (used) 1 else 0))
        dest.writeString(who)
    }
}


data class Page(var size: Int, var index: Int) {
    fun total() = size * index
}
