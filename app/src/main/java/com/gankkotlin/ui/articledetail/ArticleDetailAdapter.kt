package com.gankkotlin.ui.articledetail

import android.graphics.BitmapFactory
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gankkotlin.R
import com.gankkotlin.extension.dateToString
import com.gankkotlin.utils.ImageLoader

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/10
 */
class ArticleDetailAdapter(data: List<ArticleDetailViewModel>) : BaseItemDraggableAdapter<ArticleDetailViewModel, BaseViewHolder>(data) {

    override fun convert(helper: BaseViewHolder, item: ArticleDetailViewModel) {
        when (helper.itemViewType) {
            NO_PIC -> {
                helper.setText(R.id.tv_title, item.content.desc)
                        .setText(R.id.tv_username, item.content.who)
                        .setText(R.id.tv_time, "${item.content.createdAt.dateToString("yyyy/MM/dd")}")
            }
            ONE_PIC -> {
                helper.setText(R.id.tv_title, item.content.desc)
                        .setText(R.id.tv_username, item.content.who)
                        .setText(R.id.tv_time, "${item.content.createdAt.dateToString("yyyy/MM/dd")}")
                ImageLoader.loadImage(mContext, item.content.images!![0], helper.getView(R.id.iv_preview))
            }
        }

        helper.itemView.setOnClickListener {
            val url = item.content.url
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
            val bitmap = BitmapFactory.decodeResource(mContext.resources,
                    R.drawable.ic_arrow_back_black_24dp)
            builder.setCloseButtonIcon(bitmap)
            val customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(mContext, Uri.parse(url))
        }
    }

    override fun getDefItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return when (viewType) {
            NO_PIC -> BaseViewHolder(mLayoutInflater.inflate(R.layout.item_article_type3, parent, false))
            ONE_PIC -> BaseViewHolder(mLayoutInflater.inflate(R.layout.item_article_type1, parent, false))
            else -> super.onCreateDefViewHolder(parent, viewType)
        }
    }
}