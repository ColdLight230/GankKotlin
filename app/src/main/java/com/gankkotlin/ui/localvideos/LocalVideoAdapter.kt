package com.gankkotlin.ui.localvideos

import android.widget.ImageView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.data.bean.openeyes.Video
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.extension.formatDuration
import com.gankkotlin.extension.formatFileSize
import com.gankkotlin.ui.videoplayer.JCVideoPlayerActivity
import com.gankkotlin.ui.videoplayer.VideoViewModel
import com.gankkotlin.utils.ImageLoader
import org.jetbrains.anko.startActivity

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/5
 */
class LocalVideoAdapter(date: List<Video>) : BaseQuickAdapter<Video, BaseViewHolder>(R.layout.item_article_type1, date) {

    override fun convert(helper: BaseViewHolder, item: Video) {
        helper.setText(R.id.tv_title, item.name)
                .setText(R.id.tv_time, item.duration.formatDuration())
                .setText(R.id.tv_username, item.size.formatFileSize())

        val iv_preview = helper.getView<ImageView>(R.id.iv_preview)
        ImageLoader.loadImage(mContext, item.imageUrl!!, iv_preview)

        helper.itemView.setOnClickListener {
            Toast.makeText(mContext, item.imageUrl!! + "  " + helper.layoutPosition, Toast.LENGTH_SHORT).show()
            mContext.startActivity<JCVideoPlayerActivity>(Constants.FIRST_INTENT_PARAM to VideoViewModel(item.name, item.data, null))
        }
    }
}