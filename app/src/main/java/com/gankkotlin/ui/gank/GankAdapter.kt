package com.gankkotlin.ui.gank

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.support.v4.content.ContextCompat
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.data.bean.ArticleWithContent
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.ui.articledetail.ArticleDetailActivity
import com.gankkotlin.utils.ImageLoader
import org.jetbrains.anko.startActivity


/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/9
 */
class GankAdapter(data: List<ArticleWithContent>) : BaseQuickAdapter<ArticleWithContent, BaseViewHolder>(R.layout.item_view_gank, data) {
    override fun convert(helper: BaseViewHolder, item: ArticleWithContent) {
        val background = helper.getView<ImageView>(R.id.background)!!
        ImageLoader.loadImage(mContext, item.meizi?.url!!, background)
        val articleTitle = helper.getView<TextView>(R.id.article_title)
        articleTitle.text = item.title

        val backgroundColorAnimator1 = ObjectAnimator.ofObject(articleTitle,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(mContext, R.color.maskColor),
                ContextCompat.getColor(mContext, android.R.color.transparent))
        val textColorAnimator1 = ObjectAnimator.ofObject(articleTitle,
                "textColor",
                ArgbEvaluator(),
                ContextCompat.getColor(mContext, R.color.white),
                ContextCompat.getColor(mContext, android.R.color.transparent))
        val set1 = AnimatorSet()
        set1.play(backgroundColorAnimator1).with(textColorAnimator1)
        set1.duration = 300

        val backgroundColorAnimator2 = ObjectAnimator.ofObject(articleTitle,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(mContext, android.R.color.transparent),
                ContextCompat.getColor(mContext, R.color.maskColor))
        val textColorAnimator2 = ObjectAnimator.ofObject(articleTitle,
                "textColor",
                ArgbEvaluator(),
                ContextCompat.getColor(mContext, android.R.color.transparent),
                ContextCompat.getColor(mContext, R.color.white)
        )
        val set2 = AnimatorSet()
        set2.play(backgroundColorAnimator2).with(textColorAnimator2)
        set2.duration = 300

        helper.itemView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    set1.start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    set2.start()
                }
            }
            false
        }

        helper.itemView.setOnClickListener {
            mContext.startActivity<ArticleDetailActivity>(Constants.IntentKey.ARTICLE to item)
        }
    }

}