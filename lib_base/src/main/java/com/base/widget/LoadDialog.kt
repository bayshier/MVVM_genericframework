package com.base.widget

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.base.widget.dialog.CommonDialog
import com.base.R

@SuppressLint("InflateParams")
class LoadDialog private constructor(private val context: Context) {
    private val mCommonDialog by lazy {
        CommonDialog.Builder(context)
                .setContentView(contentView)
                .setCancelableOnTouchOutside(false)
                .create()
    }

    private val contentView by lazy {
        LayoutInflater.from(context).inflate(R.layout.public_dialog_load, null)
    }

    private val titleTv by lazy {
        contentView.findViewById<TextView>(R.id.dialog_tv)
    }

    fun show() {
        if (mCommonDialog.isShowing) return
        mCommonDialog.show()
    }

    fun setMessage(message: String) {
        titleTv.text = message
    }

    fun dismiss() {
        if (!mCommonDialog.isShowing) return
        mCommonDialog.dismiss()
    }

    companion object {
        fun create(context: Context): LoadDialog {
            return LoadDialog(context)
        }
    }
}