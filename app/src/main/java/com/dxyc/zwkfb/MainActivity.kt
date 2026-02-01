package com.dxyc.zwkfb

import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.widget.Button
import android.widget.TextView
import 商业.谷歌.安卓.材质.标签.文本
import 商业.谷歌.安卓.材质.标签.标签布局
import 安卓.应用.查找视图Id
import 安卓.应用.置内容视图
import 安卓.组件.文本
import 安卓.视图.置内边距
import 安卓.视图.置单击回调监听事件
import 安卓.视图.置长按回调监听事件
import 安卓x.应用兼容包.应用.应用兼容活动
import 安卓x.核心.视图.取边距
import 安卓x.核心.视图.窗口边距兼容
import 安卓x.核心.视图.视图兼容
import 安卓x.活动.启用边缘到边缘
import 科特林.应用


class MainActivity : 应用兼容活动() {

//    lateinit var player: ExoPlayer
//    lateinit var playerview: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        启用边缘到边缘()
        置内容视图(R.layout.activity_main)
        视图兼容.置应用窗口边距回调监听器(查找视图Id(R.id.main)) { 视图, 边距 ->
            val 系统栏 = 边距.取边距(窗口边距兼容.类型.系统栏())
            视图.置内边距(系统栏.left, 系统栏.top, 系统栏.right, 系统栏.bottom)
            边距
        }
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

//        val loadingIndicator = 查找视图Id<加载指示器>(R.id.loading_indicator)
//        loadingIndicator.addView(查找视图Id<Button>(R.id.btn2))

        查找视图Id<Button>(R.id.btn2).置长按回调监听事件 {
            it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            true
        }

        val a = 查找视图Id<标签布局>(R.id.tab_layout)

        val tabView = a.getTabAt(0)?.view

        // 禁用点击（特殊场景）
//        tabView!!.isClickable = false

        val tabView1 = a.getTabAt(1)?.view
        a.getTabAt(1)!!.文本 = "视频21111"
        // 禁用点击（特殊场景）
//        tabView1!!.isClickable = false

        查找视图Id<Button>(R.id.btn3).应用{
            文本 = "标签数量：${a.tabCount}"
            置单击回调监听事件{
//            player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频
//                    /1.mp4"))
//            player.play() // 播放视频
            }
        }
//
//        查找视图Id<Button>(R.id.btn4).置单击回调监听事件{
//            player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频
        //            /2.mp4"))
//            player.play() // 播放视频
//        }



//        val myAbi = Build.SUPPORTED_ABIS.joinToString()

        查找视图Id<TextView>(R.id.btn1).文本 = "本进程运行在：${Build.SUPPORTED_ABIS[0]}."

    }

//    override fun onStop() {
//        super.onStop()
//        player.playWhenReady = false
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        player.release()
//    }

}
