package com.gankkotlin.ui.videoplayer

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.databinding.ActivityVideoPlayerBinding
import com.gankkotlin.ui.common.base.BaseActivity
import com.gankkotlin.widget.VideoPlayerView
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/1
 */
class VideoPlayerActivity : BaseActivity<ActivityVideoPlayerBinding>(R.layout.activity_video_player) {

    val mUrl: String by lazy { intent.getStringExtra(Constants.FIRST_INTENT_PARAM) }
    val mTitle: String by lazy { intent.getStringExtra(Constants.SECOND_INTENT_PARAM) }
    val mVideoPlayerView by lazy { binding.videoPlayerView as VideoPlayerView }
    val mTvPlus by lazy { binding.tvPlus as TextView }
    val mTvMinus by lazy { binding.tvMinus as TextView }
    val mTvRate by lazy { binding.tvRate as TextView }
    val orientationUtils by lazy { OrientationUtils(this, mVideoPlayerView) }
    var isPause: Boolean = false
    var isPlay: Boolean = false

    var mRate: Float = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        orientationUtils.isEnable = false
        mTvRate.text = mRate.toString()
        mVideoPlayerView.backButton.visibility = View.GONE

        GSYVideoOptionBuilder()
//                .setThumbImageView(R.mipmap.ic_launcher)
                .setUrl(mUrl)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1f)
                .setStandardVideoAllCallBack(object : SampleListener() {
                    override fun onPrepared(url: String, vararg objects: Any) {
                        super.onPrepared(url, objects)
                        //开始播放了才能旋转和全屏
                        orientationUtils.isEnable = true
                        isPlay = true
                    }

                    override fun onQuitFullscreen(url: String, vararg objects: Any) {
                        super.onQuitFullscreen(url, objects)
                        orientationUtils.backToProtVideo()
                    }
                })
                .build(mVideoPlayerView)
        mVideoPlayerView.fullscreenButton.setOnClickListener({
            //直接横屏
            orientationUtils.resolveByClick()

            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            mVideoPlayerView.startWindowFullscreen(this@VideoPlayerActivity, true, true)
        })

        mVideoPlayerView.setLockClickListener({ _, lock ->
            //配合下方的onConfigurationChanged
            orientationUtils.isEnable = !lock
        })
        mTvPlus.setOnClickListener {
            if (mRate <= 2.0f) {
                mRate += 0.1f
                mTvRate.text = String.format("%.1f", mRate)
                mVideoPlayerView.setSpeedPlaying(mRate, true)
            }
        }
        mTvMinus.setOnClickListener {
            if (mRate >= 0.5f) {
                mRate -= 0.1f
                mTvRate.text = String.format("%.1f", mRate)
                mVideoPlayerView.setSpeedPlaying(mRate, true)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        super.onResume()
        isPause = false
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && isPause) {
            mVideoPlayerView.onConfigurationChanged(this, newConfig, orientationUtils)
        }
    }

    override fun onBackPressed() {

        orientationUtils.backToProtVideo()
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
        GSYVideoPlayer.releaseAllVideos()
        orientationUtils.releaseListener()
    }
}