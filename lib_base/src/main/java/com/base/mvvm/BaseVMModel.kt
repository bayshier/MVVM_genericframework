package com.base.mvvm

abstract class BaseVMModel< M:BaseModel> : BaseViewModel() {
    abstract var  mModel: M
}