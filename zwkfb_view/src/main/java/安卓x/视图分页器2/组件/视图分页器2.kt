package 安卓x.视图分页器2.组件

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.ClassLoaderCreator
import android.util.AttributeSet
import android.util.SparseArray
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.Px
import androidx.annotation.RequiresApi
import androidx.annotation.RestrictTo
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat
import androidx.core.view.accessibility.AccessibilityViewCommand
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.R
import androidx.viewpager2.adapter.StatefulAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min



//====================================================================================

/**
 * 描述：方向
 *
 * 版本：0.1.1
 */
var ViewPager2.方向: Int
    get() = orientation
    set(方向) { orientation = 方向 }

/**
 * 描述：取方向
 *
 * 版本：0.1.1
 */
fun ViewPager2.取方向(): Int = getOrientation()
/**
 * 描述：置方向
 *
 * 版本：0.1.1
 */
fun ViewPager2.置方向(方向: Int) = setOrientation(方向)

//====================================================================================

/**
 * 描述：适配器
 *
 * 版本：0.1.1
 */
var ViewPager2.适配器: RecyclerView.Adapter<*>?
    get() = adapter
    set(适配器) { adapter = 适配器 }

/**
 * 描述：取适配器
 *
 * 版本：0.1.1
 */
fun ViewPager2.取适配器(): RecyclerView.Adapter<*>? = getAdapter()
/**
 * 描述：置适配器
 *
 * 版本：0.1.1
 */
fun ViewPager2.置适配器(适配器: RecyclerView.Adapter<*>) = setAdapter(适配器)

//====================================================================================



