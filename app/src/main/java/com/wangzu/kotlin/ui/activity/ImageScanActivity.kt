package com.wangzu.kotlin.ui.activity

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wangzu.kotlin.R
import com.wangzu.kotlin.ui.utils.FileUtil
import kotlinx.android.synthetic.main.activity_image_scan.*


class ImageScanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_scan)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val permissions = RxPermissions(this)
        permissions.request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {

                }
        photoview.setOnClickListener { finish() }
        photoview.setOnLongClickListener {
            if (permissions.isGranted(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                val bitmap = (photoview.drawable as BitmapDrawable).bitmap
                val path = FileUtil.saveImageToGallery(this@ImageScanActivity, bitmap)
                Toast.makeText(this@ImageScanActivity, "图片已保存至" + path + "文件夹下", Toast.LENGTH_LONG).show()
            }
            return@setOnLongClickListener true
        }
        Glide.with(this).asBitmap().load(intent.getStringExtra("url")).into(photoview)
    }
}
