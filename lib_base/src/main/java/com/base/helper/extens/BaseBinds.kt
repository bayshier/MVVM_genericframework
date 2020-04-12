package com.base.helper.extens

import android.view.View
import androidx.databinding.BindingAdapter

/***
 * 通用的bindingAdapter类
 * view的显示隐藏
 * bind:visibility="@{true/false}"
 * true:显示
 * false:隐藏
 */
@BindingAdapter(value = ["visibility"])
fun bindVisibility(v: View, visibility: Boolean?) {
    v.visibility = if (visibility == true) View.VISIBLE else View.GONE
}