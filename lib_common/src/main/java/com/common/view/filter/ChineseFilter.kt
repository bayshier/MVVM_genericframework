package com.common.view.filter

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern



class ChineseFilter : InputFilter{
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {

            val regex =  "[`~!@#_$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）— +|{}【】‘；：”“’。，、？]"
            val p = Pattern.compile(regex)
            val m = p.matcher(source.toString())
        return if (m.find()) "" else null

    }

}