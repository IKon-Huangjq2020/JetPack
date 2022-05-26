package com.kotlin.pagingnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.kotlin.pagingnet.adapter.RankAppAdapter
import com.kotlin.pagingnet.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val mViewModel by viewModels<MainViewModel>()

    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val rankAppAdapter = RankAppAdapter()
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = rankAppAdapter

        lifecycleScope.launchWhenCreated {
            mViewModel.loadNews().collectLatest {
                LogUtils.d("receive===$it")
                rankAppAdapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            rankAppAdapter.loadStateFlow.collectLatest {

            }
        }

    }
}