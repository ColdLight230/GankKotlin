package com.gankkotlin.ui.openeyes

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.data.bean.openeyes.ItemListBean
import com.gankkotlin.R
import com.gankkotlin.utils.ImageLoader

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/31
 */
class OpenEyesAdapter(data: List<ItemListBean>) : BaseQuickAdapter<ItemListBean, BaseViewHolder>(R.layout.item_view_document, data) {
    override fun convert(helper: BaseViewHolder, item: ItemListBean) {
        ImageLoader.loadImage(mContext, item.data?.cover?.detail, R.mipmap.ic_launcher, helper.getView<ImageView>(R.id.background))
        helper.setText(R.id.article_title, item.data?.title)

    }
}