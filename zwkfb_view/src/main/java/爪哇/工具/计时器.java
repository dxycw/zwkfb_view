package 爪哇.工具;

import java.util.Timer;

/**
 * 创建时间：2025年11月26日.
 * <p>
 * 描述：计时器
 * @author dxyc
 */
public class 计时器 extends Timer {

    public 计时器() {
        super();
    }

    public 计时器(boolean 是否守护进程) {
        super(是否守护进程);
    }

    public 计时器(String 名) {
        super(名);
    }

    public 计时器(String 名, boolean 是否守护进程) {
        super(名, 是否守护进程);
    }

}
