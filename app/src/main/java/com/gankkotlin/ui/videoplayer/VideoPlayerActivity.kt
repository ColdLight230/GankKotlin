package com.gankkotlin.ui.videoplayer

import android.os.Bundle
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.databinding.ActivityVideoPlayerBinding
import com.gankkotlin.ui.common.base.BaseActivity
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/1
 */
class VideoPlayerActivity : BaseActivity<ActivityVideoPlayerBinding>(R.layout.activity_video_player) {

    val mUrl: String by lazy { intent.getStringExtra(Constants.FIRST_INTENT_PARAM) }
    val mTitle: String by lazy { intent.getStringExtra(Constants.SECOND_INTENT_PARAM) }
    val mFragment: VideoPlayerFragment by lazy { VideoPlayerFragment.newInstance(mUrl, mTitle) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        replaceFragment(mFragment, R.id.fl_content, false)
    }

    override fun onPause() {
        super.onPause()
        mFragment.releaseAllVideos()
    }

    override fun onBackPressed() {
        if(JCVideoPlayer.backPress()){
            return
        }
        super.onBackPressed()
    }
}