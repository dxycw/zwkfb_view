package 安卓.应用

import android.app.Application
import android.content.res.Configuration

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：应用程序
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 应用程序 : Application{
    constructor() : super()

    /**
     * onCreate
     */
    override fun onCreate() {
        创建回调()
    }

    /**
     * 创建回调
     */
    open fun 创建回调(){
        super.onCreate()
    }

}