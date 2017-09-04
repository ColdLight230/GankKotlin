package com.gankkotlin.ui.videoplayer

import android.os.Bundle
import android.view.View
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.databinding.FragmentVideoPlayerBinding
import com.gankkotlin.ui.common.base.BaseFragment
import com.gankkotlin.widget.VideoPlayerView
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/2
 */
class VideoPlayerFragment : BaseFragment<FragmentVideoPlayerBinding>(R.layout.fragment_video_player) {

    val mUrl by lazy { arguments.getString(Constants.FIRST_INTENT_PARAM) }
    val mTitle by lazy { arguments.getString(Constants.SECOND_INTENT_PARAM) }
    val mVideoPlayerView by lazy { binding.videoPlayerView as VideoPlayerView }

    companion object {
        fun newInstance(url: String, title: String): VideoPlayerFragment {
            val fragment = VideoPlayerFragment()
            val args = Bundle()
            args.putString(Constants.FIRST_INTENT_PARAM, url)
            args.putString(Constants.SECOND_INTENT_PARAM, title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVideoPlayerView.setUp(mUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, mTitle)
        mVideoPlayerView.thumbImageView.setImageResource(R.mipmap.ic_launcher)
    }

    fun releaseAllVideos() {
        mVideoPlayerView.release()
    }

}