package com.hjq.viewbinding

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.ToastUtils
import com.hjq.viewbinding.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 *    desc   :
 *    date   : 2022/5/8 12:34
 */
class MainViewModel : ViewModel() {

    val searchTextField = ObservableField<String>("")
    val searchTextLiveData = MutableLiveData<String>("")
    var imageUrl = ""


    var user = User()


    fun searchText() {
        val searchText = searchTextField.get()
        if (TextUtils.isEmpty(searchText)) {
            ToastUtils.showShort("请输入要搜索的内容")
            return
        }
        ToastUtils.showShort("你输入的内容是:${searchText}")
    }
}