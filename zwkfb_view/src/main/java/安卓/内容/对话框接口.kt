package 安卓.内容

import android.content.DialogInterface
import android.view.KeyEvent


/**
 * 创建时间：2025年12月4日.
 *
 * 描述：对话框接口
 *
 * 版本：0.1.6
 * @author dxyc
 */
interface 对话框接口{

    companion object{
        val 按钮_确定: Int
            get() = DialogInterface.BUTTON_POSITIVE

        val 按钮_取消: Int
            get() = DialogInterface.BUTTON_NEGATIVE

        val 按钮_忽略: Int
            get() = DialogInterface.BUTTON_NEUTRAL

        val 按钮1: Int
            get() = DialogInterface.BUTTON1

        val 按钮2: Int
            get() = DialogInterface.BUTTON2

        val 按钮3: Int
            get() = DialogInterface.BUTTON3
    }

    abstract fun 取消()

    abstract fun 关闭()

    interface 取消监听器回调  {
        abstract fun 取消回调(对话框: DialogInterface)
    }

    interface 关闭监听器回调  {
        abstract fun 关闭回调(对话框: DialogInterface)
    }

    interface 显示监听器回调 {
        abstract fun 显示回调(对话框: DialogInterface)
    }

    interface 单击监听器回调 {
        abstract fun 单击回调(对话框: DialogInterface,哪一个: Int)
    }

    interface 多选单击监听器回调 {
        abstract fun 单击回调(对话框: DialogInterface,哪一个: Int, 是否选中: Boolean)
    }

    interface 按键监听器回调 {
        abstract fun 按键回调(对话框: DialogInterface,键码: Int, 事件: KeyEvent)
    }

}



