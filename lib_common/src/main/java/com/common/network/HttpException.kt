package com.common.network

import com.common.network.bean.BaseBean
import com.base.helper.network.ApiException


class HttpException(val baseBean: BaseBean<Any>) : ApiException(baseBean.errorMsg)