package com.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.base.BaseApplication
import com.base.mvvm.IActivity
import com.base.mvvm.IView
import com.base.widget.LoadDialog
import com.common.bridge.callback.SharedViewModel


/***
 * Fragment的父类
 */
abstract class BaseFragment<VM : ViewModel> : Fragment(), IView, IActivity {
    //上下文
    protected lateinit var mContext: Context
    lateinit var mViewModel: VM
    protected var mSharedViewModel: SharedViewModel? = null

    //数据是否加载标识
    private var isDataInitiated = false
    //view是否加载标识
    private var isViewInitiated = false
    //fragment是否显示
    private var isVisibleToUser = false

    private val progressDialog: LoadDialog by lazy {
        LoadDialog.create(mContext)
    }

    /**
     * 是否懒加载
     * true:是
     * false:不(默认)
     */
    protected open fun lazyLoad() = false

    /**
     * 是否fragment显示的时候都重新加载数据
     */
    protected open fun reLoad() = false

    protected var mActivity: AppCompatActivity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity ?: throw Exception("activity is null")
        initVM()
        initView()
        //判断是否懒加载
        if (lazyLoad()) {
            //将view加载的标识设置为true
            isViewInitiated = true
            prepareData()
        } else {
            initData()
        }
    }

    /**
     * fragment是否显示当前界面
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareData()
    }

    /**
     * 懒加载的方法
     */
    private fun prepareData() {
        //通过判断各种标识去进行数据加载
        if (isVisibleToUser && isViewInitiated && !isDataInitiated) {
            initData()
            if (reLoad()) return
            isDataInitiated = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), null, false)
    }

    abstract fun providerVMClass(): Class<VM>?
    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
        }

        mSharedViewModel = getAppViewModelProvider()?.get(SharedViewModel::class.java)

    }


    /**
     * 显示loading框
     */
    override fun showLoading(message: String) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    /**
     * 隐藏loading框
     */
    override fun hideLoading() {
        progressDialog.dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dismiss()
    }

    protected open fun getAppViewModelProvider(): ViewModelProvider? {
        return (mActivity?.getApplicationContext() as BaseApplication).getAppViewModelProvider(mActivity!!)
    }
}