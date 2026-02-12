package com.dxyc.zwkfb_view

import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import com.dxyc.zwkfb_view.databinding.ActivityMainBinding
import 商业.谷歌.安卓.材质.标签.文本
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
import 自定义.对话框类.材质底部信息对话框

class MainActivity : 应用兼容活动() {
    private lateinit var binding: ActivityMainBinding

//    lateinit var player: ExoPlayer
//    lateinit var playerview: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        启用边缘到边缘()
        binding = ActivityMainBinding.inflate(layoutInflater)
        置内容视图(binding.root)
        视图兼容.置应用窗口边距回调监听器(binding.main) { 视图, 边距 ->
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

        binding.btn1.应用{
            文本 = "支持的ABI：${Build.SUPPORTED_ABIS.joinToString()}，\n本进程运行在：${Build.SUPPORTED_ABIS[0]}."
        }

        binding.btn2.应用{
            置长按回调监听事件 {
                it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                true
            }
            置单击回调监听事件{
//                val aa = MaterialTimePicker.Builder()
//                    .setTimeFormat(TimeFormat.CLOCK_24H) // 商业.谷歌.安卓.材质.时间选择器.时间格式.CLOCK_12H
//                    .setHour(12)
//                    .setMinute(30)
//                    .setTitleText("选择时间")
//                    .setPositiveButtonText("确定")
//                    .build()
//
//                aa.addOnPositiveButtonClickListener { view ->
//                    binding.btn2.文本 = aa.hour.toString() + ":" + aa.minute.toString()
//                }
//                aa.show(supportFragmentManager, "time_picker")
            }
        }

        val a = binding.tabLayout.应用{
            val tabView = getTabAt(0)?.view

            // 禁用点击（特殊场景）
//        tabView!!.isClickable = false

            val tabView1 = getTabAt(1)?.view
            getTabAt(1)!!.文本 = "视频21111"
            // 禁用点击（特殊场景）
//        tabView1!!.isClickable = false
        }

        binding.btn3.应用 {
            文本 = "标签数量：${a.tabCount}"
//            置单击回调监听事件{
//            player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频/1.mp4"))
//            player.play() // 播放视频
//            }

            val aaa = 材质底部信息对话框()
                .置标题("标题")
                .置内容("这是一个对话框")
                .置按钮方向(材质底部信息对话框.纵向)
                .置确定按钮单击事件("确定1") { it.dismiss() }
                .隐藏对话框子窗口状态栏(true)

//                .显示对话框子窗口状态栏()
            置单击回调监听事件 { aaa.显示(supportFragmentManager, "aaa") }

        }

        binding.btn4.应用{
//            val a1 = Dialog(this@MainActivity)
//            a1.setTitle("标题")
//            a1.setContentView(
//                CardView(this@MainActivity).apply {
//                    radius = 20f
//                    setContentPadding(20, 20, 20, 20)
//                    addView(TextView(this@MainActivity).apply { text = "这是一个对话框" })
//                }
//            )
            置单击回调监听事件{
//                a1.show()
//                player.setMediaSource(App.用网址播放视频("https://gitee.com/dxycw/shuju/raw/master/视频/2.mp4"))
//                player.play() // 播放视频
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

}
