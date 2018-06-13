package com.wangzu.kotlin.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wangzu.kotlin.R
import com.wangzu.kotlin.ui.bean.CategroyResult

/**
 * Created by AnJiuZhe on 2018/6/6 0006.
 * Email 1573335865@qq.com
 */
class CategoryAdapter(context: Context, categoryList: MutableList<CategroyResult.Categroy>?)
    : RecyclerView.Adapter<CategoryAdapter.CategroyViewHolder>() {

    private var context: Context? = null
    private var categoryList: MutableList<CategroyResult.Categroy>? = null
    private var colorDrawable: ColorDrawable? = null
    private var itemClickedListener: ItemClickedListener? = null

    init {
        this.context = context
        this.categoryList = categoryList
        colorDrawable = ColorDrawable(Color.parseColor("#EFEFEF"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategroyViewHolder {
        val inflater = LayoutInflater.from(context)
        return CategroyViewHolder(inflater.inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int {
        return categoryList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategroyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickedListener?.itemClicked(position)
        }
        holder.categroyNameTv.text = categoryList?.get(position)?.name
        holder.categroyCountTv.text = categoryList?.get(position)?.totalcnt
    }

    class CategroyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var categroyNameTv: TextView = itemView?.findViewById(R.id.categoryNameTv) as TextView
        var categroyCountTv: TextView = itemView?.findViewById(R.id.categoryCountTv) as TextView
    }

    interface ItemClickedListener {
        fun itemClicked(position: Int)
    }

    fun setListener(itemClickedListener: ItemClickedListener) {
        this.itemClickedListener = itemClickedListener
    }
}