package com.demo.mvvm.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.BaseActivity
import com.common.core.RouterConstants
import com.demo.R
import com.demo.mvvm.viewmodel.DemoViewModel

@Route(path = RouterConstants.DEMO_ACTIVITY)
class DemoActivity : BaseActivity<DemoViewModel>() {
    override fun providerVMClass() = DemoViewModel::class.java
    override fun getLayoutId() = R.layout.demo_layout

    override fun initView() {

    }

    override fun initData() {

    }


}