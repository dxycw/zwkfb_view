@file:Suppress("DEPRECATION")

package 自定义.网络类

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import 安卓.组件.吐司
import 安卓.组件.显示
import 自定义.应用类.注册APK下载监听
import kotlin.text.endsWith


fun Context.浏览器文件下载(网址: String, 文件名: String, 文件类型: String) {
    try {
        val request = DownloadManager.Request(网址.toUri())
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        // 完全自定义文件名，不依赖 URLUtil.guessFileName
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, 文件名)
        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val 下载Id = downloadManager.enqueue(request)
        // 注册下载完成监听器
        if (文件名.endsWith(".apk", ignoreCase = true)) {
            注册APK下载监听(下载Id, 文件名)
        }
        吐司.制作文本(this, "开始下载", 吐司.短).显示()
    } catch (e: Exception) {
        吐司.制作文本(this, "下载失败：${e.message}", 吐司.短).显示()
    }
}

//===========================================================================

//    @JvmStatic
//    fun 浏览器文件下载2(上下文: Activity, 网络请求: WebResponse) {
//        try {
//            // 获取下载 URL 和文件名
//            val 下载链接 = 网络请求.uri
//            val fileName = 创建请求下载网址文件名(网络请求.headers)
//
//            // 创建下载请求
//            val request = DownloadManager.Request(下载链接.toUri()).apply {
//                // 设置文件名和 MIME 类型
//                setTitle(fileName)
//                setDescription("正在下载文件")
//                setMimeType(网络请求.headers["Content-Type"] ?: "application/octet-stream")
//
//                // 设置通知可见性
//                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//
//                // 设置下载文件的存放位置
//                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
//            }
//
//            // 获取 DownloadManager 实例
//            val downloadManager = 上下文.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            // 开始下载
//            val downloadId = downloadManager.enqueue(request)
//            吐司.提示信息(上下文, "开始下载：$fileName", 吐司.短时间).显示()
//        } catch (e: Exception) {
//            吐司.提示信息(上下文, "下载失败：$e", 吐司.短时间).显示()
//        }
//    }
