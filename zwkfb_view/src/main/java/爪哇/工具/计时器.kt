package 爪哇.工具

import java.util.Timer

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：计时器
 *
 * 版本：0.1.1
 * @author dxyc
 */
open class 计时器 : Timer {
    constructor() : super()
    constructor(isDaemon: Boolean) : super(isDaemon)
    constructor(name: String?) : super(name)
    constructor(name: String?, isDaemon: Boolean) : super(name, isDaemon)
}