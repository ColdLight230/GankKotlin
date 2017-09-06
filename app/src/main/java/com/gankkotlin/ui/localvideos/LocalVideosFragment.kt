package com.gankkotlin.ui.localvideos

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gankkotlin.R
import com.gankkotlin.databinding.FragmentCommonRefreshLayoutBinding
import com.gankkotlin.extension.toast
import com.gankkotlin.ui.common.widget.TabLazyFragment

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/5
 */
class LocalVideosFragment() : TabLazyFragment<FragmentCommonRefreshLayoutBinding>(R.layout.fragment_common_refresh_layout), LocalVideosContract.View {
    override fun initData() {
        binding.refreshLayout.isRefreshing = true
        mPresenter.loadLocalVideos(context)
    }

    val mPresenter by lazy { LocalVideosPresenter(this) }
    val mAdapter by lazy { LocalVideoAdapter(mPresenter.mData) }

    override fun onLoadLocalVideosComplete() {
        binding.refreshLayout.isRefreshing = false
        mAdapter.notifyDataSetChanged()
        toast("video size : ${mPresenter.mData.size}")
    }

    companion object {
        fun newInstance() = LocalVideosFragment()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshLayout.setOnRefreshListener { mPresenter.loadLocalVideos(context) }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = mAdapter

    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        binding.refreshLayout.isRefreshing = false
    }
}