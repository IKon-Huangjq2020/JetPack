package com.kotlin.pagingdbnet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kotlin.pagingdbnet.R
import com.kotlin.pagingdbnet.databinding.AppListItemBinding
import com.kotlin.pagingdbnet.model.AppData

/**
 *    desc   :
 *    date   : 2022/5/24 21:53
 */
class AppListAdapter(val context: Context) : PagingDataAdapter<AppData, AppListAdapter.AppViewHolder>(
    object : DiffUtil.ItemCallback<AppData>() {
        override fun areItemsTheSame(oldItem: AppData, newItem: AppData): Boolean {
            return oldItem.apkid == newItem.apkid
        }

        override fun areContentsTheSame(oldItem: AppData, newItem: AppData): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var requestOptions: RequestOptions? = null

    class AppViewHolder(val binding: AppListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val appData = getItem(position)
        appData?.let {
            if (requestOptions == null) requestOptions = getOptions()
            Glide.with(holder.binding.appLogo).load(it.logo_url)
                .placeholder(R.drawable.ic_launcher_background)
                .fitCenter()
                .apply(requestOptions!!)
                .into(holder.binding.appLogo)
            holder.binding.appNumber.text = "${position + 1}"
            holder.binding.appName.text = it.name
            holder.binding.appDesc.text = it.single_word
            holder.binding.appSize.text = it.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding = AppListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return AppViewHolder(binding)
    }

    private fun getOptions(): RequestOptions {
        return RequestOptions.bitmapTransform(RoundedCorners(20))
    }

}