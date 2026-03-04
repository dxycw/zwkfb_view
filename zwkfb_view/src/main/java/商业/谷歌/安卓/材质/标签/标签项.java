package 商业.谷歌.安卓.材质.标签;

import com.google.android.material.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.TintTypedArray;

/**
 * 创建时间：2025年11月24日.
 * <p>
 * 描述：标签项
 * @author dxyc
 */
//public class 标签项 extends TabItem {
//
//    public 标签项(Context 上下文) {
//        super(上下文);
//    }
//
//    public 标签项(Context 上下文, AttributeSet 属性) {
//        super(上下文, 属性);
//    }
//
//
//}
/**
 * TabItem is a special 'view' which allows you to declare tab items for a {@link TabLayout} within
 * a layout. This view is not actually added to TabLayout, it is just a dummy which allows setting
 * of a tab items's text, icon and custom layout. See TabLayout for more information on how to use
 * it.
 *
 * @attr ref com.google.android.material.R.styleable#TabItem_android_icon
 * @attr ref com.google.android.material.R.styleable#TabItem_android_text
 * @attr ref com.google.android.material.R.styleable#TabItem_android_layout
 * @see 标签布局
 */
//TODO(b/76413401): make class final after the widget migration
public class 标签项 extends View {
    //TODO(b/76413401): make package private after the widget migration
    public final CharSequence text;
    //TODO(b/76413401): make package private after the widget migration
    public final Drawable icon;
    //TODO(b/76413401): make package private after the widget migration
    public final int customLayout;

    public 标签项(Context context) {
        this(context, null);
    }

    @SuppressLint("RestrictedApi")
    public 标签项(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TabItem);
        text = a.getText(R.styleable.TabItem_android_text);
        icon = a.getDrawable(R.styleable.TabItem_android_icon);
        customLayout = a.getResourceId(R.styleable.TabItem_android_layout, 0);
        a.recycle();
    }
}
