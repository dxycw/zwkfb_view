package 安卓.os

import android.os.CountDownTimer

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：倒计时器
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 倒计时器 :CountDownTimer{
    constructor(millisInFuture: Long, countDownInterval: Long) : super(
        millisInFuture,
        countDownInterval
    )

    override fun onFinish() {
        TODO("Not yet implemented")
    }

    override fun onTick(millisUntilFinished: Long) {
        TODO("Not yet implemented")
    }
}

//================================================================================

//fun CountDownTimer.启动() { start() }
//
//fun CountDownTimer.取消() { cancel() }