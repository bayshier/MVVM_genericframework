package com.base.mvvm

import androidx.lifecycle.LifecycleOwner

interface IView : LifecycleOwner {
    fun showLoading(message: String = "请稍后...")

    fun hideLoading()
}