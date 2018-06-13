package com.wangzu.kotlin.ui.bean

/**
 * Created by AnJiuZhe on 2018/6/6 0006.
 * Email 1573335865@qq.com
 */
data class PhotosResult(var errno: String, var errmsg: String, var consume: String, var total: String,
                        var data: MutableList<Photo>) {
        data class Photo(var pid: String, var cid: String, var dl_cnt: String, var c_t: String,
                         var imgcut: String, var url: String, var tempdata: String, var fav_total: String)
}