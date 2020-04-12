package com.base

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDex
import com.base.integration.AppDelegate
import com.base.timber.CrashReportingTree
import timber.log.Timber

abstract class BaseApplication : Application(), ViewModelStoreOwner {
    private var mAppDelegate: AppDelegate? = null

    companion object {
        private var instance: Application? = null
        fun instance() = instance ?: throw Throwable("instance 还未初始化完成")
    }

    //TODO tip：可借助 Application 来管理一个应用级 的 SharedViewModel，
    // 实现全应用范围内的 生命周期安全 且 事件源可追溯的 视图控制器 事件通知。
    var mAppViewModelStore: ViewModelStore? = null

    var mFactory: ViewModelProvider.Factory? = null

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        if (mAppDelegate == null) {
            mAppDelegate = AppDelegate(base)
        }
        mAppDelegate?.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        mAppDelegate?.onCreate(this)

        mAppViewModelStore = ViewModelStore()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        mAppDelegate?.onTerminate(this)
    }

    /**
     * SharedViewModel相关方法块
     * ViewModelStoreOwner接口实现
     */
    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore!!
    }

    //TODO tip：视图控制器基类中用来获取 SharedViewModel，
    fun getAppViewModelProvider(activity: Activity): ViewModelProvider {
        return ViewModelProvider((activity.applicationContext as BaseApplication),
                (activity.applicationContext as BaseApplication).getAppFactory(activity))
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory {
        val application = checkApplication(activity)
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }
        return mFactory!!
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
                ?: throw IllegalStateException("Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call.")
    }
    /**
     * ------------------------------------
     */

}