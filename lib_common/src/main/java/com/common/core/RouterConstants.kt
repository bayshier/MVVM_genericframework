package com.common.core

object RouterConstants {
    private const val HOME = "/home"
    private const val LOGIN = "/login"
    private const val MINE = "/mine"
    private const val WEB = "/web"
    private const val DEMO = "/demo"
    /**
     * 主界面
     */
    const val MAIN_ACTIVITY = "$HOME/MainActivity"
    const val MAIN_FRAGMENT = "$HOME/MainFragment"
    /**
     * 登录
     */
    const val LOGIN_ACTIVITY = "$LOGIN/loginActivity"
    const val LOGIN_FRAGMENT = "$LOGIN/loginFragment"
    /**
     * web页面
     */
    const val WEB_ACTIVITY = "$WEB/WebActivity"

    /**
     * 个人中心
     */
    const val MINE_ACTIVITY = "$MINE/MineActivity"
    const val MINE_FRAGMENT = "$MINE/MineFragment"

    /**
     * demo
     */
    const val DEMO_ACTIVITY = "$DEMO/DemoActivity"
}