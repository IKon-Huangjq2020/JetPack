package com.hjq.viewbinding

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.hjq.viewbinding.databinding.ActivityMainBinding
import com.hjq.viewbinding.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = mViewModel

        initData()
        mViewModel.searchTextField.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                val changeText = mViewModel.searchTextField.get()
                LogUtils.d("Field数据发生了改变：${changeText}")
            }
        })

        mViewModel.searchTextLiveData.observe(this, { text ->
            LogUtils.d("LiveData数据发生了改变：${text}")
        })
    }

    private fun initData() {
        mViewModel.user = User().apply {
            name = "小明"
            age = 18
        }
        mViewModel.imageUrl =
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F1115%2F102621102550%2F211026102550-7-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654605476&t=3d8c3d2843a558de05bf01196cc8901f"
//
//        setImageUrl(
//            imageView = binding.userImage,
//            url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F1115%2F102621102550%2F211026102550-7-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654605476&t=3d8c3d2843a558de05bf01196cc8901f",
//            placeHolder = resources.getDrawable(R.drawable.ic_launcher_background)
//        )
    }


    private fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
        if (url == null) {
            imageView.setImageDrawable(placeHolder);
        } else {
            Glide.with(imageView).load(url).error(placeHolder).into(imageView)
        }
    }
}