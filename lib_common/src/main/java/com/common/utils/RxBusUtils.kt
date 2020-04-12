package com.common.utils

import com.blankj.rxbus.RxBus

object RxBusUtils {

    /**
     * 取消RxBus的监听
     */
    fun cancelSubscriber(subscriber: Any) {
        RxBus.getDefault().unregister(subscriber)
    }

    private const val HOME_REFRESH = "home_refresh"
    /**
     * 发送首页刷新
     */
    fun postHomeRefresh(refresh: Boolean) {
        RxBus.getDefault().post(refresh, HOME_REFRESH)
    }
    /**
     * 监听首页刷新
     */
    fun subscribeHomeRefresh(subscriber: Any, callback: (Boolean) -> Unit) {
        RxBus.getDefault().subscribe(subscriber, HOME_REFRESH, object : RxBus.Callback<Boolean>() {
            override fun onEvent(isLoginSuccess: Boolean?) {
                isLoginSuccess?.let(callback)
            }
        })
    }
}
