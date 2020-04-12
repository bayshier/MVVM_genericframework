package com.common.utils

import com.alibaba.android.arouter.launcher.ARouter
import com.common.core.RouterConstants

/***
 * ARouter路由跳转
 */
object NavigationUtils {

    /**
     * 首页
     */
    fun goMainActivity() {
        ARouter.getInstance().build(RouterConstants.MAIN_ACTIVITY).navigation()
    }

    /**
     * 登录页面
     */
    fun goLoginActivity() {
        ARouter.getInstance().build(RouterConstants.LOGIN_ACTIVITY).navigation()
    }

    /**
     *个人中心
     */
    fun goMineActivity(){
        ARouter.getInstance().build(RouterConstants.MINE_ACTIVITY).navigation()
    }

    /**
     * WebView
     */
    const val WEB_URL = "url"
    const val WEB_TITLE = "title"
    fun goWebActivity(url: String, title: String) {
        ARouter.getInstance().build(RouterConstants.WEB_ACTIVITY)
                .withString(WEB_URL, url)
                .withString(WEB_TITLE, title)
                .navigation()
    }

}