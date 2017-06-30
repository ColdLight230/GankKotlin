package com.gankkotlin.ui.document

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gankkotlin.R
import com.gankkotlin.databinding.FragmentCommonRefreshLayoutBinding
import com.gankkotlin.ui.common.widget.TabLazyFragment

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/8
 */
class DocumentFragment : TabLazyFragment<FragmentCommonRefreshLayoutBinding>(R.layout.fragment_common_refresh_layout), DocumentContract.View {
    override fun initData() {
        refresh.invoke()
        binding.refreshLayout.isRefreshing = true
    }

    val mPresenter by lazy { DocumentPresenter(this) }
    val mAdapter by lazy { DocumentAdapter(mPresenter.mDatas) }
    val refresh = { mPresenter.refresh() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.setEnableLoadMore(true)
        mAdapter.setOnLoadMoreListener { mPresenter.loadMore() }
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mAdapter

        binding.refreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.light_green))
        binding.refreshLayout.setOnRefreshListener(refresh)

    }

    override fun onRefreshComplete() {
        mAdapter.notifyDataSetChanged()
        binding.refreshLayout.isRefreshing = false
    }

    override fun onLoadMoreComplete(positionStart: Int, itemCount: Int) {
        mAdapter.notifyItemRangeInserted(positionStart, itemCount)
        mAdapter.loadMoreComplete()
    }

    companion object {
        fun newInstance() = DocumentFragment()
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        binding.refreshLayout.isRefreshing = false
    }
}