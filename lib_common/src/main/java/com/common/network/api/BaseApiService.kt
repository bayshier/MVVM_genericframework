package com.common.network.api

import com.common.bean.UserInfoBean
import com.common.network.bean.BaseBean
import io.reactivex.Single
import retrofit2.http.POST

interface BaseApiService {
    @POST("common/userInfo/queryUserInfo")
    fun queryUserInfo(): Single<BaseBean<UserInfoBean>>
}