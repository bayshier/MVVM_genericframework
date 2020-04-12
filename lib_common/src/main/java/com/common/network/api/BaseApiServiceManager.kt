package com.common.network.api

import com.common.network.HttpUrlConstants
import com.common.network.provider.BaseNetProvider
import com.base.BaseApplication
import com.base.helper.network.NetMgr

object BaseApiServiceManager {
     val apiService by lazy {
        NetMgr.getRetrofit(
            HttpUrlConstants.getBaseUrl(),
            BaseNetProvider(BaseApplication.instance())
        ).create(BaseApiService::class.java)
    }
}