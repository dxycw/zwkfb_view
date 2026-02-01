package 安卓.内容

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：意图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 意图 :Intent{
    constructor() : super()
    constructor(packageContext: Context?, cls: Class<*>?) : super(packageContext, cls)
    constructor(o: Intent?) : super(o)
    constructor(action: String?) : super(action)
    constructor(action: String?, uri: Uri?) : super(action, uri)
    constructor(
        action: String?,
        uri: Uri?,
        packageContext: Context?,
        cls: Class<*>?,
    ) : super(action, uri, packageContext, cls)
}