package 自定义.对话框类;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;

import zwkfb.view.R;
import 商业.谷歌.安卓.材质.底部面板.底部面板对话框碎片;
import 自定义.状态栏类.状态栏沉浸式;
import 自定义.状态栏类.状态栏沉浸式类;
import 自定义.系统类.屏幕类;
import 自定义.系统类.系统类;

public final class 材质底部面板信息对话框 extends 底部面板对话框碎片 {

    public static final int 可视 = View.VISIBLE; // 可视
    public static final int 不可视 = View.INVISIBLE; // 不可视
    public static final int 不可视隐藏 = View.GONE; // 不可视隐藏

    //==============================================================================================

    public static final int 横向 = 0; // 横向
    public static final int 纵向 = 1; // 纵向

    //==============================================================================================

    private View 根布局;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);// 保留实例
        根布局 = inflater.inflate(R.layout.dialog_czdbxxdhk, container, false);
        return 根布局;
    }

    //==============================================================================================

    private CardView 滑动条;
    private TextView 标题栏;
    private AppCompatTextView 内容栏;

    //==============================================================================================

    private LinearLayout 横底部按钮框;
    private MaterialButton 横底部忽略按钮;
    private MaterialButton 横底部取消按钮;
    private MaterialButton 横底部确定按钮;

    //==============================================================================================

    private LinearLayout 竖底部按钮框;
    private MaterialButton 竖底部忽略按钮;
    private MaterialButton 竖底部取消按钮;
    private MaterialButton 竖底部确定按钮;

    //==============================================================================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化所有视图引用
        滑动条 = 根布局.findViewById(R.id.hdt);
        标题栏 = 根布局.findViewById(R.id.btl);
        内容栏 = 根布局.findViewById(R.id.nr);

        // 横向按钮布局
        横底部按钮框 = 根布局.findViewById(R.id.hank);
        横底部忽略按钮 = 根布局.findViewById(R.id.h_hlan);
        横底部取消按钮 = 根布局.findViewById(R.id.h_qxan);
        横底部确定按钮 = 根布局.findViewById(R.id.h_qdan);

        // 纵向按钮布局
        竖底部按钮框 = 根布局.findViewById(R.id.sank);
        竖底部忽略按钮 = 根布局.findViewById(R.id.s_hlan);
        竖底部取消按钮 = 根布局.findViewById(R.id.s_qxan);
        竖底部确定按钮 = 根布局.findViewById(R.id.s_qdan);

        // 更新UI状态（每次都要执行）
        更新界面状态();

        设置按钮点击事件();

        // 处理状态栏沉浸式
        处理状态栏沉浸式();

    }

    //==============================================================================================

    private boolean 是否隐藏状态栏导航栏 = false;

    private void 处理状态栏沉浸式() {
        if (getDialog() != null) {
            if (是否隐藏状态栏导航栏) {
                状态栏沉浸式类.隐藏状态栏(requireActivity(), getDialog().getWindow());
            } else {
                状态栏沉浸式类.显示状态栏导航栏(requireActivity(), getDialog().getWindow());
            }
        }
    }

    //==============================================================================================

    private boolean 是否显示对话框 = false;

    @Override
    public void onDestroy() {
        super.onDestroy();
        是否显示对话框 = false;
    }

    //==============================================================================================

    private int 滑动条可视 = 材质底部面板信息对话框.可视;
    private int 滑动条宽度 = 50;
    private int 滑动条高度 = 3;
    private int 滑动条背景颜色 = Color.parseColor("#A9A9A9");

    private CharSequence 标题 = null;
    private CharSequence 内容 = null;
    private boolean 内容可选 = false;

    private CharSequence 忽略按钮文本 = null;
    private 单击事件 忽略按钮单击事件 = null;

    private CharSequence 取消按钮文本 = null;
    private 单击事件 取消按钮单击事件 = null;

    private CharSequence 确定按钮文本 = null;
    private 单击事件 确定按钮单击事件 = null;

    //==============================================================================================

    private void 更新界面状态() {
        滑动条.setVisibility(滑动条可视);
        滑动条.getLayoutParams().width = 屏幕类.dp转px(requireActivity(),滑动条宽度);
        滑动条.getLayoutParams().height = 屏幕类.dp转px(requireActivity(),滑动条高度);
        滑动条.setCardBackgroundColor(滑动条背景颜色);

        标题栏.setText(标题);
        标题栏.setVisibility( (标题 == null) ? 不可视隐藏 : 可视);

        内容栏.setText(内容);
        内容栏.setVisibility( (内容 == null) ? 不可视隐藏 : 可视);
        内容栏.setTextIsSelectable(内容可选);

        // 忽略按钮
        横底部忽略按钮.setText(忽略按钮文本);
        横底部忽略按钮.setVisibility( (忽略按钮文本 == null) ? 不可视隐藏 : 可视);

        竖底部忽略按钮.setText(忽略按钮文本);
        竖底部忽略按钮.setVisibility( (忽略按钮文本 == null) ? 不可视隐藏 : 可视);

        // 取消按钮
        横底部取消按钮.setText(取消按钮文本);
        横底部取消按钮.setVisibility( (取消按钮文本 == null) ? 不可视隐藏 : 可视);

        竖底部取消按钮.setText(取消按钮文本);
        竖底部取消按钮.setVisibility( (取消按钮文本 == null) ? 不可视隐藏 : 可视);

        // 确定按钮
        横底部确定按钮.setText(确定按钮文本);
        横底部确定按钮.setVisibility( (确定按钮文本 == null) ? 不可视隐藏 : 可视);

        竖底部确定按钮.setText(确定按钮文本);
        竖底部确定按钮.setVisibility( (确定按钮文本 == null) ? 不可视隐藏 : 可视);

        // 按钮方向布局
        if (按钮方向 == 横向) {
            横底部按钮框.setVisibility(可视);
            竖底部按钮框.setVisibility(不可视隐藏);
        } else {
            横底部按钮框.setVisibility(不可视隐藏);
            竖底部按钮框.setVisibility(可视);
        }
    }

    //==============================================================================================

    private void 设置按钮点击事件() {
        横底部忽略按钮.setOnClickListener(v ->
                忽略按钮单击事件.单击事件(requireDialog())
        );

        横底部取消按钮.setOnClickListener(v ->
            取消按钮单击事件.单击事件(requireDialog())
        );

        横底部确定按钮.setOnClickListener (v ->
            确定按钮单击事件.单击事件(requireDialog())
        );

        竖底部忽略按钮.setOnClickListener(v ->
            忽略按钮单击事件.单击事件(requireDialog())
        );

        竖底部取消按钮.setOnClickListener(v ->
            取消按钮单击事件.单击事件(requireDialog())
        );

        竖底部确定按钮.setOnClickListener(v ->
            确定按钮单击事件.单击事件(requireDialog())
        );
    }

    //==============================================================================================

    @Override
    public void onResume() {
        super.onResume();
        配置底部面板行为();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 屏幕旋转时更新界面
        更新界面状态();
        处理状态栏沉浸式();
        配置底部面板行为();
    }

    //==============================================================================================

    public boolean 是否显示对话框()  {
        return 是否显示对话框;
    }

    //==============================================================================================

    public 材质底部面板信息对话框 隐藏状态栏导航栏() {
        是否隐藏状态栏导航栏 = true;
        return this;
    }

    public 材质底部面板信息对话框 显示状态栏导航栏() {
        是否隐藏状态栏导航栏 = false;
        return this;
    }

    //==============================================================================================

    public 材质底部面板信息对话框 置滑动条可视(int 可视) {
        this.滑动条可视 = 可视;
        return this;
    }

    //==============================================================================================
    //==============================================================================================

    public 材质底部面板信息对话框 置滑动条宽度(int 宽度) {
        this.滑动条宽度 = 宽度;
        return this;
    }

    public 材质底部面板信息对话框 置滑动条高度(int 高度) {
        this.滑动条高度 = 高度;
        return this;
    }

    public 材质底部面板信息对话框 置滑动条宽高(int 宽度, int 高度) {
        this.滑动条宽度 = 宽度;
        this.滑动条高度 = 高度;
        return this;
    }

    //==============================================================================================
    //==============================================================================================

    public 材质底部面板信息对话框 置滑动条背景颜色(int 颜色) {
        this.滑动条背景颜色 = 颜色;
        return this;
    }

    public 材质底部面板信息对话框 置滑动条背景颜色(String 颜色) {
        this.滑动条背景颜色 = Color.parseColor(颜色);
        return this;
    }

    //==============================================================================================
    //==============================================================================================

    public 材质底部面板信息对话框 置标题(String 标题) {
        this.标题 = 标题;
        return this;
    }

    //==============================================================================================
    //==============================================================================================

    public 材质底部面板信息对话框 置内容(String 内容) {
        this.内容 = 内容;
        return this;
    }

    //======================================================================

    public 材质底部面板信息对话框 置内容可选(boolean 可选) {
        this.内容可选 = 可选;
        return this;
    }

    //======================================================================

    private int 按钮方向 = 0;
    public 材质底部面板信息对话框 置按钮方向(int 方向) {
        this.按钮方向 = 方向;
        return this;
    }

    //======================================================================

    public 材质底部面板信息对话框 置忽略按钮单击事件(CharSequence 文本, 材质底部面板信息对话框.单击事件 事件) {
        this.忽略按钮文本 = 文本;
        this.忽略按钮单击事件 = 事件;
        return this;
    }


    //======================================================================

    public 材质底部面板信息对话框 置取消按钮单击事件(CharSequence 文本, 材质底部面板信息对话框.单击事件 事件) {
        this.取消按钮文本 = 文本;
        this.取消按钮单击事件 = 事件;
        return this;
    }

    //======================================================================

    public 材质底部面板信息对话框 置确定按钮单击事件(CharSequence 文本, 材质底部面板信息对话框.单击事件 事件) {
        this.确定按钮文本 = 文本;
        this.确定按钮单击事件 = 事件;
        return this;
    }

    //======================================================================

    public interface 单击事件 {
        void 单击事件(Dialog 对话框);
    }

    //======================================================================

    public void 显示(FragmentManager 管理器, String 标记) {
        是否显示对话框 = true;
        this.show(管理器, 标记);
    }

    public int 显示(FragmentTransaction 事务, String 标记) {
        是否显示对话框 = true;
        return this.show(事务, 标记);
    }

    //======================================================================

    private BottomSheetBehavior<?> 底部面板行为 = null;
    private View 底部面板视图 = null;

    //======================================================================

    private boolean 默认配置 = false;
    private int 布局最大高度 = -2;
    private int 布局折叠高度 = 500;
    private int 展示方式 = BottomSheetBehavior.STATE_EXPANDED;
    private boolean 可拖动 = true;

    //======================================================================

    public 材质底部面板信息对话框 默认配置(boolean 值) {
        this.默认配置 = 值;
        return this;
    }

    public 材质底部面板信息对话框 布局最大高度(int 高度) {
        this.布局最大高度 = 高度;
        return this;
    }

    public 材质底部面板信息对话框 布局折叠高度(int 高度) {
        this.布局折叠高度 = 高度;
        return this;
    }

    public 材质底部面板信息对话框 展示方式(int 展示方式) {
        this.展示方式 = 展示方式;
        return this;
    }

    //==============================================================================================

    public 材质底部面板信息对话框 可拖动(boolean 值) {
        this.可拖动 = 值;
        return this;
    }

    //==============================================================================================

    private void 配置底部面板行为() {
        // 获取 BottomSheet 的 FrameLayout
        if (getDialog() != null) {
            底部面板视图 = getDialog().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        }
        底部面板行为 = BottomSheetBehavior.from(底部面板视图);

        if (默认配置) {
            int 屏幕高度 = 屏幕类.取用户屏幕高度(requireActivity());
            int 状态栏高度 = 状态栏沉浸式.取状态栏高度(requireActivity());
            int 导航栏高度 = 状态栏沉浸式.取导航栏高度(requireActivity());
            int 默认边距 = 10;

            if (系统类.是否为手机(requireActivity()) || 状态栏沉浸式.是否手势(requireActivity())){
                if (系统类.是否处于横屏(requireActivity())){
                    底部面板行为.setMaxHeight(屏幕高度 - 状态栏高度 - 默认边距);
                }else {
                    底部面板行为.setMaxHeight(屏幕高度 - 状态栏高度 - 导航栏高度 - 默认边距);
                }
            }else {
                底部面板行为.setMaxHeight(-1);
            }

            底部面板行为.setDraggable(true); // 是否可拖动
            底部面板行为.setPeekHeight(屏幕高度 / 2); // 折叠高度
            底部面板行为.setSkipCollapsed(false); // 是否折叠
        } else {
            底部面板行为.setMaxHeight(布局最大高度); // 最大高度
            底部面板行为.setState(展示方式); // 展示方式
            底部面板行为.setDraggable(可拖动); // 是否可拖动
            底部面板行为.setPeekHeight(布局折叠高度); // 折叠高度
            底部面板行为.setSkipCollapsed(false); // 是否折叠
        }
    }

    //==============================================================================================

}
