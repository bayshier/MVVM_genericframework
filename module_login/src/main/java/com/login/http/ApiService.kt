package com.login.http

import com.app.mvvm.bean.ArticleBean
import com.common.network.bean.BaseBean
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    /**
     * 判断是否上线
     */
    @GET("article/list/1/json")
    fun getArticle(): Single<BaseBean<ArticleBean>>
}