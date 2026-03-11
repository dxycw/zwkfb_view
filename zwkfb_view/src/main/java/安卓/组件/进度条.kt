package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar


//===============================================================

/**
 * 版本：0.0.7
 *
 * 进度。
 */
var ProgressBar.进度: Int
    get() = this.progress
    set(进度) { this.progress = 进度 }

/**
 * 版本：0.0.7
 *
 * 获取进度。
 * @return 进度。
 */
fun ProgressBar.取进度(): Int = this.getProgress()

/**
 * 版本：0.0.7
 *
 * 设置进度。
 * @param 进度 进度。
 */
fun ProgressBar.置进度(进度: Int) {
    this.setProgress(进度)
}

/**
 * 版本：0.0.7
 *
 * 设置进度。
 * @param 进度 进度。
 * @param 动画 是否动画。
 */
fun ProgressBar.置进度(进度: Int, 动画: Boolean) {
    this.setProgress(进度, 动画)
}

//===============================================================
