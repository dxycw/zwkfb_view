package com.dxyc.zwkfb_view

import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.core.net.toUri
import com.dxyc.zwkfb_view.databinding.ActivityMainBinding
import com.google.android.material.timepicker.TimeFormat
import 商业.谷歌.安卓.材质.时间选择器.材质时间选择器
import 安卓.媒体.媒体播放器
import 安卓.应用.日期选择对话框
import 安卓.应用.显示
import 安卓.应用.置按钮
import 安卓.操作系统.构建
import 安卓.组件.吐司
import 安卓.组件.文本
import 安卓.视图.置单击回调监听事件
import 安卓.视图.置长按回调监听事件
import 安卓x.应用兼容.应用.应用兼容活动
import 安卓x.应用兼容.应用.置内容视图
import 科特林.应用
import 自定义.对话框类.材质底部面板信息对话框
import 自定义.状态栏类.状态栏沉浸式类

class MainActivity : 应用兼容活动() {

//    lateinit var player: ExoPlayer
//    lateinit var playerview: PlayerView

    val 视图组件: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        置内容视图(视图组件.root)
        状态栏沉浸式类.状态栏沉浸设置(this)
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

        视图组件.tv1.应用{
            文本 = "支持的ABI：${构建.支持_ABIS.joinToString()}\n本进程运行在：${构建.支持_ABIS[0]}."
        }

        视图组件.btn2.应用{
            val aa = 材质时间选择器.构建器()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(30)
                .setTitleText("选择时间")
                .setPositiveButtonText("确定")
                .build()
            aa.addOnPositiveButtonClickListener {
                视图组件.btn2.文本 = aa.hour.toString() + ":" + aa.minute.toString()
            }
            置单击回调监听事件{
                aa.show(supportFragmentManager, "time_picker")
            }
            置长按回调监听事件 {
                it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                true
            }
        }
        val a = 视图组件.tabLayout.应用{
//            val tabView = getTabAt(0)?.view
            // 禁用点击（特殊场景）
//        tabView!!.isClickable = false
//            val tabView1 = getTabAt(1)?.view
//            getTabAt(1)!!.文本 = "视频21111"
            // 禁用点击（特殊场景）
//        tabView1!!.isClickable = false
        }
        视图组件.tv3.应用 {
            文本 = "标签数量：${a.tabCount}"
//            置单击回调监听事件{
//            player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频/1.mp4"))
//            player.play() // 播放视频
//            }
        }
        视图组件.btn4.应用{
            val a4 = 材质底部面板信息对话框()
                .置标题("标题12")
                .置内容("这是一个对话框")
                .置按钮方向(材质底部面板信息对话框.横向)
                .置忽略按钮单击事件("忽略1") { it.dismiss() }
                .置确定按钮单击事件("确定1") { it.dismiss() }
                .隐藏状态栏导航栏()
            置单击回调监听事件{
                a4.显示(supportFragmentManager, "aaa")
//                player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频/2.mp4"))
//                player.play() // 播放视频
            }
        }

        val 菜单列表框3 = 视图组件.zCddhkLbk3
        菜单列表框3.置图标宽高(25, 25)
        菜单列表框3.添加项目(zwkfb.view.R.drawable.ic_zhuti, "工具", "下载")
        菜单列表框3.置单击项目事件 {项目序数: Int ->
            when (项目序数) {
                0 -> {
                    吐司.制作文本(this, "功能开发中...", 吐司.长度_短).显示()
                }
            }
        }
        菜单列表框3.置长按项目事件 {项目序数: Int ->
            when (项目序数) {
                0 -> {
                    吐司.制作文本(this, "长按...", 吐司.长度_短).显示()
                }
            }
        }
        菜单列表框3.置单击按钮事件 {项目序数: Int ->
            when (项目序数) {
                0 -> {
                    吐司.制作文本(this, "单击按钮...", 吐司.长度_短).显示()
                }
            }
        }

        val 文本高级列表框 = 视图组件.zCddhkLbk2
        文本高级列表框.添加项目( "工具")
        文本高级列表框.添加项目("阅读")
        文本高级列表框.置单击项目事件 {
            when (it) {
                0 -> {
                    吐司.制作文本(this, "功能开发中...", 吐司.长度_短).显示()
                }
                1 -> {}
            }
        }

        mediaPlayer = 媒体播放器.创建(this, "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8".toUri())

        val sc = 视图组件.surfaceView
        sc.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                mediaPlayer?.setSurface(holder.surface)
            }
            override fun surfaceDestroyed(p0: SurfaceHolder) {
                mediaPlayer?.setSurface(null)  // 防止后续绘制到已销毁的 Surface
            }
            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

            }
        })

        val bf = 视图组件.bf
        bf.setOnClickListener {
//            if (mediaPlayer!!.isPlaying){
//                mediaPlayer!!.暂停() // pause()
//                bf.text = "播放"
//            }else {
//                // 播放视频
//                mediaPlayer!!.开始() // start()
//                bf.text = "暂停"
//            }

            val aaaaa = 日期选择对话框(this)
            aaaaa.setTitle("请选择日期")
            aaaaa.置按钮(DialogInterface.BUTTON_POSITIVE, "确定"){dialog, which ->
                Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show()
            }
            aaaaa.显示()

        }

        日期选择对话框(this)

    }


    private var mediaPlayer:媒体播放器? = null
    
    override fun onStop() {
        super.onStop()
//        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
//        player.release()
        mediaPlayer!!.释放() // 添加这句
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        状态栏沉浸式类.状态栏沉浸设置(this)
    }

}
