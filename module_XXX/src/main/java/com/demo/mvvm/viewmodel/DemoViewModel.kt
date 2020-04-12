package com.demo.mvvm.viewmodel

import com.base.helper.extens.ObservableItemField
import com.base.helper.extens.async
import com.base.helper.network.EmptyException
import com.base.mvvm.BaseVMModel
import com.common.network.bean.BaseBean
import com.demo.mvvm.bean.MineBean
import com.demo.mvvm.bean.SubData
import com.demo.mvvm.model.DemoModel
import io.reactivex.Single

class DemoViewModel : BaseVMModel<DemoModel>() {
    override var mModel: DemoModel=DemoModel()
    private var page = 1
    fun getProjectList(isRefresh: Boolean, cid: Int): Single<BaseBean<MineBean>> {
        return mModel
            .getProjectList(
                if (isRefresh) {
                    page = 1
                    page
                } else page, cid
            )
            .async()
            .doOnSuccess {
                if (it.data.datas.isNotEmpty()) {
                    val list = mutableListOf<MineItemViewModel>()
                    it.data.datas.forEach { orderBean: SubData ->
                        list.add(MineItemViewModel(orderBean))
                    }
                    page++
                    if (isRefresh) {
                    } else {
                    }
                } else {
                    throw EmptyException()
                }
            }
    }
}

class MineItemViewModel(bean: SubData) {
    val chapterName = ObservableItemField<String>()
    val desc = ObservableItemField<String>()
    val data = bean

    init {
        chapterName.set(bean.chapterName)
        desc.set(bean.desc)
    }
}