package 商业.谷歌.安卓.材质.时间选择器

internal interface 时间选择器呈现器 {
    /** Do any final initialization  */
    fun initialize()

    /** Refresh the data in the view based on the model  */
    fun invalidate()

    fun hide()

    fun show()
}
