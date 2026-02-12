package 商业.谷歌.安卓.材质.导航

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.navigation.NavigationBarMenuView
import com.google.android.material.navigation.NavigationBarView

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：导航栏视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
@SuppressLint("ViewConstructor")
open class 导航栏视图 : NavigationBarView {
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun getMaxItemCount(): Int {
        TODO("Not yet implemented")
    }

    @SuppressLint("RestrictedApi")
    override fun createNavigationBarMenuView(context: Context): NavigationBarMenuView {
        TODO("Not yet implemented")
    }


}