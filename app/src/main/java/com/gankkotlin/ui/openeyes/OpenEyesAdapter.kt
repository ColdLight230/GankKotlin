package com.gankkotlin.ui.openeyes

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.data.bean.openeyes.ItemListBean
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.ui.videoplayer.JCVideoPlayerActivity
import com.gankkotlin.ui.videoplayer.VideoViewModel
import com.gankkotlin.utils.ImageLoader
import org.jetbrains.anko.startActivity

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/31
 */
class OpenEyesAdapter(data: List<ItemListBean>) : BaseQuickAdapter<ItemListBean, BaseViewHolder>(R.layout.item_view_gank, data) {
    override fun convert(helper: BaseViewHolder, item: ItemListBean) {
        ImageLoader.loadImage(mContext, item.data?.cover?.detail, R.drawable.grey_rect, helper.getView<ImageView>(R.id.background))
        helper.setText(R.id.article_title, item.data?.title)

        helper.itemView.setOnClickListener {
            val viewModel = VideoViewModel(item.data?.title, item.data?.playUrl, item.data?.cover?.detail)
            mContext.startActivity<JCVideoPlayerActivity>(Constants.FIRST_INTENT_PARAM to viewModel)
        }
    }
}