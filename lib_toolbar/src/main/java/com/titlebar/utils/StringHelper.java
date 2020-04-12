package com.titlebar.utils;

import androidx.annotation.Nullable;

public class StringHelper {

    public static boolean isNullOrEmpty(@Nullable CharSequence string) {
        return string == null || string.length() == 0;
    }

}
