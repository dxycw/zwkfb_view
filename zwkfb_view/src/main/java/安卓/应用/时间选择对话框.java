package 安卓.应用;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

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

    //=========================================================================================


    public interface 时间置监听器 extends OnTimeSetListener {
        /**
         * Called when the user is done setting a new time and the dialog has
         * closed.
         *
         * @param view the view associated with this listener
         * @param hourOfDay the hour that was set
         * @param minute the minute that was set
         */
        default void onTimeSet(TimePicker view, int hourOfDay, int minute){
            时间置(view, hourOfDay, minute);
        }

        void 时间置(TimePicker 视图, int 天中小时, int 分钟);
    }


    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        super.onTimeChanged(view, hourOfDay, minute);
        this.时间已更改(view, hourOfDay, minute);
    }

    public void 时间已更改(TimePicker 视图, int 天中小时, int 分钟){}

    @Override
    public void show() {
        super.show();
        this.显示();
    }

    public void 显示() {}


    @Override
    public void onClick(@NonNull DialogInterface dialog, int which) {
        this.单击(dialog, which);
    }

    public void 单击(@NonNull DialogInterface 对话框, int 哪个) {
        super.onClick(对话框, 哪个);
    }

    public void 更新时间(int 天中小时, int 小时中分钟) {
        this.updateTime(天中小时, 小时中分钟);
    }


    @NonNull
    @Override
    public Bundle onSaveInstanceState() {
        return this.保存实例状态();
    }

    @NonNull
    public Bundle 保存实例状态() {
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        this.恢复实例状态(savedInstanceState);
    }

    public void 恢复实例状态(Bundle 已保存实例状态) {
        super.onRestoreInstanceState(已保存实例状态);
    }

}
