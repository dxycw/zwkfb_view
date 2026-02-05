package 科特林


/**
 * 抛出异常以表明方法体尚未实现。
 */
open class 未实现错误(信息: String = "操作未实现。") : Error(信息)

/**
 * 始终抛出 [未实现错误]，表明该操作尚未实现。
 */
inline fun 待办事项(): Nothing = throw 未实现错误()

/**
 * 始终抛出 [未实现错误]，表明该操作尚未实现。
 *
 * @param 原因 一个字符串，用于解释为何缺少实现。
 */

inline fun 待办事项(原因: String): Nothing = throw 未实现错误("操作未实现: $原因")






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

