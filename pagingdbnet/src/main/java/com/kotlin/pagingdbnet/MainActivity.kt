package com.kotlin.pagingdbnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.kotlin.pagingdbnet.databinding.ActivityMainBinding
import com.kotlin.pagingdbnet.adapter.RankAppAdapter
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
                rankAppAdapter.submitData(lifecycle, it)
            }
        }


    }
}