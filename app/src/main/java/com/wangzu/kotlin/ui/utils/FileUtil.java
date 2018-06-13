package com.wangzu.kotlin.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AnJiuZhe on 2018/5/29 0029.
 * Email 1573335865@qq.com
 */

public class FileUtil {
    /**
     * 打开系统文件管理器选择文件
     */
    public static void openSystemFile(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            activity.startActivityForResult(Intent.createChooser(intent, "请选择文件!"), 1);
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    public static void openSystemFile(Fragment fragment) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            fragment.startActivityForResult(Intent.createChooser(intent, "请选择文件!"), 1);
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    public static void openSystemImage(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            activity.startActivityForResult(Intent.createChooser(intent, "请选择文件!"), 2);
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    /**
     * Bitmap转InputStream
     *
     * @param bm Bitmap
     * @return InputStream
     */
    public static InputStream Bitmap2Is(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }


    /**
     * 保存图片并更新图库
     */
    public static String saveImageToGallery(Context context, Bitmap bmp) {
        // 保存图片
        File appDir = new File(Environment.getExternalStorageDirectory() + "/Pictures");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        final File file = new File(appDir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
        return appDir.getPath();
    }
}
