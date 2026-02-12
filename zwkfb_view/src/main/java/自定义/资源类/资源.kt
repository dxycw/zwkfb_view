package 自定义.资源类

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import 安卓.内容.主题
import 安卓.内容.资源

//==================================================================================

fun Context.bitmap转Drawable(位图: Bitmap?): Drawable? {
    return 位图?.toDrawable(资源)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun Context.int转Drawable(资源id: Int): Drawable {
    return 资源.getDrawable(资源id)
}

fun Context.attr转int(attr: Int): Int {
    val attrs = intArrayOf(attr)
    val typedArray = obtainStyledAttributes(attrs)
    val selectableItemBackground = typedArray.getResourceId(0, 0)
    typedArray.recycle()
    return selectableItemBackground
}

fun Context.attr转颜色(attr: Int): ColorStateList {
    val typedValue = TypedValue()
    主题.resolveAttribute(attr, typedValue, true)
    val colorPrimary = ContextCompat.getColor(this, typedValue.resourceId)
    return ColorStateList.valueOf(colorPrimary)
}

//==================================================================================




