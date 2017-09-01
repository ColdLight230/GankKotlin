package com.gankkotlin.ui.articledetail

import android.os.Bundle
import android.view.View
import com.data.bean.ArticleWithContent
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.databinding.ActivityArticleDetailBinding
import com.gankkotlin.ui.common.base.BaseActivity
import com.gankkotlin.utils.ImageLoader

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/10
 */
class ArticleDetailActivity : BaseActivity<ActivityArticleDetailBinding>(R.layout.activity_article_detail) {

    val article: ArticleWithContent? by lazy { intent.getParcelableExtra<ArticleWithContent>(Constants.IntentKey.ARTICLE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            when (1 + verticalOffset / appBarLayout.totalScrollRange) {
                0 -> binding.title.visibility = View.VISIBLE
                else -> binding.title.visibility = View.GONE
            }
        }
        ImageLoader.loadImage(context, article?.meizi?.url!!, binding.head.image)

        replaceFragment(ArticleDetailFragment.newInstance(article?.publishedAt), R.id.fl_content, false)
    }

}