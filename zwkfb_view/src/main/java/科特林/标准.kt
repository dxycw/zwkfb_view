package 科特林

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * 创建时间：2025年11月26日.
 *
 * 版本：0.1.0
 * @author dxyc
 */



/**
 * 描述：应用函数.
 * @param 块 应用函数体.
 * @return 应用函数的结果.
 */
inline fun <T> T.应用(块: T.() -> Unit): T = this.apply(块)

/**
 * 描述：也函数.
 * @param 块 也函数体.
 * @return 也函数的结果.
 */
inline fun <T> T.也(块: (T) -> Unit): T = this.also(块)

/**
 * 描述：让函数.
 * @param 块 让函数体.
 * @return 让函数的结果.
 */
inline fun <T, R> T.让(块: (T) -> R): R = this.let(块)

