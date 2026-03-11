package 安卓.应用;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：时间选择对话框
 * @author dxyc
 */
public class 时间选择对话框 extends TimePickerDialog {

    public 时间选择对话框(Context 上下文, OnTimeSetListener 监听器, int 天中小时, int 分钟, boolean 是否24小时视图) {
        super(上下文, 监听器, 天中小时, 分钟, 是否24小时视图);
    }

    public 时间选择对话框(Context 上下文, int 主题资源Id, OnTimeSetListener 监听器, int 天中小时, int 分钟, boolean 是否24小时视图) {
        super(上下文, 主题资源Id, 监听器, 天中小时, 分钟, 是否24小时视图);
    }


}
