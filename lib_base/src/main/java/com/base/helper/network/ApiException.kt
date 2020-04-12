package com.base.helper.network

import java.io.IOException

/***
 * 网络请求错误异常类
 */

open class ApiException(message: String) : IOException(message)