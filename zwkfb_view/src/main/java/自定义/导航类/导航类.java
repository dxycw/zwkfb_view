package 自定义.导航类;

import android.graphics.Color;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigationrail.NavigationRailView;

public class 导航类 {

    public static void 显示菜单信息个数(NavigationRailView 导航栏视图, int 组件id, boolean 是否可见, int 个数) {
        // 获取或创建徽标（badge）
        BadgeDrawable badge = 导航栏视图.getOrCreateBadge(组件id);
        if (个数 > 0) {
            badge.setNumber(个数);
        }
        badge.setBackgroundColor(Color.parseColor("#FF0000"));
        badge.setBadgeTextColor(Color.parseColor("#FFFFFF"));
        // 设置徽标可见
        badge.setVisible(是否可见);
    }

    public static void 显示菜单信息个数(BottomNavigationView 底部导航视图, int 组件id, boolean 是否可见, int 个数) {
        // 获取或创建徽标（badge）
        BadgeDrawable badge = 底部导航视图.getOrCreateBadge(组件id);
        if (个数 > 0) { badge.setNumber(个数); }
        badge.setBackgroundColor(Color.parseColor("#FF0000"));
        badge.setBadgeTextColor(Color.parseColor("#FFFFFF"));
        // 设置徽标可见
        badge.setVisible(是否可见);
    }


}
