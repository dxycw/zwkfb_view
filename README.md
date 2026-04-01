# zwkfb_view

[![](https://jitpack.io/v/dxycw/zwkfb_view.svg)](https://jitpack.io/#dxycw/zwkfb_view)

本项目是中文开发的开发包项目，适用于Android的View版的xml布局项目。 如果你使用的是Jetpack Compose项目，请使用[zwkfb_compose](https://github.com/dxycw/zwkfb_compose)。

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
        maven { url = uri("https://jitpack.io") } // 添加 JitPack 仓库
        maven { url = uri("https://maven.mozilla.org/maven2") } // 使用org.mozilla.geckoview依赖库需要添加此仓库
    }
}
```

2、在项目的 build.gradle 文件中添加依赖项：

* Groovy 版本：

```groovy
dependencies {
    implementation 'com.github.dxycw:zwkfb_view:0.3.8' // 添加 zwkfb_view 依赖
}
```

* Kotlin 版本：

```kotlin
dependencies {
    implementation("com.github.dxycw:zwkfb_view:0.3.8") // 添加 zwkfb_view 依赖
}
```

3、就可以在项目中使用了。

# 使用的依赖库

下面是本依赖库所有使用的依赖库。

## 官方依赖库

* androidx.core:core-ktx:1.18.0
* androidx.appcompat:appcompat:1.7.1 
* com.google.android.material:material:1.14.0-alpha10 ([material](https://github.com/material-components/material-components-android))
* androidx.activity:activity:1.13.0
* androidx.constraintlayout:constraintlayout:2.2.1 
* androidx.swiperefreshlayout:swiperefreshlayout:1.2.0
* androidx.webkit:webkit:1.16.0-alpha04

## 其他依赖库

* com.github.OCNYang.ImmersionBar:immersionbar:3.4.6 ([ImmersionBar](https://github.com/OCNYang/ImmersionBar))
* com.github.OCNYang.ImmersionBar:immersionbar-ktx:3.4.6 ([ImmersionBar](https://github.com/OCNYang/ImmersionBar))
* com.gitee.zackratos:UltimateBarX:v0.8.1 ([UltimateBarX](https://gitee.com/zackratos/UltimateBarX))
* com.squareup.okhttp3:okhttp:5.3.2 ([OkHttp](https://square.github.io/okhttp/))
* com.google.code.gson:gson:2.13.2 ([Gson](https://github.com/google/gson))
* io.noties.markwon:core:4.6.2 ([Markwon](https://github.com/noties/Markwon))
* androidx.media3:media3-exoplayer:1.10.0 ([ExoPlayer](https://github.com/androidx/media))
* androidx.media3:media3-datasource-okhttp:1.10.0 ([OkHttp](https://github.com/androidx/media))
* androidx.media3:media3-ui:1.10.0 ([Media3 UI](https://github.com/androidx/media))
* com.github.CarGuo.GSYVideoPlayer:gsyvideoplayer:v12.0.0 ([GSYVideoPlayer](https://github.com/CarGuo/GSYVideoPlayer))
* com.github.bilibili.DanmakuFlameMaster:DanmakuFlameMaster:0.9.25 ([DanmakuFlameMaster](https://github.com/bilibili/DanmakuFlameMaster))
* org.mozilla.geckoview:geckoview-nightly:145.0.20251006095753 ([Geckoview](https://mozilla.github.io/geckoview/javadoc/mozilla-central/org/mozilla/geckoview/doc-files/CHANGELOG))
* org.mozilla.geckoview:geckoview-exoplayer2-nightly:145.0.20251006095753 ([Geckoview](https://mozilla.github.io/geckoview/javadoc/mozilla-central/org/mozilla/geckoview/doc-files/CHANGELOG))
* mysql:mysql-connector-java:5.1.49 ([MySQL Connector Java](https://mvnrepository.com/artifact/mysql/mysql-connector-java))
* com.github.jenly1314:zxing-lite:3.4.1 ([ZXing Lite](https://github.com/jenly1314/ZXingLite))

# 打包体积压缩

如果使用混淆并且使用了“MySQL”、“Geckoview”依赖库，请查看[打包体积压缩](%E6%89%93%E5%8C%85%E4%BD%93%E7%A7%AF%E5%8E%8B%E7%BC%A9.md)。

# 更新内容

## 0.3.8

* 添加 “导航类”类的“显示菜单信息个数”静态方法；
* 添加 “安卓.应用.应用程序”、“安卓.应用.日期选择对话框”、“安卓.应用.时间选择对话框”、“安卓.应用.活动”类添加所有能用的方法；
* 更新 所有 androidx.media3 依赖库版本为 1.10.0；
* 更新 androidx.webkit:webkit 依赖库版本为 1.16.0-alpha04；

## 0.3.7

* 修复 “自定义.网络类.网页视图”的“默认配置”函数“snssdk35”不能加载的Bug；
* 修改 “自定义.网络类.网页配置”为“自定义.网络类.网页设置”；
* 更新 androidx.webkit:webkit 依赖库版本为 1.16.0-alpha03；
* 更新 所有 androidx.media3 依赖库版本为 1.10.0-rc02；

## 0.3.6

* 修复 “构建”类使用后闪退的Bug；
* 添加 “列表碎片”、“媒体路由按钮”、“对话框”、“对话框碎片”类的所有函数代码；
* 添加 “列表碎片”、“媒体路由按钮”、“对话框”、“对话框碎片”类添加kotlin的函数代码；

## 0.3.5

* 修复 本版本彻底修复“自定义.系统类.文件类.取Assets文件()”方法只显示第一行的Bug；
* 修复 本版本彻底修复“网页视图”类的“默认配置()”方法使用后“今日头条”、“QQ”登录、“微博”、“搜狗”显示不能加载的Bug；
* 删除 “基础应用兼容活动”和“基础碎片”类；
* 删除 手机不能使用的组件；
* 添加 “吐司”、“构建”、“倒计时器”、“媒体播放器”类的所有函数代码；
* 优化 “材质时间选择器”类代码；

## 0.3.4

* 修复 “自定义.系统类.文件类.取Assets文件()”方法只显示第一行的Bug；
* 修复 “网页视图”类的“默认配置()”方法使用后“今日头条”、“QQ”登录、“微博”、“搜狗”显示不能加载的Bug；
* 更新 androidx.core:core-ktx依赖库为1.18.0；
* 更新 com.google.android.material:material依赖库为1.14.0-alpha10；
* 更新 androidx.activity:activity依赖库为1.13.0；
* 更新 androidx.media3:media3-exoplayer依赖库为1.10.0-rc01；
* 更新 androidx.media3:media3-datasource-okhttp依赖库为1.10.0-rc01；
* 更新 androidx.media3:media3-ui依赖库为1.10.0-rc01；
* 更新 com.github.CarGuo.GSYVideoPlayer:gsyvideoplayer依赖库为v12.0.0；
* 更新 com.github.jenly1314:zxing-lite依赖库为3.4.1；
* 删除 com.google.firebase:firebase-crashlytics-buildtools依赖库；

## 0.3.3

* 修复 Android高版本使用 `吐司.制作文本(上下文, 文本, 持续时间).显示()` 闪退的Bug；
* 添加 “安卓x.应用兼容.应用.警告对话框”、“安卓.应用.警告对话框”类的代码；
* 修改 “材质底部信息对话框”类为“材质底部面板信息对话框”类；
* 修改 “搜索框”类为“搜索栏（带图标）”类；
* 修改 “回弹嵌套滚动视图”类为“阻尼回弹嵌套滚动视图”类；
* 修改 “回弹框架布局”类为“阻尼回弹框架布局”类；
* 修改 kotlin代码为java代码，有“材质底部面板信息对话框”、“搜索栏”、“文本高级列表框”、“阻尼回弹嵌套滚动视图”、“阻尼回弹框架布局”、“基础碎片状态适配器”、“图文文高级列表”、“图片高级列表框”类；
* 优化 “状态栏沉浸式”代码函数；
* 修改 “网页视图”类的基本方法代码位置，列如：“默认配置()”方法，改在“自定义.网络类.网页视图”类中可以使用；

## 0.3.2

* 修复 浏览器配置显示下载对话框的Bug；
* 修复 “吐司”显示的Bug；
* 修改 kotlin代码为java代码，有“网页浏览器客户端”、“网页视图”、“网页视图客户端”、“网页视图碎片”、“网页设置”、“布局解析器”、“纹理视图”、“表面视图”、“视图”、“视图存根”、“视图组”、“提取编辑文本”、“键盘视图”、“圆形进度可绘制对象”、“协调器布局”、“卡片视图”、“回收视图”、“排序列表”、“媒体路由按钮”、“应用兼容对话框”、“应用兼容对话框碎片”、“应用兼容活动”、“警告对话框”、“对话框标题”、“应用兼容下拉框”、“应用兼容切换按钮”、“应用兼容单选按钮”、“应用兼容图像按钮”、“应用兼容图像视图”、“应用兼容已选文本视图”、“应用兼容复选框”、“应用兼容多选自动完成文本视图”、“应用兼容滑动条”、“应用兼容按钮”、“应用兼容文本视图”、“应用兼容自动完成文本视图”、“应用兼容编辑文本”、“应用兼容评分条”、“开关兼容”、“搜索视图”、“操作菜单视图”、“工具栏”、“线性布局兼容”、“警告对话框布局”、“抽屉布局”、“组件活动”、“内容加载进度条”、“嵌套滚动视图”、“保护布局”、“窗口兼容”、“窗口内边距兼容”、“视图兼容”、“组件对话框”、“组件活动”、“报告碎片”、“列表碎片”、“对话框碎片”、“碎片”、“碎片事务”、“碎片容器”、“碎片标签主机”、“碎片活动”、“碎片管理器”、“动画辅助器”、“动画布局”、“动画按钮”、“动画标签”、“动画轨迹线”、“图像过滤器按钮”、“图像过滤器视图”、“模拟视图”、“占位符”、“反应指南”、“指南”、“约束”、“约束布局”、“组”、“虚拟布局”、“障碍”、“动画占位符”、“动画特效”、“圆形流式”、“层”、“流式”、“网格”、“轮播”、“分页标题条”、“分页标签条”、“视图分页器”、“视图分页器2”、“碎片状态适配器”、“定时任务”、“计时器”、“剪贴板工具”、“AndroidBug5497Workaround”、“栏工具”、“主题类”、“动画类”、“颜色类”、“应用类”、“图文图高级列表”、“状态栏沉浸式类”、“状态栏沉浸式”类；
* 修改 “状态栏沉浸式”类的很多方法名，不清楚的查看“状态栏沉浸式”类；

## 0.3.1

* 修改 混淆使用MySQL修改配置；
* 修复 MySQL不能使用的Bug；
* 修改 com.mysql:mysql-connector-j:9.5.4 依赖库为 mysql:mysql-connector-java:5.1.49；
* 添加 androidx.webkit:webkit:1.15.0依赖库；
* 添加 com.github.jenly1314:zxing-lite:3.4.0依赖库；
* 修复 浏览器视频横屏竖屏后状态栏导航栏不对的Bug；
* 修改 kotlin代码为java代码，有“下载监听器”接口类；
* 修改 kotlin代码为java代码，有“电视互动应用视图”、“电视广告视图”、“电视视图”、“列表碎片”、“媒体路由按钮”、“对话框”、“对话框碎片”、“应用程序”、“日期选择对话框”、“时间选择对话框”、“活动”、“碎片”、“碎片容器”、“碎片管理器”、“碎片面包屑”、“警告对话框”、“进度对话框”、“应用组件主机视图”、“图形库表面视图”、“手势覆盖视图”、“倒计时器”、“构建”、“字符选择对话框”、“下拉框”、“切换按钮”、“列表视图”、“单选按钮”、“单选组”、“双行列表项”、“可展开列表视图”、“吐司”、“图像切换器”、“图像按钮”、“图像视图”、“图库”、“堆叠视图”、“已选文本视图”、“复选框”、“多自动完成文本视图”、“媒体控制器”、“开关”、“快速联系人徽章”、“拨号器过滤器”、“按钮”、“搜索视图”、“操作菜单视图”、“数字时钟”、“数字选择器”、“文本切换器”、“文本时钟”、“日历视图”、“日期选择器”、“时间选择器”、“标签主机”、“标签组件”、“工具栏”、“框架布局”、“模拟时钟”、“水平滚动视图”、“滑动抽屉”、“滑动条”、“滚动视图”、“相对布局”、“线性布局”、“绝对布局”、“编辑文本”、“缩放按钮”、“缩放控制器”、“网格布局”、“网格视图”、“自动完成文本视图”、“表格布局”、“表格行”、“视图切换器”、“视图动画器”、“视图翻转器”、“视频视图”、“计时器”、“评分栏”、“进度条”、“适配器视图翻转器”、“空间”、“弹出窗口”类；

## 0.3.0

* 删除 “图文图高级列表适配器”类，添加 “图文图高级列表框”类；
* 添加 “图片高级列表框”类添加长按事件；
* 添加 “图文文高级列表”类组件；
* 修复 “图文图高级列表”类不能显示的Bug；
* 修改 kotlin代码为java代码，有“侧滑面板对话框”、“停靠标题栏布局”、“基线布局”、“材质分割线”、“材质单选按钮”、“材质卡片视图”、“可塑形图像视图”、“圆形揭示协调布局”、“圆形揭示卡片视图”、“圆形揭示框架布局”、“圆形揭示相对布局”、“圆形揭示相对布局”、“圆形揭示线性布局”、“圆形揭示网格布局”、“材质复选框”、“材质警告对话框构建器”、“导航栏菜单视图”、“导航栏视图”、“导航栏项视图”、“导航视图”、“导航轨道视图”、“应用栏布局”、“折叠式标题栏布局”、“材质标题栏”、“底部导航视图”、“底部导航栏”、“底部面板对话框”、“底部面板对话框碎片”、“底部面板拖拽把手视图”、“开关材质”、“展开式悬浮操作按钮”、“悬浮操作按钮”、“悬浮工具栏布局”、“材质分割按钮”、“材质按钮”、“材质按钮切换组”、“材质按钮组”、“搜索栏”、“搜索视图”、“文本输入布局”、“文本输入编辑文本”、“材质自动完成文本视图”、“材质样式日期选择对话框”、“材质开关”、“标签布局”、“标签项”、“滑块”、“范围滑块”、“芯片”、“芯片组”、“转换小卡片”、“转换小布局”、“可遮罩框架布局”、“圆形进度指示器”、“线性进度指示器”、“加载指示器”、“提示栏”、“材质时间选择器”类；
* 删除 用不到的layout文件；
* 修复 “标签布局”不能添加“com.google.android.material.tabs.TabItem”的Bug；

# 老版本更新内容

* [0.2.x系列版本.md](%E6%9B%B4%E6%96%B0%E5%86%85%E5%AE%B9/0.2.x%E7%B3%BB%E5%88%97%E7%89%88%E6%9C%AC.md)
