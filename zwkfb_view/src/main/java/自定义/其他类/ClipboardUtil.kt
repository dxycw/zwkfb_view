package 自定义.其他类

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 创建：wukuiqing
 *
 *
 * 时间：2018/4/17
 *
 *
 * 描述：
 */
class ClipboardUtil private constructor() {
    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {
        /**
         * 复制文本到剪贴板
         *
         * @param text 文本
         */
        fun copyText(context: Context, text: CharSequence?) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("text", text))
        }

        /**
         * 获取剪贴板的文本
         *
         * @return 剪贴板的文本
         */
        fun getText(context: Context): String? {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = clipboard.primaryClip
            if (clip != null && clip.itemCount > 0) {
                return clip.getItemAt(0).coerceToText(context).toString()
            }
            return null
        }

        /**
         * 复制uri到剪贴板
         *
         * @param uri uri
         */
        fun copyUri(context: Context, uri: Uri?) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newUri(context.contentResolver, "uri", uri))
        }

        /**
         * 获取剪贴板的uri
         *
         * @return 剪贴板的uri
         */
        fun getUri(context: Context): Uri? {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = clipboard.primaryClip
            if (clip != null && clip.itemCount > 0) {
                return clip.getItemAt(0).uri
            }
            return null
        }

        /**
         * 复制意图到剪贴板
         *
         * @param intent 意图
         */
        fun copyIntent(context: Context, intent: Intent?) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newIntent("intent", intent))
        }

        /**
         * 获取剪贴板的意图
         *
         * @return 剪贴板的意图
         */
        fun getIntent(context: Context): Intent? {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = clipboard.primaryClip
            if (clip != null && clip.itemCount > 0) {
                return clip.getItemAt(0).intent
            }
            return null
        }


        /**
         * 清空剪切板第一条
         * @param context
         * @return
         */
        fun clearFirstClipboard(context: Context) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = clipboard.primaryClip
            if (clip != null && clip.itemCount > 0) {
                clipboard.setPrimaryClip(ClipData.newPlainText(null, ""))
                if (clipboard.hasPrimaryClip()) {
                    clipboard.primaryClip!!.getItemAt(0).text
                }
            }
        }
    }
}