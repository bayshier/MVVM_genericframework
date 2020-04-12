package com.base.mvvm

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.base.helper.annotation.PageStateType
import com.base.helper.annotation.RefreshType
import com.base.helper.extens.ObservableItemField
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    //页面状态
    @SuppressLint("SupportAnnotationUsage")
    @PageStateType
    val pageState = ObservableItemField<Int>()
    //刷新/加载更多状态
    @SuppressLint("SupportAnnotationUsage")
    @RefreshType
    val listState = ObservableItemField<Int>()

    init {
        pageState.set(PageStateType.NORMAL)
        listState.set(RefreshType.NORMAL)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("${javaClass.simpleName}:onCleared()")
    }
}