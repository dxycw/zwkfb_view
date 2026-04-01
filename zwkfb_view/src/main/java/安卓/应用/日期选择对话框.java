package 安卓.应用;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：日期选择对话框
 * @author dxyc
 */
public class 日期选择对话框 extends DatePickerDialog {

    public 日期选择对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 日期选择对话框(@NonNull Context 上下文, @Nullable OnDateSetListener 监听器, int 年, int 月, int 月中天) {
        super(上下文, 监听器, 年, 月, 月中天);
    }

    public 日期选择对话框(@NonNull Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }

    public 日期选择对话框(@NonNull Context 上下文, int 主题资源Id, @Nullable OnDateSetListener 监听器, int 年, int 年中月, int 月中天) {
        super(上下文, 主题资源Id, 监听器, 年, 年中月, 月中天);
    }

    //=========================================================================================


    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
        this.数据改变(view, year, month, dayOfMonth);
    }

    public void 数据改变(@NonNull DatePicker 视图, int 年, int 月, int 月中天) {
        super.onDateChanged(视图, 年, 月, 月中天);
    }

    public void 置日期选择监听器(@Nullable OnDateSetListener 监听器) {
        this.setOnDateSetListener(监听器);
    }

    @Override
    public void onClick(@NonNull DialogInterface dialog, int which) {
        this.单击(dialog, which);
    }

    public void 单击(@NonNull DialogInterface 对话框, int 哪个) {
        super.onClick(对话框, 哪个);
    }


    public DatePicker 取日期选择器() {
        return this.getDatePicker();
    }

    public void 更新日期(int 年, int 月, int 月中天) {
        this.updateDate(年, 月, 月中天);
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

    //===========================================================================================

    public interface 日期置监听器 extends OnDateSetListener {
        default void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
            日期置(view, year, month, dayOfMonth);
        }
        void 日期置(DatePicker 视图, int 年, int 月, int 月中天);
    }

}
