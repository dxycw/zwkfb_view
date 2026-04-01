package 安卓.应用;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：对话框碎片
 * @author dxyc
 */
public class 对话框碎片 extends DialogFragment {

    public static final int 样式_普通 = DialogFragment.STYLE_NORMAL;

    public static final int 样式_不_标题 = DialogFragment.STYLE_NO_TITLE;

    public static final int 样式_不_边框 = DialogFragment.STYLE_NO_FRAME;

    public static final int 样式_不_输入 = DialogFragment.STYLE_NO_INPUT;

    //============================================================================================

    public 对话框碎片() {
        super();
    }

    public void 置样式(int 样式, int 主题) {
        this.setStyle(样式, 主题);
    }

    public void 显示(FragmentManager 管理器, String 标签) {
        this.show(管理器, 标签);
    }

    public int 显示(FragmentTransaction 事务, String 标签) {
        return this.show(事务, 标签);
    }

    public void 关闭() {
        this.dismiss();
    }

    public void 关闭允许状态丢失() {
        this.dismissAllowingStateLoss();
    }


    public Dialog 取对话框() {
        return this.getDialog();
    }

    public int 取主题() {
        return this.getTheme();
    }


    public void 置可取(boolean 可取消) {
        this.setCancelable(可取消);
    }

    public boolean 是否可取消() {
        return this.isCancelable();
    }


    public void 置显示对话框(boolean 显示对话框) {
        this.setShowsDialog(显示对话框);
    }

    public boolean 取显示对话框() {
        return this.getShowsDialog();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onAttach(Context context) {
        this.附加(context);
    }

    public void 附加(Context 上下文) {
        super.onAttach(上下文);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDetach() {
        this.分离();
    }

    public void 分离() {
        super.onDetach();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.创建(savedInstanceState);
    }

    public void 创建(Bundle 已保存的实例状态) {
        super.onCreate(已保存的实例状态);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return this.创建对话框(savedInstanceState);
    }

    public Dialog 创建对话框(Bundle 已保存的实例状态) {
        return super.onCreateDialog(已保存的实例状态);
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        this.取消(dialog);
    }

    public void 取消(DialogInterface 对话框) {
        super.onCancel(对话框);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        this.关闭(dialog);
    }

    public void 关闭(DialogInterface 对话框) {
        super.onDismiss(对话框);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        this.活动已创建(savedInstanceState);
    }

    public void 活动已创建(Bundle 已保存的实例状态) {
        super.onActivityCreated(已保存的实例状态);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {
        this.开始();
    }

    public void 开始() {
        super.onStart();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        this.保存实例状态(outState);
    }

    public void 保存实例状态(Bundle 输出状态) {
        super.onSaveInstanceState(输出状态);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
        this.停止();
    }

    public void 停止() {
        super.onStop();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroyView() {
        this.销毁视图();
    }

    public void 销毁视图() {
        super.onDestroyView();
    }


    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        this.转储(prefix, fd, writer, args);
    }

    public void 转储(String 前缀, FileDescriptor 文件描述, PrintWriter 写入器, String[] 参数) {
        super.dump(前缀, 文件描述, 写入器, 参数);
    }

}
