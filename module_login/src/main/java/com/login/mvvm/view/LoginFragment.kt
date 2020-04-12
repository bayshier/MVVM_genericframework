package com.login.mvvm.view

import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.base.helper.extens.bindDialogOrLifeCycle
import com.common.base.BaseFragment
import com.common.core.RouterConstants
import com.common.network.dealResult
import com.common.utils.NavigationUtils
import com.login.R
import com.login.mvvm.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_activity_login.*

@Route(path = RouterConstants.LOGIN_FRAGMENT)
class LoginFragment : BaseFragment<LoginViewModel>() {
    override fun providerVMClass() = LoginViewModel::class.java
    override fun getLayoutId() = R.layout.login_activity_login

    override fun initView() {
        btn.setOnClickListener {
            mViewModel.run {
                activity?.let { it1 ->
                    this.getArticle()
                            .bindDialogOrLifeCycle(this@LoginFragment)
                            .dealResult(it1)
                }
            }

        }
        btn_login.setOnClickListener {
            NavigationUtils.goLoginActivity()
        }

        btn_mine.setOnClickListener {
            NavigationUtils.goMineActivity()
        }

    }

    override fun initData() {
        mViewModel.chapterName.observe(this, Observer {
            it?.let { name.text = it }
        })
        mViewModel.link.observe(this, Observer {
            it?.let { desc.text = it }
        })
    }
}