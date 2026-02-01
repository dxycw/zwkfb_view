package 自定义.对话框

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.core.net.toUri
import 商业.谷歌.安卓.材质.对话框.材质警告对话框构建器
import 安卓.应用.创建
import 安卓.应用.显示
import 安卓.应用.警告对话框
import 自定义.网络类.下载器.浏览器文件下载

object 对话框类 {

    @SuppressLint("UnsafeImplicitIntentLaunch")
    fun 浏览器下载对话框(
        上下文: Activity,
        下载链接: String?,
        用户代理: String,
        内容处理: String,
        文件类型: String
    ) {
        警告对话框.构建器(上下文).setTitle("下载")
            .setMessage("是否下载") //设置对话框的按钮
            .setNegativeButton("立即下载") { dialog: DialogInterface?, which: Int ->
                浏览器文件下载(上下文, 下载链接, 用户代理, 内容处理, 文件类型)
            }
            .setPositiveButton("浏览器下载") { dialog: DialogInterface?, which: Int ->
                上下文.startActivity(Intent(Intent.ACTION_VIEW, 下载链接?.toUri()))
            }
            .setNeutralButton("取消") { dialog: DialogInterface?, which: Int ->
                dialog!!.dismiss()
            }.创建().显示()
    }

    @SuppressLint("UnsafeImplicitIntentLaunch")
    fun 浏览器新下载对话框(
        上下文: Activity,
        下载链接: String?,
        用户代理: String,
        内容处理: String,
        文件类型: String
    ) {
        材质警告对话框构建器(上下文).setTitle("下载")
            .setMessage("是否下载") //设置对话框的按钮
            .setNegativeButton("立即下载") { dialog: DialogInterface?, which: Int ->
                //下载器.浏览器文件下载(上下文,下载链接);//,用户代理,内容处理,文件类型);
                浏览器文件下载(上下文, 下载链接, 用户代理, 内容处理, 文件类型)
            }
            .setNeutralButton("浏览器下载") { dialog: DialogInterface?, which: Int ->
                上下文.startActivity(Intent(Intent.ACTION_VIEW, 下载链接?.toUri()))
            }
            .setPositiveButton("取消") { dialog: DialogInterface?, which: Int ->
                dialog!!.dismiss()
            }.show()
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
