package com.wangzu.kotlin.ui.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by AnJiuZhe on 2018/6/7 0007.
 * Email 1573335865@qq.com
 */
data class PhotoSearch(var errno: String, var errmsg: String, var consume: String, var total: String,
                       var data: MutableList<Search>) {
    data class Search(var id: String, var class_id: String,var resolution: String, var url_mobile: String,
                      var url: String, var url_thumb: String,var url_mid: String, var download_times: String,
                      var imgcut: String, var tag: String,var create_time: String, var update_time: String,
                      var utag: String, var img_1600_900: String,var img_1440_900: String, var img_1366_768: String,
                      var img_1280_800: String, var img_1280_1024: String,var img_1024_768: String)  : Parcelable{
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(class_id)
            parcel.writeString(resolution)
            parcel.writeString(url_mobile)
            parcel.writeString(url)
            parcel.writeString(url_thumb)
            parcel.writeString(url_mid)
            parcel.writeString(download_times)
            parcel.writeString(imgcut)
            parcel.writeString(tag)
            parcel.writeString(create_time)
            parcel.writeString(update_time)
            parcel.writeString(utag)
            parcel.writeString(img_1600_900)
            parcel.writeString(img_1440_900)
            parcel.writeString(img_1366_768)
            parcel.writeString(img_1280_800)
            parcel.writeString(img_1280_1024)
            parcel.writeString(img_1024_768)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Search> {
            override fun createFromParcel(parcel: Parcel): Search {
                return Search(parcel)
            }

            override fun newArray(size: Int): Array<Search?> {
                return arrayOfNulls(size)
            }
        }

    }
}