package 自定义.bar

/**
 * 软键盘监听
 * Created by geyifeng on 2017/8/28.
 */
interface OnKeyboardListener {
    /**
     * On keyboard change.
     *
     * @param isPopup        the is popup  是否弹出
     * @param keyboardHeight the keyboard height  软键盘高度
     */
    fun onKeyboardChange(isPopup: Boolean, keyboardHeight: Int)
}
