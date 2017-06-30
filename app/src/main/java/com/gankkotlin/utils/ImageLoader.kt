package com.gankkotlin.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
object ImageLoader {

    const val ANDROID_RESOURCE = "android.resource://"
    val FORWARD_SLASH: String = File.separator

    /**
     * 加载网络图片
     * @param context   context
     * @param url       url
     * @param imageView imageView
     */
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).centerCrop().into(imageView)
    }

    /**
     * 加载网络图片并设置大小
     * @param context   context
     * @param url       url
     * @param imageView imageView
     * @param width     width
     * @param height    height
     */
    fun loadImage(context: Context, url: String, imageView: ImageView, width: Int, height: Int) {
        Glide.with(context).load(url).override(width, height).into(imageView)
    }

    /**
     * 加载SD卡图片
     * @param context   context
     * @param file      file
     * @param imageView imageView
     */
    fun loadImage(context: Context, file: File, imageView: ImageView) {
        Glide.with(context).load(file).centerCrop().into(imageView)
    }

    /**
     * 加载SD卡图片并设置大小
     * @param context   context
     * @param file      file
     * @param imageView imageView
     * @param width     width
     * @param height    height
     */
    fun loadImage(context: Context, file: File, imageView: ImageView, width: Int, height: Int) {
        Glide.with(context).load(file).override(width, height).into(imageView)
    }

    /**
     * 加载 Drawable 图片
     * @param context   context
     * @param resId     drawableResId
     * @param imageView imageView
     */
    fun loadImage(context: Context, resId: Int, imageView: ImageView) {
        Glide.with(context).load(resourceIdToUri(context, resId)).into(imageView)
    }

    /**
     * 将资源ID转为Uri
     * @param context    context
     * @param resourceId resourceId
     * @return Uri
     */
    fun resourceIdToUri(context: Context, resourceId: Int): Uri {
        return Uri.parse(ANDROID_RESOURCE + context.packageName + FORWARD_SLASH + resourceId)
    }
}