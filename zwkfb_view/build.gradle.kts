@file:Suppress("DEPRECATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    api("androidx.core:core-ktx:1.17.0") // 核心ktx库
    api("androidx.appcompat:appcompat:1.7.1") // appcompat库
    api("com.google.android.material:material:1.13.0") // material库
    api("androidx.activity:activity:1.12.1") // activity库
    api("androidx.constraintlayout:constraintlayout:2.2.1") // 约束布局库
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0") // 下拉刷新库

    api("com.google.firebase:firebase-crashlytics-buildtools:3.0.6") // firebase crashlytics 构建工具

    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)

    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))

    //=====================================================================

    // ImmersionBar沉浸式状态栏
    api("com.github.OCNYang.ImmersionBar:immersionbar:3.4.6")

    // ImmersionBar kotlin扩展（可选）
    api("com.github.OCNYang.ImmersionBar:immersionbar-ktx:3.4.6")

    // UltimateBarX 状态栏和导航栏
    api("com.gitee.zackratos:UltimateBarX:v0.8.1")

    //=====================================================================

    // OkHttp3 网络请求库
    api("com.squareup.okhttp3:okhttp:5.3.2")
//    api("com.squareup.okio:okio:3.16.4")

    // Json解析,是 Google 官方出品的 Java/Kotlin JSON 解析库 Gson 的 2.13.2 版本，用于把 JSON 字符串 ↔ Java/Kotlin 对象 之间做 序列化 / 反序列化。
    api("com.google.code.gson:gson:2.13.2")

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

    // 弹幕
    api("com.github.bilibili.DanmakuFlameMaster:DanmakuFlameMaster:v0.9.25")

    //=====================================================================

}