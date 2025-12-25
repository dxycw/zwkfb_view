package com.dxyc.zwkfb

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import 安卓.应用.查找视图Id
import 安卓.应用.置内容视图
import 安卓.组件.文本
import 安卓.组件.文本视图
import 安卓.视图.置内边距
import 安卓x.应用兼容包.应用.应用兼容活动
import 安卓x.核心.视图.取边距
import 安卓x.核心.视图.窗口边距兼容
import 安卓x.核心.视图.视图兼容
import 安卓x.活动.启用边缘到边缘

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
//            player.pause() // 暂停视频
//        }
//
//        查找视图Id<Button>(R.id.btn3).置单击回调监听事件{
//            player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频
        //            /1.mp4"))
//            player.play() // 播放视频
//        }
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
