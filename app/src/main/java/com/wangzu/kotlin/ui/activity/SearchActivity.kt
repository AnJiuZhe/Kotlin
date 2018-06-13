package com.wangzu.kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.KeyEvent
import com.wangzu.kotlin.R
import com.wangzu.kotlin.ui.adapter.PhotoSearchAdapter
import com.wangzu.kotlin.ui.bean.PhotoSearch
import com.wangzu.kotlin.ui.retrofit.RetrofitClient
import com.wangzu.kotlin.ui.widget.EndLessOnScrollListener
import com.wangzu.kotlin.ui.widget.GridItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), PhotoSearchAdapter.ItemClickedListener {

    private var start: Int = 0
    private var count: Int = 30
    private var total: Int? = 30
    private var adapter: PhotoSearchAdapter? = null
    private var list: MutableList<PhotoSearch.Search> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        val layoutManager = GridLayoutManager(this, 2)
        recyclerview.layoutManager = layoutManager
        recyclerview.addItemDecoration(GridItemDecoration(2))
        adapter = PhotoSearchAdapter(this, list)
        adapter?.setListener(this)
        recyclerview.adapter = adapter
        recyclerview.addOnScrollListener(object : EndLessOnScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                if (currentPage<= total!!) {
                    requestPhotoSearch(currentPage)
                }
            }
        })
        searchEt.setOnKeyListener { _, keyCode, event ->
            if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.action) {
                list.clear()
                requestPhotoSearch(start)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun requestPhotoSearch(start: Int) {
        RetrofitClient.instance
                .getApiService()
                .searchPhotos(searchEt.text.toString(), start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { photoSearch: PhotoSearch? ->
                    total = photoSearch?.total?.toInt()
                    photoSearch?.data?.let { list.addAll(it) }
                    adapter?.notifyDataSetChanged()
                }
    }

    override fun itemClicked(position: Int) {
        val intent = Intent(this, ImageScanActivity::class.java)
        intent.putExtra("url",list[position].url)
        startActivity(intent)
    }
}
