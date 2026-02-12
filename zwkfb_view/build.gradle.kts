plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {

    signingConfigs {
        create("release") {
            storeFile = file("../key.jks")
            storePassword = "12345678"
            keyAlias = "key"
            keyPassword = "12345678"
        }
    }

    namespace = "zwkfb.view"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

// 关键：使用正确的 publishing 配置
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.dxycw"
            artifactId = "zwkfb_view"
            version = "0.2.7"

            // 关键：使用 afterEvaluate 获取组件
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    api("androidx.core:core-ktx:1.17.0") // 核心ktx库
    api("androidx.appcompat:appcompat:1.7.1") // appcompat库
    api("com.google.android.material:material:1.14.0-alpha09") // material库
    api("androidx.activity:activity:1.12.2") // activity库
    api("androidx.constraintlayout:constraintlayout:2.2.1") // 约束布局库
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0") // 下拉刷新库

    //=====================================================================

    api("com.google.firebase:firebase-crashlytics-buildtools:3.0.6") // firebase crashlytics 构建工具

    //=====================================================================

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //=====================================================================

    // 数据库 mysql 驱动
    api("com.mysql:mysql-connector-j:9.5.0")

    //=====================================================================

    //广告 ID 库定义了一个接口，用于在运行您应用的不同设备上与系统级广告提供程序进行交互。此接口可让您的应用接收一致的广告 ID 值。
//    api("androidx.ads:ads-identifier:1.0.0-alpha05")

    //AppSearch 是一个搜索库，用于管理本地存储的结构化数据，其中包含用于将数据编入索引和通过全文搜索来检索数据的 API。您可以使用此库来为用户构建自定义的应用内搜索功能。
//    api("androidx.appsearch:appsearch:1.1.0")

    //就是 “系统浏览器遥控器”：不装内核，只发 Intent，却能获得与 Chrome 同等性能 + 定制 UI + PWA 全屏，适合“外跳网页”场景，包体几乎零增加。
//    api("androidx.browser:browser:1.9.0")

    //通过生物识别特征或设备凭据进行身份验证，以及执行加密操作。
//    api("androidx.biometric:biometric:1.4.0-alpha05")

    //使用向后兼容的 API 使用 Android 平台的蓝牙功能。
//    api("androidx.bluetooth:bluetooth:1.0.0-alpha02")

    //它本身不是新内核，而是给系统 WebView 增加了新 API，并保证在低版本系统上也能调用，开发者无需自己写版本判断。
//    api("androidx.webkit:webkit:1.15.0")

    //=====================================================================

    // ImmersionBar沉浸式状态栏
    api("com.github.OCNYang.ImmersionBar:immersionbar:3.4.6")

    // ImmersionBar kotlin扩展（可选）
    api("com.github.OCNYang.ImmersionBar:immersionbar-ktx:3.4.6")

    // fragment快速实现（可选）已废弃
//    api("com.github.OCNYang.ImmersionBar:immersionbar-components:3.4.6")

    // UltimateBarX 状态栏和导航栏
    api("com.gitee.zackratos:UltimateBarX:v0.8.1")

    //=====================================================================

    // OkHttp3 网络请求库
    api("com.squareup.okhttp3:okhttp:5.3.2")
//    api("com.squareup.okio:okio:3.16.4")

    //=====================================================================

    // Json解析,是 Google 官方出品的 Java/Kotlin JSON 解析库 Gson 的 2.13.2 版本，用于把 JSON 字符串 ↔ Java/Kotlin 对象 之间做 序列化 / 反序列化。
    api("com.google.code.gson:gson:2.13.2")

    //=====================================================================

    // Markdown,md解析
    api("io.noties.markwon:core:4.6.2")

    //=====================================================================

    // 使用 ExoPlayer 播放媒体 核心库
    api("androidx.media3:media3-exoplayer:1.8.0")

//    // 支持使用 ExoPlayer 播放 DASH
//    api("androidx.media3:media3-exoplayer-dash:1.8.0")
//    // 支持使用 ExoPlayer 播放 HLS
//    api("androidx.media3:media3-exoplayer-hls:1.8.0")
//    // 支持 ExoPlayer 的 SmoothStreaming 播放
//    api("androidx.media3:media3-exoplayer-smoothstreaming:1.8.0")
//    // 支持使用 ExoPlayer 进行 RTSP 播放
//    api("androidx.media3:media3-exoplayer-rtsp:1.8.0")
//    // 要在 ExoPlayer 中支持 MIDI 播放
//    api("androidx.media3:media3-exoplayer-midi:1.8.0")
//    // 使用 ExoPlayer 结合互动媒体广告 SDK 插入广告
//    api("androidx.media3:media3-exoplayer-ima:1.8.0")
//
//    // 使用 Cronet 网络堆栈加载数据
//    api("androidx.media3:media3-datasource-cronet:1.8.0")
    // 使用 OkHttp 网络堆栈加载数据  网络数据源
    api("androidx.media3:media3-datasource-okhttp:1.8.0")
//    // 使用 librtmp 加载数据
//    api("androidx.media3:media3-datasource-rtmp:1.8.0")
//
//    // 使用 Compose 构建媒体播放界面
//    api("androidx.media3:media3-ui-compose:1.8.0")
    // 用于使用 Views 构建媒体播放 UI的功能。 UI 控制条（可选，但建议带上）
    api("androidx.media3:media3-ui:1.8.0")
    // 用于使用 Jetpack Compose 构建媒体播放 UI的功能。
//    api("androidx.media3:media3-ui-compose:1.8.0")
//    // 用于使用 Jetpack Leanback 库为 Android TV 构建媒体播放 UI的功能。
//    api("androidx.media3:media3-ui-leanback:1.8.0")
//
//    // 用于公开和控制媒体会话的功能。
//    api("androidx.media3:media3-session:1.8.0")
//
//    // 用于从媒体容器中提取数据的功能。 支持常见容器（mp4/mkv/ts...）
//    api("androidx.media3:media3-extractor:1.8.0")
//
//    // 用于与 Cast（投屏）集成的功能。
//    api("androidx.media3:media3-cast:1.8.0")
//
//    // 用于使用 Jetpack WorkManager 调度 ExoPlayer 的后台操作的功能。
//    api("androidx.media3:media3-exoplayer-workmanager:1.8.0")
//
//    // 用于转换媒体文件的功能。
//    api("androidx.media3:media3-transformer:1.8.0")
//
//    // 用于在视频帧上应用特效的功能。
//    api("androidx.media3:media3-effect:1.8.0")
//
//    // 用于混流（muxing）媒体文件的功能。
//    api("androidx.media3:media3-muxer:1.8.0")

    // 用于测试媒体组件（包括 ExoPlayer 组件）的实用工具
//    api("androidx.media3:media3-test-utils:1.8.0") // 不行
    // 通过 Robolectric 测试媒体组件（包括 ExoPlayer 组件）的实用工具
//    api("androidx.media3:media3-test-utils-robolectric:1.8.0") // 不行

//    // 读写媒体容器的通用功能
//    api("androidx.media3:media3-container:1.8.0")
//    // 媒体数据库组件的通用功能
//    api("androidx.media3:media3-database:1.8.0")
//    // 媒体解码器的通用功能
//    api("androidx.media3:media3-decoder:1.8.0")
//    // 加载数据的通用功能
//    api("androidx.media3:media3-datasource:1.8.0")
//    // 跨多个媒体库使用的通用功能
//    api("androidx.media3:media3-common:1.8.0")
//    // Kotlin 专用通用功能
//    api("androidx.media3:media3-common-ktx:1.8.0")

    //=====================================================================

    // GSYVideoPlayer 播放器
    api("com.github.CarGuo.GSYVideoPlayer:gsyvideoplayer:v11.3.0")

    //=====================================================================

    // 弹幕,B 站开源的一款 Android 弹幕解析与绘制引擎，主打“高性能 + 低耦合 + 易集成”，能让任何视频/直播类 App 在 1-2 天内快速拥有“B 站级”弹幕体验。
    api("com.github.bilibili.DanmakuFlameMaster:DanmakuFlameMaster:v0.9.25")

    //=====================================================================

    // 浏览器 Geckoview 浏览器内核  nightly 版本,会提前开放实验性 API（如新增 WebExtension、Web 标准实现等），接口随时可能变动或被移除 。
    api("org.mozilla.geckoview:geckoview-nightly:145.0.20251006095753") // 最后一个支持Api 21

    api("org.mozilla.geckoview:geckoview-exoplayer2-nightly:145.0.20251006095753") // 最后一个支持Api 21

    //=====================================================================

    // 相机 media3
//    api("androidx.camera.media3:media3-effect:1.0.0-alpha04")

    //=====================================================================

    //查询相机功能。
//    api("androidx.camera.featurecombinationquery:featurecombinationquery:1.6.0-alpha02")
//    api("androidx.camera.featurecombinationquery:featurecombinationquery-play-services:1.6.0-alpha02")

    //=====================================================================

    // 适用于相机的独立可组合项和基于视图的取景器"
//    api("androidx.camera.viewfinder:viewfinder-view:1.6.0-alpha02")
//    api("androidx.camera.viewfinder:viewfinder-core:1.6.0-alpha02")
//    api("androidx.camera.viewfinder:viewfinder-compose:1.6.0-alpha02")

    //=====================================================================

    // CameraX 是 Jetpack 的新增库。利用该库，可以更轻松地向应用添加相机功能。
    // 该库提供了很多兼容性修复程序和解决方法，有助于在众多设备上打造一致的开发者体验。
//    api("androidx.camera:camera-core:1.5.2")
//    api("androidx.camera:camera-camera2:1.5.2")
    // 如果你还想使用 CameraX 生命周期库
//    api("androidx.camera:camera-lifecycle:1.5.2")
    // 如果你想额外使用 CameraX VideoCapture 库
//    api("androidx.camera:camera-video:1.5.2")
    // 如果你还想使用 CameraX 视图类
//    api("androidx.camera:camera-view:1.5.2")
    // 如果你想额外添加 CameraX ML Kit 视觉集成
//    api("androidx.camera:camera-mlkit-vision:1.5.2")
    // 如果你还想使用 CameraX 扩展库
//    api("androidx.camera:camera-extensions:1.5.2")

    // MLKit 条形码扫描
//    api("com.google.mlkit:barcode-scanning:17.3.0")

    //=====================================================================

    // 二维码
//    api("com.github.jenly1314:zxing-lite:3.3.0")

    //=====================================================================

    //公共库 (*必须) MLKit 二维码
//    api("com.github.jenly1314.MLKit:mlkit-common:2.3.0")
    //条码识别 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-barcode-scanning:2.3.0")
    //人脸检测 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-face-detection:2.3.0")
    //人脸网格检测 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-face-mesh-detection:2.3.0")
    //图像标签 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-image-labeling:2.3.0")
    //对象检测 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-object-detection:2.3.0")
    //姿势检测 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-pose-detection:2.3.0")
    //姿势检测精确版 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-pose-detection-accurate:2.3.0")
    //自拍分割 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-segmentation-selfie:2.3.0")
    //文字识别 (可选)
//    api("com.github.jenly1314.MLKit:mlkit-text-recognition:2.3.0")

    //=====================================================================

    // XUI 通用UI库
//    api("com.github.xuexiangjys:XUI:1.2.1")

    //=====================================================================

    // DialogX对话框
//    api("com.github.kongzue.DialogX:DialogX:0.0.50")

    //=====================================================================

    //是 “Android/Kotlin 样板代码粉碎机”——把官方 SDK / Jetpack 里最常用的 200+
    // 个小痛点拆成 单功能、零依赖、<3 kB 的微模块，想用哪个就引哪个，不写重复工具类。
//    api("com.louiscad.splitties:splitties-fun-pack-android-base:3.0.0")
//    api("com.louiscad.splitties:splitties-fun-pack-android-base-with-views-dsl:3.0.0")
//    api("com.louiscad.splitties:splitties-fun-pack-android-appcompat:3.0.0")
//    api("com.louiscad.splitties:splitties-fun-pack-android-appcompat-with-views-dsl:3.0.0")
//    api("com.louiscad.splitties:splitties-fun-pack-android-material-components:3.0.0")
//    api("com.louiscad.splitties:splitties-fun-pack-android-material-components-with-views-dsl:3.0.0")

    //=====================================================================


    //=====================================================================


    //=====================================================================


    //=====================================================================
}