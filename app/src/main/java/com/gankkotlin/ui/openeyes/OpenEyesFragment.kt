package com.gankkotlin.ui.openeyes

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
 * 时    间：2017/8/30
 */
class OpenEyesFragment : TabLazyFragment<FragmentCommonRefreshLayoutBinding>(R.layout.fragment_common_refresh_layout), OpenEyesContract.View {

    val mPresenter by lazy { OpenEyesPresenter(this) }
    val refresh = { mPresenter.refresh() }
    val mAdapter by lazy { OpenEyesAdapter(mPresenter.data) }

    companion object {
        fun newInstance() = OpenEyesFragment()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onLoadMoreComplete() {
        mAdapter.loadMoreComplete()
    }

    override fun initData() {
        binding.refreshLayout.isRefreshing = true
        refresh.invoke()
    }
}