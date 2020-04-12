package com.app.mvvm.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.app.R
import com.app.mvvm.viewmodel.MainViewModel
import com.common.base.BaseActivity
import com.common.core.RouterConstants

@Route(path = RouterConstants.MAIN_ACTIVITY)
class MainActivity : BaseActivity<MainViewModel>() {
    override fun providerVMClass()=MainViewModel::class.java
    override fun getLayoutId() = R.layout.activity_main_nav
    override fun initView() {

    }

    override fun initData() {

    }
}

