package 自定义.网络类;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.webkit.URLUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import kotlin.text.MatchResult;
import kotlin.text.Regex;
import 安卓.组件.吐司;
import 自定义.系统类.应用类;

public class 下载器 {


    public static void 浏览器文件下载(Context 上下文, String 网址, String 文件名, String 文件类型) {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(网址));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            // 完全自定义文件名，不依赖 URLUtil.guessFileName
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, 文件名);
            DownloadManager downloadManager = (DownloadManager)上下文.getSystemService(Context.DOWNLOAD_SERVICE);
            long 下载Id = downloadManager.enqueue(request);
            // 注册下载完成监听器
            if (文件名.endsWith(".apk")) {
                应用类.注册APK下载监听(上下文, 下载Id, 文件名);
            }
            吐司.制作文本(上下文, "开始下载", 吐司.长度_短).显示();
        } catch (Exception e) {
            吐司.制作文本(上下文, "下载失败：" + e.getMessage(), 吐司.长度_短).显示();
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


    public static void 浏览器文件下载(Context 上下文, String 网址, String ua, String 内容处理, String 文件类型){
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(网址));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            String 文件名 = URLUtil.guessFileName(网址, 内容处理, 文件类型);//"app.apk"
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, 文件名);
            DownloadManager downloadManager = (DownloadManager)上下文.getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            吐司.制作文本(上下文,"开始下载", 吐司.长度_短).显示();
        } catch (Exception e) {
            吐司.制作文本(上下文, "下载失败："+e.getMessage(), 吐司.长度_短).显示();
        }
    }

//    fun 浏览器文件下载2(上下文: Activity, 网络请求: WebResponse) {
//        try {
//            // 获取下载 URL 和文件名
//            val 下载链接 = 网络请求.uri
//            val fileName = generateFileName(网络请求.headers)
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
//            吐司.制作文本(上下文, "开始下载：$fileName", 吐司.短).显示()
//        } catch (e: Exception) {
//            吐司.制作文本(上下文, "下载失败：$e", 吐司.短).显示()
//        }
//    }

    /**
     * 根据响应头和 URL 生成文件名
     */
    private String generateFileName(Map<String, String> headers)  {
        // 如果没有从 URL 中获取到文件名，则从 Content-Disposition 中提取
        String contentDisposition = headers.getOrDefault("Content-Disposition", "");
        String fileNameFromHeader = String.valueOf(getContentDispositionFileName(contentDisposition));
        if (fileNameFromHeader != null) {
            return fileNameFromHeader;
        }

        // 如果仍然没有文件名，则根据 MIME 类型生成默认文件名
        String mimeType = headers.getOrDefault("Content-Type", "application/octet-stream");
        return switch (mimeType){
            case "image/jpeg" -> "image_${System.currentTimeMillis()}.jpg";
            case "image/png" -> "image_${System.currentTimeMillis()}.png";
            case "image/gif" -> "image_${System.currentTimeMillis()}.gif";
            case "image/bmp" -> "image_${System.currentTimeMillis()}.bmp";
            case "video/mp4" -> "video_${System.currentTimeMillis()}.mp4";
            case "video/3gpp" -> "video_${System.currentTimeMillis()}.3gp";
            case "audio/mpeg" -> "audio_${System.currentTimeMillis()}.mp3";
            case "audio/wav" -> "audio_${System.currentTimeMillis()}.wav";
            case "text/plain" -> "text_${System.currentTimeMillis()}.txt";
            case "text/html" -> "text_${System.currentTimeMillis()}.html";
            case "application/pdf" -> "document_${System.currentTimeMillis()}.pdf";
            case "application/vnd.android.package-archive" -> "app_${System.currentTimeMillis()}.apk";
            case "binary/octet-stream" -> "app_${System.currentTimeMillis()}.apk";
            case "application/octet-stream" -> "app_${System.currentTimeMillis()}.apk";
            default -> "file_${System.currentTimeMillis()}.${getMimeTypeExtension(mimeType)}";
        };
    }

    /**
     * 从 Content-Disposition 中提取文件名
     */
    private String getContentDispositionFileName(String contentDisposition)  {
        Regex fileNamePattern = new Regex("filename\\*=.*?utf-8''(.*?)");
        MatchResult matchResult = fileNamePattern.find(contentDisposition,0);
        if (matchResult != null) {
            try {
                return URLDecoder.decode(matchResult.getGroupValues().get(1), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        Regex simpleFileNamePattern = new Regex("filename\\s*=\\s*\"?(.*?)\"\\s*$");
        MatchResult simpleMatchResult = simpleFileNamePattern.find(contentDisposition,0);
        return simpleMatchResult.getGroupValues().get(1);
    }

    /**
     * 根据 MIME 类型获取文件扩展名
     */
    private String getMimeTypeExtension(String mimeType) {
        return switch (mimeType){
            case "image/jpeg" -> "jpg";
            case "image/png" -> "png";
            case "image/gif" -> "gif";
            case "image/bmp" -> "bmp";
            case "video/mp4" -> "mp4";
            case "video/3gpp" -> "3gp";
            case "audio/mpeg" -> "mp3";
            case "audio/wav" -> "wav";
            case "text/plain" -> "txt";
            case "text/html" -> "html";
            case "application/pdf" -> "pdf";
            case "application/vnd.android.package-archive" -> "apk";
            case "binary/octet-stream" -> ".apk";
            case "application/octet-stream" -> ".apk";
            case "application/zip" -> "zip";
            case "application/x-rar-compressed" -> "rar";
            default -> "bin";
        };
    }


}
