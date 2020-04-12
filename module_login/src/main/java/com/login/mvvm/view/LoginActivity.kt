package com.login.mvvm.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterConstants
import com.common.base.BaseActivity
import com.login.R
import com.login.mvvm.viewmodel.LoginViewModel

@Route(path = RouterConstants.LOGIN_ACTIVITY)
class LoginActivity: BaseActivity<LoginViewModel>() {
    override fun providerVMClass()=LoginViewModel::class.java
    override fun getLayoutId()= R.layout.login_activity_login

    override fun initView() {
    }

    override fun initData() {
    }
}