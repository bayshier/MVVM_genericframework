package com.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.base.BaseApplication
import com.base.mvvm.BaseViewModel
import com.base.mvvm.IActivity
import com.base.mvvm.IView
import com.base.widget.LoadDialog
import com.common.bridge.callback.SharedViewModel

/***
 * BaseActivity
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), IView, IActivity {

    lateinit var mViewModel: VM

    abstract fun providerVMClass(): Class<VM>?

    protected var mSharedViewModel: SharedViewModel? = null

    private val progressDialog: LoadDialog by lazy {
        LoadDialog.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initVM()
        initView()
        initData()
    }

    private fun initVM() {

        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
        }

        mSharedViewModel = getAppViewModelProvider()?.get(SharedViewModel::class.java)

    }

    override fun showLoading(message: String) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dismiss()
    }

    protected open fun getAppViewModelProvider(): ViewModelProvider? {
        return (applicationContext as BaseApplication).getAppViewModelProvider(this)
    }

}