package com.gankkotlin.widget

import android.content.Context
import android.util.AttributeSet
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/2
 */
class VideoPlayerView : StandardGSYVideoPlayer {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
}