package com.demo.mine.mvvm.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.BaseActivity
import com.common.core.RouterConstants
import com.demo.R
import com.demo.mine.mvvm.viewmodel.MineViewModel

@Route(path = RouterConstants.MINE_ACTIVITY)
class MineActivity : BaseActivity<MineViewModel>() {
    override fun providerVMClass() = MineViewModel::class.java
    override fun getLayoutId() = R.layout.mine_fragment_mine

    override fun initView() {

    }

    override fun initData() {

    }


}
