package 安卓.文本.方法;

import android.content.Context;
import android.text.Editable;
import android.text.method.CharacterPickerDialog;
import android.view.View;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：字符选择对话框
 * @author dxyc
 */
public class 字符选择对话框 extends CharacterPickerDialog {

    public 字符选择对话框(Context 上下文, View 视图, Editable 文本, String 选项, boolean 插入) {
        super(上下文, 视图, 文本, 选项, 插入);
    }


}
