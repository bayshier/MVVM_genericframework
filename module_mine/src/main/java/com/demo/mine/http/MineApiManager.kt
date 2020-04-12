package com.demo.mine.http

import com.base.BaseApplication
import com.base.helper.network.NetMgr
import com.common.network.HttpUrlConstants
import com.common.network.provider.BaseNetProvider

object MineApiManager {
    val apiService by lazy {
        NetMgr.getRetrofit(
                HttpUrlConstants.getBaseUrl(),
                BaseNetProvider(BaseApplication.instance())
        ).create(ApiService::class.java)
    }
}