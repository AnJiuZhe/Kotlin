package com.wangzu.kotlin.ui.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by AnJiuZhe on 2018/6/6 0006.
 * Email 1573335865@qq.com
 */
data class CategroyResult(var errno: String, var errmsg: String, var consume: String, var total: String,
                          var data: MutableList<Categroy>) {
    data class Categroy(var id: String, var name: String, var totalcnt: String,
                        var create_time: String, var displaytype: String, var tempdata: String) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
            parcel.writeString(totalcnt)
            parcel.writeString(create_time)
            parcel.writeString(displaytype)
            parcel.writeString(tempdata)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Categroy> {
            override fun createFromParcel(parcel: Parcel): Categroy {
                return Categroy(parcel)
            }

            override fun newArray(size: Int): Array<Categroy?> {
                return arrayOfNulls(size)
            }
        }
    }
}