package com.jetpack.pagingdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.jetpack.pagingdb.adapter.UserAdapter
import com.jetpack.pagingdb.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val dataBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dataBinding.root)

        dataBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter()
        dataBinding.recyclerView.adapter = userAdapter

        lifecycleScope.launchWhenResumed {
            @OptIn(ExperimentalCoroutinesApi::class)
            viewModel.allUsers.collectLatest { userAdapter.submitData(it) }
        }

        lifecycleScope.launch {
            userAdapter.loadStateFlow.collectLatest { state ->
                LogUtils.d("state==$state")
            }
        }

    }
}