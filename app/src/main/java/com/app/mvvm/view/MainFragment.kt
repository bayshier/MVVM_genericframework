package com.app.mvvm.view

import android.annotation.SuppressLint
import com.alibaba.android.arouter.facade.annotation.Route
import com.app.R
import com.app.mvvm.viewmodel.MainViewModel
import com.common.base.BaseFragment
import com.common.core.RouterConstants
import com.example.navigationdemo.adapter.MainViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

@Route(path = RouterConstants.MAIN_FRAGMENT)
class MainFragment : BaseFragment<MainViewModel>() {

    override fun providerVMClass() = MainViewModel::class.java
    override fun getLayoutId() = R.layout.fragment_main

    override fun initView() {
        main_view_pager.adapter = MainViewPagerAdapter(this)

        TabLayoutMediator(bottom_btn_view, main_view_pager) { tab, position ->
            tab.text = Card.DECK[position].toString()
        }.attach()

    }

    override fun initData() {

    }

    @SuppressLint("TimberArgCount")
    private fun setCurrentItem(position: Int) {
        Timber.i("setCurrentItem", position.toString())
        main_view_pager.setCurrentItem(position, false)
    }
}

