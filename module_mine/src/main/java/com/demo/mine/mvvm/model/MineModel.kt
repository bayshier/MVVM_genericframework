package com.demo.mine.mvvm.model

import com.base.mvvm.BaseModel
import com.demo.mine.http.MineApiManager

class MineModel:BaseModel() {
    fun getProjectList(pageNum:Int,cid:Int)= MineApiManager.apiService.getProjectList(pageNum,cid)
}