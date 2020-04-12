package com.common.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class UserInfoBean(
        @SerializedName("mobile") val mobile: String = "",
        @SerializedName("name") val name: String = ""
) : Parcelable

