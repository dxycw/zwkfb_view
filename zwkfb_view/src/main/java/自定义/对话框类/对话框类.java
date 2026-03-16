package 自定义.对话框类;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import 商业.谷歌.安卓.材质.对话框.材质警告对话框构建器;
import 安卓.应用.警告对话框;
import 自定义.网络类.下载器;

public class 对话框类 {

    public static void 浏览器下载对话框(Context 上下文, String 下载链接, String 用户代理, String 内容处理, String 文件类型) {
        new 警告对话框.构建器(上下文)
            .置标题("下载")
            .置消息("是否下载") //设置对话框的按钮
            .置取消按钮("立即下载", (dialog, which) ->
                    下载器.浏览器文件下载(上下文, 下载链接, 用户代理, 内容处理, 文件类型)
            )
            .置确定按钮("浏览器下载", (dialog, which) ->
                    上下文.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(下载链接)))
            )
            .置忽略按钮("取消", (dialog, which) -> dialog.dismiss())
            .显示();
    }


    public static void 材质浏览器下载对话框(Context 上下文, String 下载链接, String 用户代理, String 内容处理, String 文件类型) {
        new 材质警告对话框构建器(上下文)
            .setTitle("下载")
            .setMessage("是否下载") //设置对话框的按钮
            .setNegativeButton("立即下载", (dialog, which) ->
                    //下载器.浏览器文件下载(上下文,下载链接);//,用户代理,内容处理,文件类型);
                    下载器.浏览器文件下载(上下文, 下载链接, 用户代理, 内容处理, 文件类型)
            )
            .setNeutralButton("浏览器下载", (dialog, which) ->
                    上下文.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(下载链接)))
            )
            .setPositiveButton("取消", (dialog, which) ->
                    dialog.dismiss()
            ).show();
    }


//    @SuppressLint("UnsafeImplicitIntentLaunch")
//    fun 浏览器下载对话框(上下文: Activity, 网络请求: WebResponse) {
//        构建器(上下文).置标题("下载")
//            .置内容("是否下载") //设置对话框的按钮
//            .置右按钮(
//                "立即下载",
//                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                    浏览器文件下载2(上下文, 网络请求)
//                })
//            .置中按钮(
//                "浏览器下载",
//                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                    上下文.startActivity(Intent(Intent.ACTION_VIEW, 网络请求.uri.toUri()))
//                })
//            .置左按钮(
//                "取消",
//                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                    dialog!!.dismiss()
//                }).创建().显示()
//    }
//
//    @SuppressLint("UnsafeImplicitIntentLaunch")
//    fun 浏览器新下载对话框(上下文: Activity, 网络请求: WebResponse) {
//        材质对话框构建器(上下文).置标题("下载")
//            .置内容("是否下载") //设置对话框的按钮
//            .置右按钮(
//                "立即下载",
//                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                    浏览器文件下载2(上下文, 网络请求)
//                })
//            .置中按钮(
//                "浏览器下载",
//                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                    上下文.startActivity(Intent(Intent.ACTION_VIEW, 网络请求.uri.toUri()))
//                })
//            .置左按钮(
//                "取消",
//                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                    dialog!!.dismiss()
//                }).显示()
//    }

}
