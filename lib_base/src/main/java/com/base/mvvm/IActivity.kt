package com.base.mvvm

import androidx.annotation.LayoutRes

interface IActivity {
    @LayoutRes
    fun getLayoutId(): Int

    fun initView()

    fun initData()

}