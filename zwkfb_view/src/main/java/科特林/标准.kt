package 科特林


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




