package 自定义.粘贴板类

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri


//======================================================================

fun 获取粘贴板内容(上下文: Activity): String {
    // 获取 ClipboardManager 实例
    val clipboard = 上下文.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    // 检查剪贴板是否有内容
    if (clipboard.hasPrimaryClip()) {
        // 获取剪贴板中的内容
        val clipData = clipboard.primaryClip
        if (clipData != null && clipData.itemCount > 0) {
            // 获取第一个条目的文本内容
            val pasteData = clipData.getItemAt(0).text
            // 显示获取到的文本内容
            return pasteData.toString()
        }
    }
    // 剪贴板为空
    return ""
}

//======================================================================

/**
 * 复制文本到剪贴板
 * @param 文本 要复制的文本内容
 */
fun Context.复制文本到剪贴板(文本: CharSequence?) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("text", 文本))
}

//======================================================================

/**
 * 获取剪贴板的文本
 *
 * @return 剪贴板的文本
 */
fun Context.取剪贴板文本(): String? {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = clipboard.primaryClip
    if (clip != null && clip.itemCount > 0) {
        return clip.getItemAt(0).coerceToText(this).toString()
    }
    return null
}

//======================================================================

/**
 * 复制uri到剪贴板
 *
 * @param uri uri
 */
fun Context.复制Uri到剪贴板(uri: Uri?) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newUri(contentResolver, "uri", uri))
}

//======================================================================

/**
 * 获取剪贴板的uri
 *
 * @return 剪贴板的uri
 */
fun Context.取剪贴板Uri(): Uri? {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = clipboard.primaryClip
    if (clip != null && clip.itemCount > 0) {
        return clip.getItemAt(0).uri
    }
    return null
}

//======================================================================

/**
 * 复制意图到剪贴板
 *
 * @param intent 意图
 */
fun Context.复制意图到剪贴板(intent: Intent?) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newIntent("intent", intent))
}

//======================================================================

/**
 * 获取剪贴板的意图
 *
 * @return 剪贴板的意图
 */
fun Context.取剪贴板意图(): Intent? {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = clipboard.primaryClip
    if (clip != null && clip.itemCount > 0) {
        return clip.getItemAt(0).intent
    }
    return null
}

//======================================================================

/**
 * 清空剪切板第一条
 * @param context
 * @return
 */
fun Context.清空剪切板第一条() {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = clipboard.primaryClip
    if (clip != null && clip.itemCount > 0) {
        clipboard.setPrimaryClip(ClipData.newPlainText(null, ""))
        if (clipboard.hasPrimaryClip()) {
            clipboard.primaryClip!!.getItemAt(0).text
        }
    }
}

//======================================================================
