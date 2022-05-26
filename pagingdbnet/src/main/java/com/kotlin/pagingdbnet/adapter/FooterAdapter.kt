package com.kotlin.pagingdbnet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.kotlin.pagingdbnet.databinding.LoadStateHeaderBinding

/**
 *    desc   :
 *    date   : 2022/5/26 21:41
 */
class FooterAdapter(private val adapter: AppListAdapter, private val context: Context) :
    LoadStateAdapter<FooterAdapter.FooterViewHolder>() {


    class FooterViewHolder(
        private val viewBinding: LoadStateHeaderBinding,
        val retryCallBack: () -> Unit
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: LoadState) {
            viewBinding.apply {
                progress.isVisible = data is LoadState.Loading
                retryBtn.isVisible = data is LoadState.Error
                retryBtn.setOnClickListener {
                    retryCallBack()
                }
                errorTv.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorTv.text = (data as? LoadState.Error)?.error?.message
            }

        }
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        LogUtils.d("loadState===$loadState")
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        LogUtils.d("onCreateViewHolder===$loadState")
        val viewBinding =
            LoadStateHeaderBinding.inflate(LayoutInflater.from(context), parent, false)
        return FooterViewHolder(viewBinding) { adapter.retry() }
    }
}
