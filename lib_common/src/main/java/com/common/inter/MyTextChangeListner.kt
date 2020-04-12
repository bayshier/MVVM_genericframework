package com.common.inter

import android.text.Editable
import android.text.TextWatcher

abstract class MyTextChangeListner: TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }
    override fun afterTextChanged(s: Editable?) {
    }
}