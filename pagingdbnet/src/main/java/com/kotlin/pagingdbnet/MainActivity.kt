package com.kotlin.pagingdbnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.kotlin.pagingdbnet.databinding.ActivityMainBinding
import com.kotlin.pagingdbnet.adapter.AppListAdapter
import com.kotlin.pagingdbnet.adapter.FooterAdapter
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val mViewModel by viewModels<MainViewModel>()

    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val appListAdapter = AppListAdapter(this)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter =
            appListAdapter.withLoadStateFooter(FooterAdapter(appListAdapter, this))

        lifecycleScope.launchWhenCreated {
            mViewModel.loadNews().collectLatest {
                LogUtils.d("receive===$it")
                appListAdapter.submitData(lifecycle, it)
                mBinding.swipeRefreshLayout.isEnabled = false
            }
        }

        lifecycleScope.launchWhenCreated {
            appListAdapter.loadStateFlow.collectLatest { state ->
                mBinding.swipeRefreshLayout.isRefreshing = state.refresh is LoadState.Loading
            }
        }

    }
}