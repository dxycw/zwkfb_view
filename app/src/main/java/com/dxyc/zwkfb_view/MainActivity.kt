package com.dxyc.zwkfb_view

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import com.dxyc.zwkfb_view.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import 安卓.应用.置内容视图
import 安卓.组件.吐司
import 安卓.组件.文本
import 安卓.视图.置单击回调监听事件
import 安卓.视图.置长按回调监听事件
import 安卓x.应用兼容包.应用.应用兼容活动
import 安卓x.活动.启用边缘到边缘
import 科特林.应用
import 自定义.对话框类.材质底部信息对话框
import 自定义.状态栏类.状态栏沉浸式类


class MainActivity : 应用兼容活动() {

    private lateinit var binding: ActivityMainBinding

//    lateinit var player: ExoPlayer
//    lateinit var playerview: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        启用边缘到边缘()
        状态栏沉浸式类.状态栏沉浸设置(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        置内容视图(binding.root)
        init()
    }

    private fun init() {
//        playerview = findViewById(R.id.playerview)
//
//        // 1. 创建播放器
//        player = ExoPlayer.Builder(this).build()
//        playerview.player = player          // 绑定视图

//        url = "android.resource://${packageName}/${R.raw.a}"
//        "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"
//
//        val url = "https://gitee.com/dxycw/shuju/raw/master/视频/1.mp4"

//        // 2. 媒体项（可换你的 Gitee 链接）
//        val mediaItem = MediaItem.fromUri("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8")
//
//        // 3. 数据源工厂（允许 302 跨协议）
//        val dataSourceFactory = DefaultHttpDataSource.Factory()
//            .setAllowCrossProtocolRedirects(true)
//
//        // 4. 组合成 MediaSource
//        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
//            .createMediaSource(mediaItem)

//        // 5. 塞给播放器
//        player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频
        //        /2.mp4"))
//        player.prepare()
//        player.playWhenReady = true         // 自动开始
//
//        查找视图Id<Button>(R.id.btn1).置单击回调监听事件{
//            player.play() // 播放视频
//        }
//
//        查找视图Id<Button>(R.id.btn2).置单击回调监听事件{
////            player.pause() // 暂停视频
//            val vib = this.getSystemService(VIBRATOR_SERVICE) as Vibrator
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vib.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
//            } else {
//                vib.vibrate(200) // 兼容旧版本
//            }
//        }

//        val loadingIndicator = binding.loadingIndicator
//        loadingIndicator.containerColor = "#FF0000".toColorInt()

        binding.tv1.应用{
            文本 = "支持的ABI：${Build.SUPPORTED_ABIS.joinToString()}，\n本进程运行在：${Build.SUPPORTED_ABIS[0]}."
        }

        binding.btn2.应用{
            val aa = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H) // 商业.谷歌.安卓.材质.时间选择器.时间格式.CLOCK_12H
                .setHour(12)
                .setMinute(30)
                .setTitleText("选择时间")
                .setPositiveButtonText("确定")
                .build()
            aa.addOnPositiveButtonClickListener {
                binding.btn2.文本 = aa.hour.toString() + ":" + aa.minute.toString()
            }

            置单击回调监听事件{
                aa.show(supportFragmentManager, "time_picker")
            }
            置长按回调监听事件 {
                it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                true
            }
        }

        val a = binding.tabLayout.应用{
////            val tabView = getTabAt(0)?.view
//            // 禁用点击（特殊场景）
////        tabView!!.isClickable = false
////            val tabView1 = getTabAt(1)?.view
////            getTabAt(1)!!.文本 = "视频21111"
//            // 禁用点击（特殊场景）
////        tabView1!!.isClickable = false
        }

        binding.tv3.应用 {
            文本 = "标签数量：${a.tabCount}"
//            置单击回调监听事件{
//            player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频/1.mp4"))
//            player.play() // 播放视频
//            }
        }

        binding.btn4.应用{
            val a4 = 材质底部信息对话框()
                .置标题("标题12")
                .置内容("这是一个对话框")
                .置按钮方向(材质底部信息对话框.横向)
                .置忽略按钮单击事件("忽略1") { it.dismiss() }
                .置确定按钮单击事件("确定1") { it.dismiss() }
                .隐藏状态栏导航栏()
            置单击回调监听事件{
                a4.显示(supportFragmentManager, "aaa")
//                player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频/2.mp4"))
//                player.play() // 播放视频
            }
        }

        val 菜单列表框3 = binding.zCddhkLbk3
        菜单列表框3.置图标宽高(25, 25)
//        菜单列表框2.置项目单击效果(图片高级列表框.整体变灰效果)
//        菜单列表框2.置列数(5)
        菜单列表框3.添加项目(zwkfb.view.R.drawable.ic_zhuti, "工具", "下载")
        菜单列表框3.添加项目(zwkfb.view.R.drawable.ic_wode, "阅读", "下载")
        菜单列表框3.添加项目(zwkfb.view.R.drawable.ic_yingyong, "视频", "下载")
        菜单列表框3.添加项目(zwkfb.view.R.drawable.ic_caidan, "音乐", "下载")
        菜单列表框3.添加项目(zwkfb.view.R.drawable.ic_caidan, "应用", "下载")
        菜单列表框3.添加项目(zwkfb.view.R.drawable.ic_wode, "深色模式")
        菜单列表框3.添加项目(null, "深色模式", "下载")
        菜单列表框3.置单击项目事件 {项目序数: Int ->
            when (项目序数) {
                0 -> {
                    吐司.制作文本(this, "功能开发中...", 吐司.长度_短).show()
//                    上下文.startActivity(Intent(上下文, 工具主窗口::class.java))
                }
                1 -> {
//                    上下文.startActivity(Intent(上下文, 阅读主窗口::class.java))
                }
                2 -> {
//                    上下文.startActivity(Intent(上下文, 视频主窗口::class.java))
                }
                3 -> {
//                    上下文.startActivity(Intent(上下文, 音乐主窗口::class.java))
                }
                4 -> {
//                    上下文.startActivity(Intent(上下文, 应用主窗口::class.java))
                }
                5 -> {
//                    设置模块.切换主题模式(上下文)
                }
            }
        }
        菜单列表框3.置长按项目事件 {项目序数: Int ->
            when (项目序数) {
                0 -> {
                    吐司.制作文本(this, "长按...", 吐司.长度_短).show()
//                    上下文.startActivity(Intent(上下文, 工具主窗口::class.java))
                }
                1 -> {
//                    上下文.startActivity(Intent(上下文, 阅读主窗口::class.java))
                }
                2 -> {
//                    上下文.startActivity(Intent(上下文, 视频主窗口::class.java))
                }
                3 -> {
//                    上下文.startActivity(Intent(上下文, 音乐主窗口::class.java))
                }
                4 -> {
//                    上下文.startActivity(Intent(上下文, 应用主窗口::class.java))
                }
                5 -> {
//                    设置模块.切换主题模式(上下文)
                }

            }

        }
        菜单列表框3.置单击按钮事件 {项目序数: Int ->
            when (项目序数) {
                0 -> {
                    吐司.制作文本(this, "单击按钮...", 吐司.长度_短).show()
//                    上下文.startActivity(Intent(上下文, 工具主窗口::class.java))
                }
                1 -> {
//                    上下文.startActivity(Intent(上下文, 阅读主窗口::class.java))
                }
                2 -> {
//                    上下文.startActivity(Intent(上下文, 视频主窗口::class.java))
                }
                3 -> {
//                    上下文.startActivity(Intent(上下文, 音乐主窗口::class.java))
                }
                4 -> {
//                    上下文.startActivity(Intent(上下文, 应用主窗口::class.java))
                }
                5 -> {
//                    设置模块.切换主题模式(上下文)
                }
            }
        }

    }

    override fun onStop() {
        super.onStop()
//        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
//        player.release()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        状态栏沉浸式类.状态栏沉浸设置(this)
    }

}
