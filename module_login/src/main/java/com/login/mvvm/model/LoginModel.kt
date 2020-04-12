package com.login.mvvm.model

import com.base.mvvm.BaseModel
import com.login.http.LoginApiManager

class LoginModel:BaseModel() {
    fun  getArticle()= LoginApiManager.apiService.getArticle()
}