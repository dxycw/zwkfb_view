package 商业.谷歌.安卓.材质.时间选择器

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.RadialGradient
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.os.SystemClock
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.withStyledAttributes
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat
import com.google.android.material.R
import com.google.android.material.resources.MaterialResources
import java.util.Arrays
import kotlin.math.abs
import kotlin.math.max

/**
 * A View to display a clock face.
 * a circle.
 */
@SuppressLint("RestrictedApi")
internal class 时钟表盘视图 @SuppressLint("ClickableViewAccessibility") constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : 径向视图组(context, attrs, defStyleAttr), 时钟指针视图.OnRotateListener {
    private var clockHandView: 时钟指针视图? = null
    private val textViewRect = Rect()
    private val scratch = RectF()
    private val scratchLineBounds = Rect()

    private val textViewPool = SparseArray<TextView?>()
    private val valueAccessibilityDelegate: AccessibilityDelegateCompat

    private var gradientColors: IntArray = intArrayOf()
    private val gradientPositions = floatArrayOf(0f, 0.9f, 1f)
    private var clockHandPadding: Int = 0
    private val minimumHeight: Int
    private val minimumWidth: Int
    private val clockSize: Int

    private var values: Array<String?> = emptyArray()

    private var currentHandRotation = 0f

    private var textColor: ColorStateList? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : this(
        context, attrs, R.attr.materialClockStyle
    )

    init {
        val res = getResources()
        context.withStyledAttributes(
            attrs, R.styleable.ClockFaceView, defStyleAttr,
            R.style.Widget_MaterialComponents_TimePicker_Clock
        ){

            textColor = MaterialResources.getColorStateList(
                    context, this, R.styleable.ClockFaceView_clockNumberTextColor
                )
            LayoutInflater.from(context)
                .inflate(zwkfb.view.R.layout.material_clockface_view1, this@时钟表盘视图, true)
            clockHandView = findViewById(R.id.material_clock_hand)
            clockHandPadding = res.getDimensionPixelSize(R.dimen.material_clock_hand_padding)
            val clockHandTextColor = textColor!!.getColorForState(
                    intArrayOf(android.R.attr.state_selected), textColor!!.getDefaultColor()
                )
            gradientColors = intArrayOf(clockHandTextColor, clockHandTextColor, textColor!!.getDefaultColor())
            clockHandView!!.addOnRotateListener(this@时钟表盘视图)

            val defaultBackgroundColor = AppCompatResources
                .getColorStateList(context, R.color.material_timepicker_clockface)
                .getDefaultColor()

            val backgroundColor =
                MaterialResources.getColorStateList(
                    context, this, R.styleable.ClockFaceView_clockFaceBackgroundColor
                )

            setBackgroundColor(
                if (backgroundColor == null) defaultBackgroundColor else backgroundColor.getDefaultColor()
            )

            getViewTreeObserver().addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (!isShown()) {
                        return true
                    }
                    getViewTreeObserver().removeOnPreDrawListener(this)
                    val circleRadius =
                        getHeight() / 2 - clockHandView!!.selectorRadius - clockHandPadding
                    setRadius(circleRadius)
                    return true
                }
            })

            setFocusable(false)
        }

        valueAccessibilityDelegate = object : AccessibilityDelegateCompat() {
                override fun onInitializeAccessibilityNodeInfo(
                    host: View, info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    val index = host.getTag(R.id.material_value_index) as Int
                    if (index > 0) {
                        info.setTraversalAfter(textViewPool.get(index - 1))
                    }

                    info.setCollectionItemInfo(
                        CollectionItemInfoCompat.obtain( /* rowIndex= */
                            0,  /* rowSpan= */
                            1,  /* columnIndex =*/
                            index,  /* columnSpan= */
                            1,  /* heading= */
                            false,  /* selected= */
                            host.isSelected()
                        )
                    )

                    info.setClickable(true)
                    info.addAction(AccessibilityActionCompat.ACTION_CLICK)
                }

                override fun performAccessibilityAction(
                    host: View,
                    action: Int,
                    args: Bundle?
                ): Boolean {
                    if (action == AccessibilityNodeInfoCompat.ACTION_CLICK) {
                        val time = SystemClock.uptimeMillis()
                        host.getHitRect(textViewRect)
                        val x = textViewRect.centerX().toFloat()
                        val y = textViewRect.centerY().toFloat()
                        clockHandView!!.onTouchEvent(
                            MotionEvent.obtain(
                                time,
                                time,
                                MotionEvent.ACTION_DOWN,
                                x,
                                y,
                                0
                            )
                        )
                        clockHandView!!.onTouchEvent(
                            MotionEvent.obtain(
                                time,
                                time,
                                MotionEvent.ACTION_UP,
                                x,
                                y,
                                0
                            )
                        )
                        return true
                    }
                    return super.performAccessibilityAction(host, action, args)
                }
            }

        // Fill clock face with place holders
        val initialValues = arrayOfNulls<String>(INITIAL_CAPACITY)
        Arrays.fill(initialValues, VALUE_PLACEHOLDER)
        setValues(initialValues,  /* contentDescription= */0)

        minimumHeight = res.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height)
        minimumWidth = res.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width)
        clockSize = res.getDimensionPixelSize(R.dimen.material_clock_size)
    }

    /**
     * Sets the list of values that will be shown in the clock face. The first value will be shown in
     * the 12 O'Clock position, subsequent values will be evenly distributed after.
     */
    fun setValues(values: Array<String?>, @StringRes contentDescription: Int) {
        this.values = values
        updateTextViews(contentDescription)
    }

    private fun updateTextViews(@StringRes contentDescription: Int) {
        var isMultiLevel = false

        val inflater = LayoutInflater.from(getContext())
        val size = textViewPool.size()
        for (i in 0..<max(values.size, size)) {
            var textView = textViewPool.get(i)
            if (i >= values.size) {
                removeView(textView)
                textViewPool.remove(i)
                continue
            }

            if (textView == null) {
                textView =
                    inflater.inflate(R.layout.material_clockface_textview, this, false) as TextView?
                textViewPool.put(i, textView)
                addView(textView)
            }

            textView!!.setText(values[i])
            textView.setTag(R.id.material_value_index, i)

            val level: Int = (i / INITIAL_CAPACITY) + LEVEL_1
            textView.setTag(R.id.material_clock_level, level)
            if (level > LEVEL_1) {
                isMultiLevel = true
            }

            ViewCompat.setAccessibilityDelegate(textView, valueAccessibilityDelegate)

            textView.setTextColor(textColor)
            if (contentDescription != 0) {
                val res = getResources()
                textView.setContentDescription(res.getString(contentDescription, values[i]))
            }
        }

        clockHandView!!.setMultiLevel(isMultiLevel)
    }

    override fun updateLayoutParams() {
        super.updateLayoutParams()
        for (i in 0..<textViewPool.size()) {
            textViewPool.get(i)!!.setVisibility(VISIBLE)
        }
    }

    override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(info)
        val infoCompat = AccessibilityNodeInfoCompat.wrap(info)
        infoCompat.setCollectionInfo(
            CollectionInfoCompat.obtain( /* rowCount= */
                1,  /* columnCount= */
                values.size,  /* hierarchical= */
                false,
                CollectionInfoCompat.SELECTION_MODE_SINGLE
            )
        )
    }

    override fun setRadius(radius: Int) {
        if (radius != getRadius()) {
            super.setRadius(radius)
            clockHandView!!.setCircleRadius(getRadius())
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        findIntersectingTextView()
    }

    fun setHandRotation(@FloatRange(from = 0.0, to = 360.0) rotation: Float) {
        clockHandView!!.setHandRotation(rotation)
        findIntersectingTextView()
    }

    private fun findIntersectingTextView() {
        val selectorBox = clockHandView!!.currentSelectorBox
        val selected = getSelectedTextView(selectorBox)
        for (i in 0..<textViewPool.size()) {
            val tv = textViewPool.get(i)
            if (tv == null) {
                continue
            }

            // set selection
            tv.setSelected(tv === selected)

            // set gradient
            val radialGradient = getGradientForTextView(selectorBox, tv)
            tv.getPaint().setShader(radialGradient)
            tv.invalidate()
        }
    }

    private fun getSelectedTextView(selectorBox: RectF): TextView? {
        var minArea = Float.Companion.MAX_VALUE
        var selected: TextView? = null

        for (i in 0..<textViewPool.size()) {
            val tv = textViewPool.get(i)
            if (tv == null) {
                continue
            }
            tv.getHitRect(textViewRect)
            scratch.set(textViewRect)
            scratch.union(selectorBox)
            val area = scratch.width() * scratch.height()
            if (area < minArea) { // the smallest enclosing rectangle is the selection (most overlap)
                minArea = area
                selected = tv
            }
        }

        return selected
    }

    private fun getGradientForTextView(selectorBox: RectF, tv: TextView): RadialGradient? {
        tv.getHitRect(textViewRect)
        scratch.set(textViewRect)
        tv.getLineBounds(0, scratchLineBounds)
        scratch.inset(scratchLineBounds.left.toFloat(), scratchLineBounds.top.toFloat())
        if (!RectF.intersects(selectorBox, scratch)) {
            return null
        }

        return RadialGradient(
            (selectorBox.centerX() - scratch.left),
            (selectorBox.centerY() - scratch.top),
            selectorBox.width() * .5f,
            gradientColors,
            gradientPositions,
            TileMode.CLAMP
        )
    }

    override fun onRotate(rotation: Float, animating: Boolean) {
        if (abs(currentHandRotation - rotation) > EPSILON) {
            currentHandRotation = rotation
            findIntersectingTextView()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val r = getResources()
        val displayMetrics = r.getDisplayMetrics()

        val height = displayMetrics.heightPixels.toFloat()
        val width = displayMetrics.widthPixels.toFloat()

        // If the screen is smaller than our defined values. Scale the clock face
        // proportionally to the smaller size
        val size = (clockSize / max3(minimumHeight / height, minimumWidth / width, 1f)).toInt()

        val spec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        setMeasuredDimension(size, size)
        super.onMeasure(spec, spec)
    }

    @get:Level
    var currentLevel: Int
        get() = clockHandView!!.getCurrentLevel()
        set(level) {
            clockHandView!!.setCurrentLevel(level)
        }

    companion object {
        private const val EPSILON = .001f
        private const val INITIAL_CAPACITY = 12
        private const val VALUE_PLACEHOLDER = ""

        private fun max3(a: Float, b: Float, c: Float): Float {
            return max(max(a, b), c)
        }
    }
}
