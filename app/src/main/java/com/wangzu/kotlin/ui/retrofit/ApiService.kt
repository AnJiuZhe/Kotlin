package com.wangzu.kotlin.ui.retrofit

import com.wangzu.kotlin.ui.bean.CategroyResult
import com.wangzu.kotlin.ui.bean.PhotoSearch
import com.wangzu.kotlin.ui.bean.PhotosResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AnJiuZhe on 2018/6/6 0006.
 * Email 1573335865@qq.com
 */
interface ApiService {

    companion object {
        val baseUrl: String
            get() = "http://wallpaper.apc.360.cn/"
    }

    //获取图片分类
    @GET("index.php?c=WallPaperAndroid&a=getAllCategories")
    fun getCategoryResult(): Observable<CategroyResult>

    //获取某类别图片
    @GET("index.php?c=WallPaperAndroid&a=getAppsByCategory")
    fun getPhotosResult(@Query("cid") cid: String, @Query("start") start: Int, @Query("count") count: Int): Observable<PhotosResult>

    //关键字搜索图片
    @GET("index.php?c=WallPaper&a=search")
    fun searchPhotos(@Query("kw") cid: String, @Query("start") start: Int, @Query("count") count: Int)  : Observable<PhotoSearch>

}