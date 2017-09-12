package com.gankkotlin.ui.videoplayer

import android.os.Bundle
import android.view.View
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.databinding.FragmentVideoPlayerBinding
import com.gankkotlin.ui.common.base.BaseFragment
import com.gankkotlin.utils.ImageLoader
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/2
 */
class VideoPlayerFragment : BaseFragment<FragmentVideoPlayerBinding>(R.layout.fragment_video_player) {

    val mUrl: String by lazy { arguments.getString(Constants.FIRST_INTENT_PARAM) }
    val mTitle: String by lazy { arguments.getString(Constants.SECOND_INTENT_PARAM) }
    val mImageUrl: String? by lazy { arguments.getString(Constants.THIRD_INTENT_PARAM) }
    val mVideoPlayerView by lazy { binding.videoPlayerView as JCVideoPlayerStandard }

    companion object {
        fun newInstance(url: String?, title: String?, imageUrl: String?): VideoPlayerFragment {
            val fragment = VideoPlayerFragment()
            val args = Bundle()
            args.putString(Constants.FIRST_INTENT_PARAM, url)
            args.putString(Constants.SECOND_INTENT_PARAM, title)
            args.putString(Constants.THIRD_INTENT_PARAM, imageUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVideoPlayerView.setUp(mUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, mTitle)
        ImageLoader.loadImage(activity, mImageUrl, R.drawable.grey_rect, mVideoPlayerView.thumbImageView)
    }

    fun releaseAllVideos() {
        mVideoPlayerView.release()
    }

}