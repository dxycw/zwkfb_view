package 自定义.文件类

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URLDecoder
import java.util.UUID

fun 随机文件名(): String = UUID.randomUUID().toString()

//==================================================================================

//读文件
fun Context.读取Assets文件(fileName: String): String {
    val returnString = kotlin.text.StringBuilder()
    var fIn: InputStream? = null
    var isr: InputStreamReader? = null
    var input: BufferedReader? = null
    try {
        fIn = resources.assets.open(fileName, Context.MODE_PRIVATE)
        isr = InputStreamReader(fIn)
        input = BufferedReader(isr)
        var line: String?
        while ((input.readLine().also { line = it }) != null) {
            returnString.append(line)
            returnString.append('\n')
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            input?.close()
            isr?.close()
            fIn?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return returnString.toString()
}

fun Context.从Assets加载JS文件(fileName: String): String? {
    val jsContent = kotlin.text.StringBuilder()
    try {
        val `is` = assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(`is`))
        var line: String?
        while ((reader.readLine().also { line = it }) != null) {
            jsContent.append(line).append("\n")
        }
        reader.close()
    } catch (e: IOException) {
        return null
    }
    return jsContent.toString()
}

//==================================================================================

// 创建下载网址文件名
fun 创建下载网址文件名(网址: String?, 文件类型: String): String {
    val timestamp = System.currentTimeMillis() % 1000000 // 简化时间戳
    return when {
        // APK 文件
        文件类型.contains("apk", ignoreCase = true) ||
                文件类型.contains("application/vnd.android.package-archive", ignoreCase = true) ||
                (网址 != null && 网址.contains(".apk", ignoreCase = true)) ->
            "app_$timestamp.apk"

        // 图片文件
        文件类型.contains("jpeg", ignoreCase = true) ||
                文件类型.contains("jpg", ignoreCase = true) ||
                (网址 != null && (网址.contains(".jpg", ignoreCase = true) || 网址.contains(".jpeg", ignoreCase = true))) ->
            "photo_$timestamp.jpg"

        文件类型.contains("png", ignoreCase = true) ||
                (网址 != null && 网址.contains(".png", ignoreCase = true)) ->
            "image_$timestamp.png"

        文件类型.contains("gif", ignoreCase = true) ||
                (网址 != null && 网址.contains(".gif", ignoreCase = true)) ->
            "animation_$timestamp.gif"

        // 视频文件
        文件类型.contains("mp4", ignoreCase = true) ||
                (网址 != null && 网址.contains(".mp4", ignoreCase = true)) ->
            "video_$timestamp.mp4"

        // PDF 文件
        文件类型.contains("pdf", ignoreCase = true) ||
                (网址 != null && 网址.contains(".pdf", ignoreCase = true)) ->
            "document_$timestamp.pdf"

        // 音频文件
        文件类型.contains("mp3", ignoreCase = true) ||
                (网址 != null && 网址.contains(".mp3", ignoreCase = true)) ->
            "audio_$timestamp.mp3"

        // 压缩文件
        文件类型.contains("zip", ignoreCase = true) ||
                (网址 != null && 网址.contains(".zip", ignoreCase = true)) ->
            "archive_$timestamp.zip"

        // 默认情况
        else -> "download_$timestamp.file"
    }
}

//==================================================================================

// 修改后的文件拷贝方法（去掉随机字符）
fun Context.从Intent获取文件名及扩展名(uri: Uri): String? {
    return try {
        contentResolver.openInputStream(uri)?.use { inputStream ->
            // 获取安全文件名和扩展名---------------------
            val originalName = getOriginalFileName(uri) ?: "unknown" // 获取原始文件名
            val (base, ext) = splitNameAndExtension(originalName) // 分割文件名和扩展名

            // 清理文件名主体
            var safeBase = base
                .replace(Regex("[\\\\/:*?\"<>|]"), "_")
                .take(50) // 限制文件名长度

            // 处理无文件名情况（只有扩展名）
            if (safeBase.isEmpty()) safeBase = "file" // 使用默认文件名
            val (safeName, extension) = Pair(safeBase, ext) // 返回文件名和扩展名
            //--------------------------------------------------

            // 构建完整文件名（无随机字符）
            val fileName = if (extension.isNotEmpty()) {
                "$safeName.$extension"
            } else {
                safeName
            }

            // 直接创建目标文件
            val outputFile = File(cacheDir, fileName).apply {
                // 删除已存在的文件
                if (exists()) {
                    if (!delete()) {
                        throw IOException("无法删除已存在文件: $absolutePath")
                    }
                }
            }

            // 执行文件拷贝
            FileOutputStream(outputFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
            "file://${outputFile.absolutePath}"
        }
    } catch (e: Exception) {
        Log.e("FileCopy", "文件拷贝失败", e)
        null
    }
}
// 优化后的文件名分割方法（处理多扩展名）
private fun splitNameAndExtension(fileName: String): Pair<String, String> {
    // 处理复合扩展名（如.tar.gz）
    val compoundExtensions = setOf("tar.gz", "tar.bz2", "apk.1")
    compoundExtensions.forEach { ext ->
        if (fileName.endsWith(".$ext")) {
            val base = fileName.removeSuffix(".$ext")
            return Pair(base, ext)
        }
    }

    // 常规分割逻辑
    val lastDotIndex = fileName.lastIndexOf('.')
    return when (lastDotIndex) {
        -1 -> Pair(fileName, "")
        0 -> Pair(fileName, "")
        else -> {
            val base = fileName.substring(0, lastDotIndex)
            val ext = fileName.substring(lastDotIndex + 1)
            Pair(base, ext)
        }
    }
}

// 获取原始文件名
private fun Context.getOriginalFileName(uri: Uri): String? {
    // 方法1：通过ContentResolver查询
    contentResolver.query(
        uri, arrayOf(MediaStore.MediaColumns.DISPLAY_NAME), // 指定列
        null, null, null
    )?.use { cursor -> // 创建游标
        if (cursor.moveToFirst()) { // 移动到第一行
            return cursor.getString(0)?.takeIf { it.isNotBlank() } // 返回文件名
        }
    }
    return null
}

//==================================================================================

// 根据响应头和 URL 生成文件名
fun 创建请求下载网址文件名(headers: Map<String, String>): String {
    // 如果没有从 URL 中获取到文件名，则从 Content-Disposition 中提取
    val contentDisposition = headers["Content-Disposition"] ?: ""
    val fileNameFromHeader = getContentDispositionFileName(contentDisposition)
    if (fileNameFromHeader.isNotEmpty()) {
        return fileNameFromHeader
    }

    // 如果仍然没有文件名，则根据 MIME 类型生成默认文件名
    val mimeType = headers["Content-Type"] ?: "application/octet-stream"
    return when {
        mimeType.startsWith("image/jpeg") -> "image_${System.currentTimeMillis()}.jpg"
        mimeType.startsWith("image/png") -> "image_${System.currentTimeMillis()}.png"
        mimeType.startsWith("image/gif") -> "image_${System.currentTimeMillis()}.gif"
        mimeType.startsWith("image/bmp") -> "image_${System.currentTimeMillis()}.bmp"
        mimeType.startsWith("video/mp4") -> "video_${System.currentTimeMillis()}.mp4"
        mimeType.startsWith("video/3gpp") -> "video_${System.currentTimeMillis()}.3gp"
        mimeType.startsWith("audio/mpeg") -> "audio_${System.currentTimeMillis()}.mp3"
        mimeType.startsWith("audio/wav") -> "audio_${System.currentTimeMillis()}.wav"
        mimeType.startsWith("text/plain") -> "text_${System.currentTimeMillis()}.txt"
        mimeType.startsWith("text/html") -> "text_${System.currentTimeMillis()}.html"
        mimeType.startsWith("application/pdf") -> "document_${System.currentTimeMillis()}.pdf"
        mimeType.startsWith("application/vnd.android.package-archive") -> "app_${System.currentTimeMillis()}.apk"
        mimeType.startsWith("binary/octet-stream") -> "app_${System.currentTimeMillis()}.apk"
        mimeType.startsWith("application/octet-stream") -> "app_${System.currentTimeMillis()}.apk"
        else -> "file_${System.currentTimeMillis()}.${getMimeTypeExtension(mimeType)}"
    }
}
// 从 Content-Disposition 中提取文件名
private fun getContentDispositionFileName(contentDisposition: String): String {
    val fileNamePattern = "filename\\*=.*?utf-8''(.*?)".toRegex()
    val matchResult = fileNamePattern.find(contentDisposition)
    if (matchResult != null) {
        return URLDecoder.decode(matchResult.groupValues[1], "UTF-8")
    }

    val simpleFileNamePattern = "filename\\s*=\\s*\"?(.*?)\"\\s*$".toRegex()
    val simpleMatchResult = simpleFileNamePattern.find(contentDisposition)
    return simpleMatchResult?.groupValues?.get(1) ?: ""
}
// 根据 MIME 类型获取文件扩展名
private fun getMimeTypeExtension(mimeType: String): String {
    return when {
        mimeType.startsWith("image/jpeg") -> "jpg"
        mimeType.startsWith("image/png") -> "png"
        mimeType.startsWith("image/gif") -> "gif"
        mimeType.startsWith("image/bmp") -> "bmp"
        mimeType.startsWith("video/mp4") -> "mp4"
        mimeType.startsWith("video/3gpp") -> "3gp"
        mimeType.startsWith("audio/mpeg") -> "mp3"
        mimeType.startsWith("audio/wav") -> "wav"
        mimeType.startsWith("text/plain") -> "txt"
        mimeType.startsWith("text/html") -> "html"
        mimeType.startsWith("application/pdf") -> "pdf"
        mimeType.startsWith("application/vnd.android.package-archive") -> "apk"
        mimeType.startsWith("binary/octet-stream") -> ".apk"
        mimeType.startsWith("application/octet-stream") -> ".apk"
        mimeType.startsWith("application/zip") -> "zip"
        mimeType.startsWith("application/x-rar-compressed") -> "rar"
        else -> "bin"
    }
}