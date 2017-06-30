package com.gankkotlin.ui.articledetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gankkotlin.Constants
import com.gankkotlin.R
import com.gankkotlin.databinding.FragmentCommonRefreshLayoutBinding
import com.gankkotlin.extension.RecyclerViewDivider
import com.gankkotlin.ui.common.base.BaseFragment
import java.util.*

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/24
 */
class ArticleDetailFragment : BaseFragment<FragmentCommonRefreshLayoutBinding>(R.layout.fragment_common_refresh_layout), ArticleDetailContract.View {

    val mPresenter by lazy { ArticleDetailPresenter(this) }
    val date by lazy { arguments.getSerializable(Constants.IntentKey.KEY_DATE) as Date }
    val mAdapter: ArticleDetailAdapter by lazy { ArticleDetailAdapter(mPresenter.dailyContent) }

    companion object {
        fun newInstance(date: Date?): Fragment {
            val args = Bundle()
            args.putSerializable(Constants.IntentKey.KEY_DATE, date)
            val fragment = ArticleDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(RecyclerViewDivider(dividerDrawable = ContextCompat.getDrawable(context, R.color.divider)))

        val refresh = { mPresenter.loadDailyData(date) }

        binding.refreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.light_green))
        binding.refreshLayout.setOnRefreshListener(refresh)

        refresh.invoke()
    }

    override fun loadDailyDataComplete() {
        binding.refreshLayout.isRefreshing = false
        mAdapter.notifyDataSetChanged()
    }

}