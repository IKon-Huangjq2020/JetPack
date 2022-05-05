package com.hjq.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hjq.room.R
import com.hjq.room.databinding.UserListItemBinding
import com.hjq.room.model.User

/**
 *    desc   :
 *    date   : 2022/5/5 13:33
 */
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListAdapterVH>() {

    private var mUserList = mutableListOf<User>()

    class UserListAdapterVH(view: View, val dataBinding: UserListItemBinding) :
        RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapterVH {

        val dataBinding = DataBindingUtil.inflate<UserListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.user_list_item,
            parent,
            false
        )
        return UserListAdapterVH(dataBinding.root, dataBinding)


    }

    override fun onBindViewHolder(holder: UserListAdapterVH, position: Int) {
        val mUser = mUserList[position]
        holder.dataBinding.mUser = mUser
    }

    fun updateData(mutableList: MutableList<User>) {
        mUserList.clear()
        mUserList.addAll(mutableList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mUserList.size
}