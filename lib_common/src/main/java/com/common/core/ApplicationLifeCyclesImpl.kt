package com.common.core

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.base.integration.AppLifeCycles
import com.common.BuildConfig

import timber.log.Timber

class ApplicationLifeCyclesImpl : AppLifeCycles {
    override fun attachBaseContext(base: Context) {
        Timber.i("Application attachBaseContext")
    }

    override fun onCreate(application: Application) {
        if (BuildConfig.DEBUG) {//debug模式下初始化
            Timber.plant(Timber.DebugTree())
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application) // 尽可能早,推荐在Application中初始化

    }

    override fun onTerminate(application: Application) {
        Timber.i("${application.javaClass.simpleName} onCreate")
    }
}