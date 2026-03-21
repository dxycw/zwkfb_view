package 自定义.系统类;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 文件类 {

    public static String 随机文件名(){
        return UUID.randomUUID().toString();
    }

    //==================================================================================

    //读文件
    public static String 取Assets文件(Context 上下文, String 文件名) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(上下文.getAssets().open(文件名)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(上下文, "读取失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return sb.toString();
    }

    public static String 从Assets加载JS文件(Context 上下文, String fileName) {
        StringBuilder jsContent = new StringBuilder();
        try {
            InputStream is = 上下文.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                jsContent.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return jsContent.toString();
    }

    //==================================================================================

    // 创建下载网址文件名
    public static String 创建下载网址文件名(String 网址, String 文件类型) {
        float timestamp = System.currentTimeMillis() % 1000000; // 简化时间戳

        if (文件类型.contains("apk") || 文件类型.contains("application/vnd.android.package-archive") || (网址 != null && 网址.contains(".apk"))) {
            // APK 文件
            return "app_" + timestamp + ".apk";
        }else if (文件类型.contains("jpeg") || 文件类型.contains("jpg") || (网址 != null && (网址.contains(".jpg") || 网址.contains(".jpeg")))){
            // 图片文件
            return "photo_" + timestamp + ".jpg";
        }else if (文件类型.contains("png") || (网址 != null && 网址.contains(".png"))) {
            // 图片文件
            return "image_" + timestamp + ".png";
        }else if (文件类型.contains("gif") || (网址 != null && 网址.contains(".gif"))){
            // 动画文件
            return "animation_" + timestamp + ".gif";
        }else if (文件类型.contains("mp4") || (网址 != null && 网址.contains(".mp4"))){
            // 视频文件
            return "video_" + timestamp + ".mp4";
        }else if (文件类型.contains("pdf") || (网址 != null && 网址.contains(".pdf"))){
            // PDF文件
            return "document_" + timestamp + ".pdf";
        }else if (文件类型.contains("mp3") || (网址 != null && 网址.contains(".mp3"))){
            // 音频文件
            return "audio_" + timestamp + ".mp3";
        }else if (文件类型.contains("zip") || (网址 != null && 网址.contains(".zip"))){
            // 压缩文件
            return "archive_" + timestamp + ".zip";
        }else {
            // 默认情况
            return "download_" + timestamp + ".file";
        }
    }

    //==================================================================================

    // 修改后的文件拷贝方法（去掉随机字符）

    public static String 从Intent获取文件名及扩展名(Context context, Uri uri) {
        if (context == null || uri == null) return null;

        try (InputStream is = context.getContentResolver().openInputStream(uri)) {
            if (is == null) return null;

            String original = Optional.ofNullable(getOriginalFileName(context, uri))
                    .filter(s -> !s.isEmpty())
                    .orElse("unknown");

            Pair<String, String> parts = splitNameAndExtension(original);
            String safeBase = Pattern.compile("[\\\\/:*?\"<>|]").matcher(parts.first).replaceAll("_");
            safeBase = safeBase.length() > 50 ? safeBase.substring(0, 50) : safeBase;
            if (safeBase.isEmpty()) safeBase = "file";

            String fileName = parts.second.isEmpty() ? safeBase : safeBase + "." + parts.second;

            File outFile = new File(context.getCacheDir(), fileName);
            if (outFile.exists() && !outFile.delete()) {
                throw new IOException("无法删除: " + outFile.getAbsolutePath());
            }

            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                byte[] buf = new byte[8192];
                int n;
                while ((n = is.read(buf)) > 0) fos.write(buf, 0, n);
            }

            return "file://" + outFile.getAbsolutePath();

        } catch (Exception e) {
            Log.e("FileCopy", "文件拷贝失败", e);
            return null;
        }
    }

    // 优化后的文件名分割方法（处理多扩展名）
    private static Pair<String, String> splitNameAndExtension(String fileName) {
        // 处理复合扩展名（如.tar.gz）
        String[] compoundExtensions = new String[]{"tar.gz", "tar.bz2", "apk.1"};
        for (String ext : compoundExtensions) {
            if (fileName.endsWith("." + ext)) {
                String base = fileName.substring(0, fileName.length() - ext.length() - 1);
                return new Pair<>(base, ext);
            }
        }

        // 常规分割逻辑
        int lastDotIndex = fileName.lastIndexOf('.');
        return switch (lastDotIndex) {
            case -1 -> new Pair<>(fileName, "");
            case 0 -> new Pair<>(fileName, "");
            default -> {
                String base = fileName.substring(0, lastDotIndex);
                String ext = fileName.substring(lastDotIndex + 1);
                yield(new Pair<>(base, ext));
            }
        };
    }

    // 获取原始文件名
    private static String getOriginalFileName(Context 上下文, Uri uri) {
        // 方法1：通过ContentResolver查询
        ContentResolver resolver = 上下文.getContentResolver();
        String[] projection = {MediaStore.MediaColumns.DISPLAY_NAME};
        try (Cursor cursor = resolver.query(uri, projection, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                String fileName = cursor.getString(0);
                return (fileName != null && !fileName.trim().isEmpty()) ? fileName : null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //==================================================================================


    // 根据响应头和 URL 生成文件名
    public static String 创建请求下载网址文件名(Map<String, String> headers) {
        // 如果没有从 URL 中获取到文件名，则从 Content-Disposition 中提取
        final String contentDisposition = headers.get("Content-Disposition");
        final String fileNameFromHeader = getContentDispositionFileName(contentDisposition);
        if (fileNameFromHeader != null && !fileNameFromHeader.isEmpty()) {
            return fileNameFromHeader;
        }

        // 如果仍然没有文件名，则根据 MIME 类型生成默认文件名
        final String mimeType = headers.get("Content-Type");
        return switch (mimeType) {
            case "image/jpeg" -> "image_"+System.currentTimeMillis()+".jpg";
            case "image/png" -> "image_"+System.currentTimeMillis()+".png";
            case "image/gif" -> "image_"+System.currentTimeMillis()+".gif";
            case "image/bmp" -> "image_"+System.currentTimeMillis()+".bmp";
            case "video/mp4" -> "video_"+System.currentTimeMillis()+".mp4";
            case "video/3gpp" -> "video_"+System.currentTimeMillis()+".3gp";
            case "audio/mpeg" -> "audio_"+System.currentTimeMillis()+".mp3";
            case "audio/wav" -> "audio_"+System.currentTimeMillis()+".wav";
            case "text/plain" -> "text_"+System.currentTimeMillis()+".txt";
            case "text/html" -> "text_"+System.currentTimeMillis()+".html";
            case "application/pdf" -> "document_"+System.currentTimeMillis()+".pdf";
            case "application/vnd.android.package-archive" -> "app_\"+System.currentTimeMillis()+\".apk";
            case "binary/octet-stream" -> "app_"+System.currentTimeMillis()+".apk";
            case "application/octet-stream" -> "app_"+System.currentTimeMillis()+".apk";
            case "application/zip" -> "archive_"+System.currentTimeMillis()+".zip";
            case "application/x-rar-compressed" -> "archive_"+System.currentTimeMillis()+".rar";
            default -> "file_"+System.currentTimeMillis()+"."+getMimeTypeExtension(mimeType);
        };
    }
    // 从 Content-Disposition 中提取文件名
    private static String getContentDispositionFileName(String contentDisposition) {
        final Pattern fileNamePattern = Pattern.compile("filename\\*=.*?utf-8''(.*?)");
        final Matcher matchResult = fileNamePattern.matcher(contentDisposition);
        if (matchResult.find()) {
            try {
                return URLDecoder.decode(matchResult.group(1), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        final Pattern simpleFileNamePattern = Pattern.compile("filename\\s*=\\s*\"?(.*?)\"\\s*$");
        final Matcher simpleMatchResult = simpleFileNamePattern.matcher(contentDisposition);
        return simpleMatchResult.find() ? simpleMatchResult.group(1) : "";
    }
    // 根据 MIME 类型获取文件扩展名
    private static String getMimeTypeExtension(String mimeType) {
        return switch (mimeType) {
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
            case "binary/octet-stream" -> "apk";
            case "application/octet-stream" -> "apk";
            case "application/zip" -> "zip";
            case "application/x-rar-compressed" -> "rar";
            default -> "bin";
        };
    }


}
