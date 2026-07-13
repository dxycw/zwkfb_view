
> [!CAUTION]
>
> **注意：本库是本作者个人私库（不公开分享本库，不建议他人使用），如果想使用本库可以在项目中使用依赖库或克隆分支（可以自己新建一个分支修改本库，不可提交到本库），请不要上传提交，请勿私自外传本项目。**


<div align="center">

<h1>
  Zwkfb-View
</h1>

**一款强大的Kotlin 的xml项目，“zwkfb”中文开发包，适用于Android的View版的xml布局项目。**

[![GitHub](https://jitpack.io/v/dxycw/zwkfb-view.svg)](https://jitpack.io/#dxycw/zwkfb-view)
![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)

</div>

> [!NOTE]
>
> 如果使用的是Jetpack Compose Multiplatform项目，请使用[zwkfb](https://github.com/dxycw/zwkfb)。


# 使用方法

1、在项目的 settings.gradle 文件中添加 JitPack 仓库：

* Groovy 版本：

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }  // 添加 JitPack 仓库
        maven { url 'https://maven.mozilla.org/maven2' } // 使用org.mozilla.geckoview依赖库需要添加此仓库
    }
}
```

* Kotlin 版本：

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven("https://jitpack.io")  // 添加 JitPack 仓库
        maven("https://maven.mozilla.org/maven2")  // 使用org.mozilla.geckoview依赖库需要添加此仓库
    }
}
```

2、在项目的 build.gradle 文件中添加依赖项：

* Groovy 版本：

```groovy
dependencies {
    implementation 'com.github.dxycw:zwkfb-view:0.4.0' // 添加 zwkfb-view 依赖
}
```

* Kotlin 版本：

```kotlin
dependencies {
    implementation("com.github.dxycw:zwkfb-view:0.4.0") // 添加 zwkfb-view 依赖
}
```

3、就可以在项目中使用了。

# 使用的依赖库

下面是本依赖库所有[使用的依赖库](%E6%96%87%E6%A1%A3/%E4%BD%BF%E7%94%A8%E7%9A%84%E4%BE%9D%E8%B5%96%E5%BA%93.md)。

# 打包体积压缩

如果使用混淆并且使用了“MySQL”、“Geckoview”依赖库，请查看[打包体积压缩](%E6%96%87%E6%A1%A3/%E6%89%93%E5%8C%85%E4%BD%93%E7%A7%AF%E5%8E%8B%E7%BC%A9.md)。

# 更新内容

## 0.4.0

* 优化 项目文档，完善使用方法、平台支持、依赖库等信息；
* 修改 下载器类的“浏览器文件下载”方法；
* 添加 对话框类的 “浏览器下载对话框()”、“材质浏览器下载对话框()” 方法；
* 更新 com.squareup.okhttp3:okhttp 依赖库版本为 5.4.0；
* 更新 com.github.jenly1314:zxing-lite 依赖库版本为 3.5.0；
* 删除 [zwkfb-compose](https://github.com/dxycw/zwkfb-compose)库；

# 老版本更新内容

* [0.2.x系列版本](%E6%96%87%E6%A1%A3/%E6%9B%B4%E6%96%B0%E5%86%85%E5%AE%B9/0.2.x%E7%B3%BB%E5%88%97%E7%89%88%E6%9C%AC.md)
* [0.3.x系列版本](%E6%96%87%E6%A1%A3/%E6%9B%B4%E6%96%B0%E5%86%85%E5%AE%B9/0.3.x%E7%B3%BB%E5%88%97%E7%89%88%E6%9C%AC.md)