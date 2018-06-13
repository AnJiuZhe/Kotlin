package com.wangzu.kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.wangzu.kotlin.R
import com.wangzu.kotlin.ui.adapter.CategoryAdapter
import com.wangzu.kotlin.ui.bean.CategroyResult
import com.wangzu.kotlin.ui.retrofit.RetrofitClient
import com.wangzu.kotlin.ui.widget.GridItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CategoryAdapter.ItemClickedListener {

    private var adapter: CategoryAdapter? = null
    private var list: MutableList<CategroyResult.Categroy> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        recyclerview.layoutManager = GridLayoutManager(this, 4)
        recyclerview.addItemDecoration(GridItemDecoration(4))
        adapter = CategoryAdapter(this, list)
        adapter?.setListener(this)
        recyclerview.adapter = adapter
        searchIv.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
        }
        requestCategory()
    }

    private fun requestCategory() {
        RetrofitClient.instance.getApiService()
                .getCategoryResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { categroyResult: CategroyResult? ->
                    list.clear()
                    categroyResult?.data?.let { list.addAll(it) }
                    adapter?.notifyDataSetChanged()
                }
    }

    override fun itemClicked(position: Int) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("category", list[position])
        startActivity(intent)
    }
}
