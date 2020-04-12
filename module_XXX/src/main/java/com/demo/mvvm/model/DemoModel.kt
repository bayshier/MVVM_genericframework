package com.demo.mvvm.model

import com.base.mvvm.BaseModel
import com.demo.http.DemoApiManager

class DemoModel:BaseModel() {
    fun getProjectList(pageNum:Int,cid:Int)=DemoApiManager.apiService.getProjectList(pageNum,cid)
}