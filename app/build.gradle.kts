plugins {
    alias(libs.plugins.android.application)
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

    namespace = "com.dxyc.zwkfb_view"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.dxyc.zwkfb_view"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    splits {
        abi {
            isEnable = true // 是否开启ABI分割
            reset()  // 清空默认的abi配置
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64") // 指定要支持的ABI
            isUniversalApk = false     // 是否生成通用apk
        }
    }

    buildTypes {
        release {
//            isMinifyEnabled = false

            isMinifyEnabled = true // 是否混淆
            isShrinkResources = true // 是否压缩资源
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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

dependencies {

    implementation(project(":zwkfb_view")){

        exclude("com.mysql", "mysql-connector-j")

        exclude("com.github.OCNYang.ImmersionBar", "immersionbar-ktx")
        exclude("com.gitee.zackratos", "UltimateBarX")

        // 如果使用了“media3”的"media3-datasource-okhttp"这个也要注释掉。
        exclude("com.squareup.okhttp3", "okhttp")
        exclude("com.google.code.gson", "gson")
        exclude("io.noties.markwon", "core")

        exclude("androidx.media3", "media3-exoplayer")
        exclude("androidx.media3", "media3-datasource-okhttp")
        exclude("androidx.media3", "media3-ui")

        exclude("com.github.CarGuo.GSYVideoPlayer", "gsyvideoplayer")
        exclude("com.github.bilibili.DanmakuFlameMaster", "DanmakuFlameMaster")

        // 使用下面库需要 需要添加 “https://maven.mozilla.org/maven2” 仓库，否则不要注释掉，不添加仓库会报错。
        exclude("org.mozilla.geckoview", "geckoview-nightly")
        exclude("org.mozilla.geckoview", "geckoview-exoplayer2-nightly")

    }

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}