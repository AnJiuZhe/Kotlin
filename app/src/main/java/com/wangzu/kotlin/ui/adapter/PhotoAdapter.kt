package com.wangzu.kotlin.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wangzu.kotlin.R
import com.wangzu.kotlin.ui.bean.PhotosResult
import com.wangzu.kotlin.ui.utils.DensityUtil

/**
 * Created by AnJiuZhe on 2018/6/6 0006.
 * Email 1573335865@qq.com
 */
class PhotoAdapter(context: Context, photoList: MutableList<PhotosResult.Photo>?)
    : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var context: Context? = null
    private var photoList: MutableList<PhotosResult.Photo>? = null
    private var colorDrawable: ColorDrawable? = null
    private var itemClickedListener: ItemClickedListener? = null

    init {
        this.context = context
        this.photoList = photoList
        colorDrawable = ColorDrawable(Color.parseColor("#EFEFEF"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(context)
        return PhotoViewHolder(inflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun getItemCount(): Int {
        return photoList?.size ?: 0
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickedListener?.itemClicked(position)
        }
        val height = DensityUtil.dip2px(context!!, 300f).toInt()
        val requestOptions = RequestOptions()
                .placeholder(colorDrawable)
                .sizeMultiplier(0.5f)
                .override(height, height)
        Glide.with(context!!)
                .load(photoList?.get(position)?.url)
                .apply(requestOptions)
                .into(holder.photoIv)
    }

    class PhotoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var photoIv: ImageView = itemView?.findViewById(R.id.photoIv) as ImageView
    }

    interface ItemClickedListener {
        fun itemClicked(position: Int)
    }

    fun setListener(itemClickedListener: ItemClickedListener) {
        this.itemClickedListener = itemClickedListener
    }
}