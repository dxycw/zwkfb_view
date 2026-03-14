package 自定义.系统类;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 创建：wukuiqing
 * <p>
 * 时间：2018/4/17
 * <p>
 * 描述：剪贴板工具
 */

public class 剪贴板 {

    private 剪贴板() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 复制文本到剪贴板
     * @param 文本 文本
     */
    public static void 复制文本(Context 上下文, CharSequence 文本) {
        ClipboardManager clipboard = (ClipboardManager) 上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText("text", 文本));
    }

    /**
     * 获取剪贴板的文本
     * @return 剪贴板的文本
     */
    public static String 取文本(Context 上下文) {
        ClipboardManager clipboard = (ClipboardManager) 上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(上下文).toString();
        }
        return null;
    }

    public static String 取粘贴板内容(Activity 上下文) {
        // 获取 ClipboardManager 实例
        ClipboardManager clipboard = (ClipboardManager)上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        // 检查剪贴板是否有内容
        if (clipboard.hasPrimaryClip()) {
            // 获取剪贴板中的内容
            ClipData clipData = clipboard.getPrimaryClip();
            if (clipData != null && clipData.getItemCount() > 0) {
                // 获取第一个条目的文本内容
                CharSequence pasteData = clipData.getItemAt(0).getText();
                // 显示获取到的文本内容
                return pasteData.toString();
            }
        }
        // 剪贴板为空
        return "";
    }

    /**
     * 复制uri到剪贴板
     *
     * @param 网址 uri
     */
    public static void 复制网址(Context 上下文, Uri 网址) {
        ClipboardManager clipboard = (ClipboardManager) 上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newUri(上下文.getContentResolver(), "uri", 网址));
    }

    /**
     * 获取剪贴板的uri
     *
     * @return 剪贴板的uri
     */
    public static Uri 取网址(Context 上下文) {
        ClipboardManager clipboard = (ClipboardManager) 上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getUri();
        }
        return null;
    }

    /**
     * 复制意图到剪贴板
     *
     * @param 意图 意图
     */
    public static void 复制意图(Context 上下文, Intent 意图) {
        ClipboardManager clipboard = (ClipboardManager) 上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newIntent("intent", 意图));
    }


    /**
     * 获取剪贴板的意图
     *
     * @return 剪贴板的意图
     */
    public static Intent 取意图(Context 上下文) {
        ClipboardManager clipboard = (ClipboardManager) 上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getIntent();
        }
        return null;
    }


    /**
     * 清空剪切板第一条
     * @param 上下文 上下文
     */
    public static void 清除第一个剪贴板(Context 上下文) {
        ClipboardManager clipboard = (ClipboardManager) 上下文.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            clipboard.setPrimaryClip(ClipData.newPlainText(null, ""));
            if (clipboard.hasPrimaryClip()) {
                clipboard.getPrimaryClip().getItemAt(0).getText();
            }
        }
    }

}
