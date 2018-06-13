package com.wangzu.kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.wangzu.kotlin.R
import com.wangzu.kotlin.ui.adapter.PhotoAdapter
import com.wangzu.kotlin.ui.bean.CategroyResult
import com.wangzu.kotlin.ui.bean.PhotosResult
import com.wangzu.kotlin.ui.retrofit.RetrofitClient
import com.wangzu.kotlin.ui.widget.EndLessOnScrollListener
import com.wangzu.kotlin.ui.widget.GridItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class CategoryActivity : AppCompatActivity(), PhotoAdapter.ItemClickedListener {

    private var category: CategroyResult.Categroy? = null
    private var start: Int = 0
    private var count: Int = 30
    private var total: Int? = 30
    private var adapter: PhotoAdapter? = null
    private var list: MutableList<PhotosResult.Photo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        category = intent.getParcelableExtra("category")
        val layoutManager = GridLayoutManager(this, 2)
        recyclerview.layoutManager = layoutManager
        recyclerview.addItemDecoration(GridItemDecoration(2))
        adapter = PhotoAdapter(this, list)
        adapter?.setListener(this)
        recyclerview.adapter = adapter
        recyclerview.addOnScrollListener(object : EndLessOnScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                if (currentPage <= total!!) {
                    requestPhotos(currentPage)
                }
            }
        })
        requestPhotos(start)
    }

    override fun itemClicked(position: Int) {
        val intent = Intent(this, ImageScanActivity::class.java)
        intent.putExtra("url",list[position].url)
        startActivity(intent)
    }

    private fun requestPhotos(start: Int) {
        RetrofitClient.instance
                .getApiService()
                .getPhotosResult(category!!.id, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { photoResult: PhotosResult? ->
                    total = photoResult?.total?.toInt()
                    photoResult?.data?.let { list.addAll(it) }
                    adapter?.notifyDataSetChanged()
                }
    }
}
