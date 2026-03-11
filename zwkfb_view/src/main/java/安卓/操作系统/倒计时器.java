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


}
