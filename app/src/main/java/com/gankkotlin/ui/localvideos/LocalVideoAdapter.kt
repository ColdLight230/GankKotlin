package com.gankkotlin.ui.localvideos

import android.content.Intent
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.data.bean.openeyes.Video
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.ui.videoplayer.JCVideoPlayerActivity

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/5
 */
class LocalVideoAdapter(date: List<Video>) : BaseQuickAdapter<Video, BaseViewHolder>(R.layout.item_article_type1, date) {

    override fun convert(helper: BaseViewHolder, item: Video) {
        helper.setText(R.id.tv_title, item.name)
                .setText(R.id.tv_time, "${item.duration}")
                .setText(R.id.tv_username, "${item.type}")

        val iv_preview = helper.getView<ImageView>(R.id.iv_preview)

        helper.itemView.setOnClickListener {
            val intent = Intent(mContext, JCVideoPlayerActivity::class.java)
            intent.putExtra(Constants.FIRST_INTENT_PARAM, item.data)
            intent.putExtra(Constants.SECOND_INTENT_PARAM, item.name)
            mContext.startActivity(intent)
        }
    }
}