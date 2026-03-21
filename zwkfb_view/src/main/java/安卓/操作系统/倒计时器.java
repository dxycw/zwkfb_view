package 安卓.操作系统;

import android.os.CountDownTimer;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：倒计时器
 * @author dxyc
 */
public abstract class 倒计时器 extends CountDownTimer {

    public 倒计时器(long 未来的毫秒, long 倒计时间隔) {
        super(未来的毫秒, 倒计时间隔);
    }


    public synchronized final void 取消() {
        this.cancel();
    }

    public synchronized final CountDownTimer 启动() {
        return this.start();
    }

    /**
     * 回调按固定间隔触发。
     * @param millisUntilFinished 直到完成所需的时间
     */
    @Override
    public void onTick(long millisUntilFinished) {
        计时回调(millisUntilFinished);
    }

    /**
     * 时间到时触发回调。
     */
    @Override
    public void onFinish() {
        完成回调();
    }

    public abstract void 计时回调(long 剩余毫秒);

    public abstract void 完成回调();

}
