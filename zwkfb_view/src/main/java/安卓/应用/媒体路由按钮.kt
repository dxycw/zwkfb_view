package 安卓.应用

import android.app.MediaRouteButton
import android.view.View


//============================================================================================

fun MediaRouteButton.取路由类型(): Int {
    return this.getRouteTypes()
}

fun MediaRouteButton.置路由类型(类型: Int) {
    this.setRouteTypes(类型)
}

fun MediaRouteButton.置扩展设置单击监听器(监听器: View.OnClickListener?) {
    this.setExtendedSettingsClickListener(监听器)
}

fun MediaRouteButton.显示对话框() {
    this.showDialog()
}

fun MediaRouteButton.置内容描述(内容描述: CharSequence?) {
    this.setContentDescription(内容描述)
}

fun MediaRouteButton.执行单击(): Boolean {
    return this.performClick()
}


fun MediaRouteButton.跳转到当前状态的可绘制对象() {
    this.jumpDrawablesToCurrentState()
}


fun MediaRouteButton.置可见性(可见性: Int) {
    this.setVisibility(可见性)
}

fun MediaRouteButton.附加到窗口() {
    this.onAttachedToWindow()
}


fun MediaRouteButton.分离从窗口() {
    this.onDetachedFromWindow()
}
