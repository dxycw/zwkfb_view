package 安卓x.应用兼容.应用;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.ArrayRes;
import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;

import 安卓.内容.对话框接口;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：警告对话框
 * @author dxyc
 */
public class 警告对话框 extends AlertDialog implements 对话框接口 {

    protected 警告对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    protected 警告对话框(@NonNull Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }

    protected 警告对话框(@NonNull Context 上下文, boolean 可取消, @Nullable OnCancelListener 取消监听器) {
        super(上下文, 可取消, 取消监听器);
    }

    //==============================================================================================

    /**
     * 描述：取按钮
     */
    public Button 取按钮(int 哪个按钮) {
        return this.getButton(哪个按钮);
    }

    /**
     * 描述：取列表视图
     */
    public ListView 取列表视图() {
        return this.getListView();
    }

    /**
     * 描述：设置警告对话框标题
     */
    public void 置标题(CharSequence 标题) {
        this.setTitle(标题);
    }

    /**
     * 描述：置自定义标题
     */
    public void 置自定义标题(View 自定义标题视图) {
        this.setCustomTitle(自定义标题视图);
    }

    /**
     * 描述：置消息
     */
    public void 置消息(CharSequence 消息) {
        this.setMessage(消息);
    }

    /**
     * 描述：置视图
     */
    public void 置视图(View 视图) {
        this.setView(视图);
    }

    /**
     * 描述：置视图
     */
    public void 置视图(View 视图, int 视图间距左, int 视图间距上, int 视图间距右, int 视图间距下) {
        this.setView(视图, 视图间距左, 视图间距上, 视图间距右, 视图间距下);
    }

    /**
     * 描述：置按钮
     */
    public void 置按钮(int 哪个按钮, CharSequence 文本, Message 信息) {
        this.setButton(哪个按钮, 文本, 信息);
    }

    /**
     * 描述：置按钮
     */
    public void 置按钮(int 哪个按钮, CharSequence 文本, OnClickListener 监听器) {
        this.setButton(哪个按钮, 文本, 监听器);
    }

    /**
     * 描述：置按钮
     */
    public void 置按钮(int 哪个按钮, CharSequence 文本, Drawable 图标, OnClickListener 监听器) {
        this.setButton(哪个按钮, 文本, 图标, 监听器);
    }

    /**
     * 描述：置图标
     */
    public void 置图标(int 资源Id) {
        this.setIcon(资源Id);
    }

    /**
     * 描述：置图标
     */
    public void 置图标(Drawable 图标) {
        this.setIcon(图标);
    }

    /**
     * 描述：置图标属性
     */
    public void 置图标属性(int 属性Id) {
        this.setIconAttribute(属性Id);
    }


//    /**
//     * 描述：创建回调
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        this.创建回调(savedInstanceState);
//    }
//
//    /**
//     * 描述：按键按下回调
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return this.按键按下回调(keyCode, event);
//    }
//
//    /**
//     * 描述：按键抬起回调
//     * @hidden
//     */
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        return this.按键抬起回调(keyCode, event);
//    }

    /**
     * 描述：创建回调
     */
    protected void 创建回调(Bundle 已保存实例状态) {
        this.onCreate(已保存实例状态);
    }

    /**
     * 描述：按键按下回调
     */
    public boolean 按键按下回调(int 键码, KeyEvent 事件) {
        return this.onKeyDown(键码, 事件);
    }

    /**
     * 描述：按键抬起回调
     */
    public boolean 按键抬起回调(int 键码, KeyEvent 事件) {
        return this.onKeyUp(键码, 事件);
    }

    //==============================================================================================

    /**
     * 描述：构建器
     */
    public static class 构建器 extends AlertDialog.Builder{

        public 构建器(@NonNull Context 上下文) {
            super(上下文);
        }

        public 构建器(@NonNull Context 上下文, int 主题资源Id) {
            super(上下文, 主题资源Id);
        }


        public Context 取上下文() {
            return this.getContext();
        }

        public 构建器 置标题(@StringRes int 标题Id) {
            this.setTitle(标题Id);
            return this;
        }


        public 构建器 置标题(@Nullable CharSequence 标题) {
            this.setTitle(标题);
            return this;
        }

        public 构建器 置自定义标题(@Nullable View 自定义标题视图) {
            this.setCustomTitle(自定义标题视图);
            return this;
        }

        public 构建器 置消息(@StringRes int 消息Id) {
            this.setMessage(消息Id);
            return this;
        }


        public 构建器 置消息(@Nullable CharSequence 消息) {
            this.setMessage(消息);
            return this;
        }

        public 构建器 置图标(@DrawableRes int 图标Id) {
            this.setIcon(图标Id);
            return this;
        }


        public 构建器 置图标(@Nullable Drawable 图标) {
            this.setIcon(图标);
            return this;
        }

        public 构建器 置图标属性(@AttrRes int 属性Id) {
            this.setIconAttribute(属性Id);
            return this;
        }


        public 构建器 置确定按钮(@StringRes int 文本Id, final OnClickListener 监听器) {
            this.setPositiveButton(文本Id, 监听器);
            return this;
        }

        public 构建器 置确定按钮(CharSequence 文本, final OnClickListener 监听器) {
            this.setPositiveButton(文本, 监听器);
            return this;
        }


        public 构建器 置确定按钮图标(Drawable 图标) {
            this.setPositiveButtonIcon(图标);
            return this;
        }


        public 构建器 置取消按钮(@StringRes int 文本Id, final OnClickListener 监听器) {
            this.setNegativeButton(文本Id, 监听器);
            return this;
        }

        public 构建器 置取消按钮(CharSequence 文本, final OnClickListener 监听器) {
            this.setNegativeButton(文本, 监听器);
            return this;
        }


        public 构建器 置取消按钮图标(Drawable 图标) {
            this.setNegativeButtonIcon(图标);
            return this;
        }


        public 构建器 置忽略按钮(@StringRes int 文本Id, final OnClickListener 监听器) {
            this.setNegativeButton(文本Id, 监听器);
            return this;
        }

        public 构建器 置忽略按钮(CharSequence 文本, final OnClickListener 监听器) {
            this.setNegativeButton(文本, 监听器);
            return this;
        }


        public 构建器 置忽略按钮图标(Drawable 图标) {
            this.setNeutralButtonIcon(图标);
            return this;
        }


        public 构建器 置可取消(boolean 可取消) {
            this.setCancelable(可取消);
            return this;
        }

        public 构建器 置取消监听器(OnCancelListener 取消监听器) {
            this.setOnCancelListener(取消监听器);
            return this;
        }

        public 构建器 置关闭监听器(OnDismissListener 关闭监听器) {
            this.setOnDismissListener(关闭监听器);
            return this;
        }

        public 构建器 置按键监听器(OnKeyListener 按键监听器) {
            this.setOnKeyListener(按键监听器);
            return this;
        }


        public 构建器 置项目(@ArrayRes int 项目Id, final OnClickListener 监听器) {
            this.setItems(项目Id, 监听器);
            return this;
        }

        public 构建器 置项目(CharSequence[] 项目, final OnClickListener 监听器) {
            this.setItems(项目, 监听器);
            return this;
        }


        public 构建器 置适配器(final ListAdapter 适配器, final OnClickListener 监听器) {
            this.setAdapter(适配器, 监听器);
            return this;
        }


        public 构建器 置光标(final Cursor 光标, final OnClickListener 监听器, String 标签列) {
            this.setCursor(光标, 监听器, 标签列);
            return this;
        }



        public 构建器 置多选项目(@ArrayRes int 项目Id, boolean[] 已选项目, final OnMultiChoiceClickListener 监听器) {
            this.setMultiChoiceItems(项目Id, 已选项目, 监听器);
            return this;
        }

        public 构建器 置多选项目(CharSequence[] 项目, boolean[] 已选项目, final OnMultiChoiceClickListener 监听器) {
            this.setMultiChoiceItems(项目, 已选项目, 监听器);
            return this;
        }

        public 构建器 置多选项目(Cursor 光标, String 是否已选中列, String 标签列, final OnMultiChoiceClickListener 监听器) {
            this.setMultiChoiceItems(光标, 是否已选中列, 标签列, 监听器);
            return this;
        }




        public 构建器 置单选项目(@ArrayRes int 项目Id, int 已选项目, final OnClickListener 监听器) {
            this.setSingleChoiceItems(项目Id, 已选项目, 监听器);
            return this;
        }

        public 构建器 置单选项目(Cursor 光标, int 已选项目, String 标签列, final OnClickListener 监听器) {
            this.setSingleChoiceItems(光标, 已选项目, 标签列, 监听器);
            return this;
        }

        public 构建器 置单选项目(CharSequence[] 项目, int 已选项目, final OnClickListener 监听器) {
            this.setSingleChoiceItems(项目, 已选项目, 监听器);
            return this;
        }

        public 构建器 置单选项目(ListAdapter 适配器, int 已选项目, final OnClickListener 监听器) {
            this.setSingleChoiceItems(适配器, 已选项目, 监听器);
            return this;
        }




        public 构建器 置项目选中监听器(final AdapterView.OnItemSelectedListener 监听器) {
            this.setOnItemSelectedListener(监听器);
            return this;
        }



        public 构建器 置视图(int 布局资源Id) {
            this.setView(布局资源Id);
            return this;
        }

        public 构建器 置视图(View 视图) {
            this.setView(视图);
            return this;
        }

        @SuppressLint("RestrictedApi")
        public 构建器 置视图(View 视图, int 视图间距左, int 视图间距上, int 视图间距右, int 视图间距下) {
            this.setView(视图, 视图间距左, 视图间距上, 视图间距右, 视图间距下);
            return this;
        }




        public 构建器 置强制反向背景(boolean 使用反向背景)  {
            this.setInverseBackgroundForced(使用反向背景);
            return this;
        }

        @SuppressLint("RestrictedApi")
        public 构建器 置测量时回收启用(boolean 启用) {
            this.setRecycleOnMeasureEnabled(启用);
            return this;
        }



        public 警告对话框 创建() {
            this.create();
            return new 警告对话框(取上下文());
        }

        /**
         * 使用此构建器提供的参数创建一个 `警告对话框`，并立即显示该对话框。
         * <p>
         * 调用此方法在功能上等同于：
         * <pre>
         *  警告对话框 对话框 = 构建器.创建();
         *  对话框.显示();
         * </pre>
         */
        public 警告对话框 显示() {
            this.show();
            return new 警告对话框(取上下文());
        }

    }

}
