package com.hjq.viewbinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *    desc   :
 *    date   : 2022/5/8 12:37
 */
object BindingAdapters {

    @BindingAdapter(value = ["app:loadImageViewByUrl", "app:loadError"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
        if (url == null) {
            imageView.setImageDrawable(placeHolder);
        } else {
            Glide.with(imageView).load(url).error(placeHolder).into(imageView)
        }
    }
}