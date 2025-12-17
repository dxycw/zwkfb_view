package 自定义.导航类

import androidx.core.graphics.toColorInt
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.显示菜单信息个数(组件id: Int, 是否可见: Boolean, 个数: Int) {
    // 获取或创建徽标（badge）
    val badge = getOrCreateBadge(组件id)
    if (个数 > 0) { badge.setNumber(个数) }
    badge.setBackgroundColor("#FF0000".toColorInt())
    badge.badgeTextColor = "#FFFFFF".toColorInt()
    // 设置徽标可见
    badge.setVisible(是否可见)
}