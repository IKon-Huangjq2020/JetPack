package com.hjq.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.hjq.room.adapter.UserListAdapter
import com.hjq.room.databinding.ActivityMainBinding
import com.hjq.room.db.UserDataBase
import com.hjq.room.model.User
import com.hjq.room.model.UserPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding

    private lateinit var mAdapter: UserListAdapter

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mAdapter = UserListAdapter()
        dataBinding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        dataBinding.userRecyclerView.adapter = mAdapter

        initListener()

        //LiveData监听数据改变
//        mViewModel.getUserLiveData().observe(this) { t ->
//            LogUtils.d("data change")
//            if (!t.isNullOrEmpty()) {
//                mAdapter.updateData(t.toMutableList())
//            }
//        }

        //Flow监听数据改变
        mViewModel.getUserFlow().onEach {
            LogUtils.d("data change")
            mAdapter.updateData(it.toMutableList())
        }.launchIn(lifecycleScope)


    }

    private fun initListener() {
        //插入
        dataBinding.insertUser.setOnClickListener {
            val user1 = User("小明", 12)
            val user2 = User("小红", 18)
            val user3 = User("小清", 20)
            mViewModel.insertUsers(user1, user2, user3)
        }

        //删除
        dataBinding.deleteUser.setOnClickListener {
            val user = User(3)
            mViewModel.deleteUser(user)
        }

        //修改
        dataBinding.updateUser.setOnClickListener {
            val user = User(1, "大明明3333", 15)
            user.userPosition = UserPosition()
            mViewModel.updateUser(user)
        }

        //查询
        dataBinding.queryUser.setOnClickListener {
            mViewModel.getAllUser()
                .onEach {
                    mAdapter.updateData(it.toMutableList())
                }.launchIn(lifecycleScope)

        }
    }


}