# zwkfb_view

[![](https://jitpack.io/v/dxycw/zwkfb_view.svg)](https://jitpack.io/#dxycw/zwkfb_view)

本项目是中文开发的开发包项目，适用于Android的View版的xml布局项目。
如果你使用的是Jetpack Compose项目，请使用[zwkfb_compose](https://github.com/dxycw/zwkfb_compose)。

# 使用方法

1、在项目的 settings.gradle 文件中添加 JitPack 仓库：

* Groovy 版本：

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }  // 添加 JitPack 仓库
    }
}
```

* Kotlin 版本：

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // 添加 JitPack 仓库
    }
}
```

2、在项目的 build.gradle 文件中添加依赖项：

* Groovy 版本：

```groovy
dependencies {
    implementation 'com.github.dxycw:zwkfb_view:0.2.1' // 添加 zwkfb_view 依赖
}
```

* Kotlin 版本：

```kotlin
dependencies {
    implementation("com.github.dxycw:zwkfb_view:0.2.1") // 添加 zwkfb_view 依赖
}
```

3、就可以在项目中使用了。

# 使用的依赖库

下面是本依赖库所有使用的依赖库。

## 官方依赖库

* androidx.core:core-ktx:1.17.0 
* androidx.appcompat:appcompat:1.7.1 
* com.google.android.material:material:1.13.0 
* androidx.activity:activity:1.12.1 
* androidx.constraintlayout:constraintlayout:2.2.1 
* androidx.swiperefreshlayout:swiperefreshlayout:1.1.0 
* com.google.firebase:firebase-crashlytics-buildtools:3.0.6 

## 其他依赖库

* com.github.OCNYang.ImmersionBar:immersionbar:3.4.6 (在0.1.8版本添加 ,[ImmersionBar](https://github.com/OCNYang/ImmersionBar))
* com.github.OCNYang.ImmersionBar:immersionbar-ktx:3.4.6 (在0.1.8版本添加 ,[ImmersionBar](https://github.com/OCNYang/ImmersionBar))
* com.gitee.zackratos:UltimateBarX:v0.8.1 (在0.1.8版本添加 ,[UltimateBarX](https://gitee.com/zackratos/UltimateBarX))
* com.squareup.okhttp3:okhttp:5.3.2 (在0.1.3版本添加 ,[OkHttp](https://square.github.io/okhttp/))
* com.google.code.gson:gson:2.13.2 (在0.1.5版本添加 ,[Gson](https://github.com/google/gson))
* io.noties.markwon:core:4.6.2 (在0.1.5版本添加 ,[Markwon](https://github.com/noties/Markwon))
* androidx.media3:media3-exoplayer:1.8.0 (在0.1.8版本添加 ，[ExoPlayer](https://github.com/androidx/media))
* androidx.media3:media3-datasource-okhttp:1.8.0 (在0.1.8版本添加 ，[OkHttp](https://github.com/androidx/media))
* androidx.media3:media3-ui:1.8.0 (在0.1.8版本添加 ，[Media3 UI](https://github.com/androidx/media))
* com.github.CarGuo.GSYVideoPlayer:gsyvideoplayer:v11.3.0 (在0.1.8版本添加 ，[GSYVideoPlayer](https://github.com/CarGuo/GSYVideoPlayer))
* com.github.bilibili.DanmakuFlameMaster:DanmakuFlameMaster:v0.9.25 (在0.1.8版本添加 ，[DanmakuFlameMaster](https://github.com/bilibili/DanmakuFlameMaster))
* org.mozilla.geckoview:geckoview:146.0.20251201213807 (在0.2.1版本添加 ，[Geckoview](https://mozilla.github.io/geckoview/javadoc/mozilla-central/org/mozilla/geckoview/doc-files/CHANGELOG))

# 打包体积压缩

## 一、打包分包

1、在项目的 build.gradle 文件中使用“splits”配置分包，如下配置：

* Groovy 版本：

```groovy
android{
    splits {
        abi {
            enable true // 是否开启ABI分割
            reset()  // 清空默认的abi配置
            include "armeabi-v7a", "arm64-v8a", "x86", "x86_64" // 指定要支持的ABI
            universalApk false     // 是否生成通用apk
        }
    }
}
```

* Kotlin 版本：

```kotlin
android{
    splits {
        abi {
            isEnable = true // 是否开启ABI分割
            reset()  // 清空默认的abi配置
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64") // 指定要支持的ABI
            isUniversalApk = false     // 是否生成通用apk
        }
    }
}
```

2、注意：因为“Geckoview”只有"x86", "x86_64"两个架构，但是有大部分手机还有这两个架构，所以必须写。

## 二、混淆规则

1、在项目的 build.gradle 文件中使用了混淆，如下配置：

* Groovy 版本：

```groovy
android {
    buildTypes {
        release {
            minifyEnabled true // 是否混淆
            shrinkResources true // 是否压缩资源
        }
    }
}
```

* Kotlin 版本：

```kotlin
android {
    buildTypes {
        release {
            isMinifyEnabled = true // 是否混淆
            isShrinkResources = true // 是否压缩资源
        }
    }
}
```

2、使用“GSYVideoPlayer”视频播放库，需要添加以下混淆规则，否则视频不能正常播放：

```
-keep class com.shuyu.gsyvideoplayer.video.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.**
-keep class com.shuyu.gsyvideoplayer.video.base.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.base.**
-keep class com.shuyu.gsyvideoplayer.utils.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.utils.**
-keep class com.shuyu.gsyvideoplayer.player.** {*;}
-dontwarn com.shuyu.gsyvideoplayer.player.**
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**
-keep class androidx.media3.** {*;}
-keep interface androidx.media3.**

-keep class com.shuyu.alipay.** {*;}
-keep interface com.shuyu.alipay.**

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, java.lang.Boolean);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
```

## 三、去掉未使用的依赖库

1、在项目的 build.gradle 文件中添加用不到的依赖项：

* Groovy 版本：

```groovy
dependencies {
    implementation 'com.github.dxycw:zwkfb_view:0.2.1'{
        exclude group: '', module: 'mysql-connector-java-5.1.49'
        
        exclude group: 'com.github.OCNYang.ImmersionBar', module: 'immersionbar-ktx'
        exclude group: "com.gitee.zackratos", module: "UltimateBarX"

        // 如果使用了“media3”的"media3-datasource-okhttp"这个也要注释掉。
        exclude group: "com.squareup.okhttp3", module: "okhttp"
        exclude group: "com.google.code.gson", module: "gson"
        exclude group: "io.noties.markwon", module: "core"

        exclude group: "androidx.media3", module: "media3-exoplayer"
        exclude group: "androidx.media3", module: "media3-datasource-okhttp"
        exclude group: "androidx.media3", module: "media3-ui"

        exclude group: "com.github.CarGuo.GSYVideoPlayer", module: "gsyvideoplayer"
        exclude group: "com.github.bilibili.DanmakuFlameMaster", module: "DanmakuFlameMaster"

        exclude group: "org.mozilla.geckoview", module: "geckoview"
    }
}
```

* Kotlin 版本：

```kotlin
dependencies {
    implementation("com.github.dxycw:zwkfb_view:0.2.1"){
        exclude("", "mysql-connector-java-5.1.49")
        
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

        exclude("org.mozilla.geckoview", "geckoview")
    }
}
```

2、如果在你的项目中使用到了以上依赖库，注释掉或在项目中添加需要的依赖库。

# 更新内容

## 0.2.1

* 新增 android.view.View 类添加“横向滑动”、“纵向滑动”、“嵌套滑动”属性；
* 新增 android.view.View 类添加“取横向滑动()”、“置横向滑动()”、“取纵向滑动()”、“置纵向滑动()”、“取嵌套滑动()”、“置嵌套滑动()”函数；
* 新增 org.mozilla.geckoview:geckoview 依赖库 [Geckoview](https://mozilla.github.io/geckoview/javadoc/mozilla-central/org/mozilla/geckoview/doc-files/CHANGELOG)；
* 新增 “ic_shipin”、“ic_yingyong”图标

## 0.2.0

* 重写库；
* 新增 android.view.View 类添加“滑动x”、“滑动y”属性；
* 新增 android.view.View 类添加“取x()”、“置x()”、“取y()”、“置y()”、“取滑动x()”、“置滑动x()”、“取滑动y()”、“置滑动y()”函数；
* 新增 android.view.ViewGroup 类添加“移除所有视图()”函数；
