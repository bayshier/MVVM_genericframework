package com.base.integration

import android.app.Application
import android.content.Context

/***
 * Application生命周期接口
 */

interface AppLifeCycles {

    fun attachBaseContext(base: Context)

    fun onCreate(application: Application)

    fun onTerminate(application: Application)
}