package com.app.http

import com.common.network.HttpUrlConstants
import com.common.network.provider.BaseNetProvider
import com.base.BaseApplication
import com.base.helper.network.NetMgr

object HomeApiManager {
    val apiService by lazy {
        NetMgr.getRetrofit(
                HttpUrlConstants.getBaseUrl(),
                BaseNetProvider(BaseApplication.instance())
        ).create(ApiService::class.java)
    }
}