package com.login.mvvm.viewmodel

import com.app.mvvm.bean.ArticleBean
import com.base.helper.extens.ObservableItemField
import com.base.helper.extens.async
import com.base.mvvm.BaseVMModel
import com.common.network.bean.BaseBean
import com.login.mvvm.model.LoginModel
import io.reactivex.Single
import timber.log.Timber

class LoginViewModel: BaseVMModel<LoginModel>() {

    override var mModel: LoginModel=LoginModel()

    val chapterName = ObservableItemField<String>()
    val link = ObservableItemField<String>()
    fun getArticle(): Single<BaseBean<ArticleBean>> {
        return mModel
                .getArticle()
                .async()
                .doOnSuccess {
                    chapterName.set(it.data.datas[0].chapterName)
                    link.set(it.data.datas[0].link)
                }
                .doOnError {
                    Timber.d("doOnError")
                }
    }

}