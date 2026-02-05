package 商业.谷歌.安卓.材质.时间选择器

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.IntDef
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.R
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize

/**
 * A View Group evenly distributes children in circles.
 *
 * <P> Children that set `android:tag="skip"` can be positioned anywhere in the container.
</P> * <P> Children that set `android:tag="level"` can be positioned in multiple circles.
</P> */
internal open class 径向视图组 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    /** Position views in circles, `LEVEL_1` for outer, `LEVEL_2` for inner.  */
    @IntDef(*[LEVEL_1, LEVEL_2])
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class Level

    private val updateLayoutParametersRunnable: Runnable
    private var radius: Int
    private var background: MaterialShapeDrawable? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this)
        setBackground(createBackground())

        val a =
            context.obtainStyledAttributes(attrs, R.styleable.RadialViewGroup, defStyleAttr, 0)
        radius = a.getDimensionPixelSize(R.styleable.RadialViewGroup_materialCircleRadius, 0)
        updateLayoutParametersRunnable = Runnable { this.updateLayoutParams() }
        a.recycle()
    }

    private fun createBackground(): Drawable {
        background = MaterialShapeDrawable()
        background!!.setCornerSize(RelativeCornerSize(.5f))
        background!!.setFillColor(ColorStateList.valueOf(Color.WHITE))
        return background!!
    }

    /** Set the background color for the circular background  */
    override fun setBackgroundColor(@ColorInt color: Int) {
        background!!.setFillColor(ColorStateList.valueOf(color))
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        if (child.getId() == NO_ID) {
            child.setId(generateViewId())
        }
        updateLayoutParamsAsync()
    }

    override fun onViewRemoved(view: View?) {
        super.onViewRemoved(view)
        // Post so we only update once on a batch of added views.
        updateLayoutParamsAsync()
    }

    private fun updateLayoutParamsAsync() {
        // Post so we only update once on a batch of added views.
        val handler = getHandler()
        if (handler != null) {
            handler.removeCallbacks(updateLayoutParametersRunnable)
            handler.post(updateLayoutParametersRunnable)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        updateLayoutParams()
    }

    protected open fun updateLayoutParams() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        val levels: MutableMap<Int?, MutableList<View?>?> = HashMap<Int?, MutableList<View?>?>()
        for (i in 0..<getChildCount()) {
            val childAt = getChildAt(i)
            if (childAt.getId() == R.id.circle_center || shouldSkipView(childAt)) {
                continue
            }

            var level = childAt.getTag(R.id.material_clock_level) as Int?
            if (level == null) {
                level = LEVEL_1
            }
            if (!levels.containsKey(level)) {
                levels.put(level, ArrayList<View?>()) // initialize if empty
            }
            levels.get(level)!!.add(childAt)
        }

        for (entry in levels.entries) {
            addConstraints(entry.value!!, constraintSet, getLeveledRadius(entry.key!!))
        }

        constraintSet.applyTo(this@径向视图组)
    }

    private fun addConstraints(
        views: MutableList<View?>, constraintSet: ConstraintSet, leveledRadius: Int
    ) {
        var currentAngle = 0f
        for (view in views) {
            constraintSet.constrainCircle(
                view!!.getId(),
                R.id.circle_center,
                leveledRadius,
                currentAngle
            )
            currentAngle += (360f / views.size)
        }
    }

    open fun setRadius(@Dimension radius: Int) {
        this.radius = radius
        updateLayoutParams()
    }

    @Dimension
    fun getRadius(): Int {
        return radius
    }

    @Dimension
    fun getLeveledRadius(@Level level: Int): Int {
        return if (level == LEVEL_2) Math.round(radius * LEVEL_RADIUS_RATIO) else radius
    }

    companion object {
        private const val SKIP_TAG = "skip"

        const val LEVEL_1: Int = 1
        const val LEVEL_2: Int = 2
        const val LEVEL_RADIUS_RATIO: Float = .66f

        private fun shouldSkipView(child: View): Boolean {
            return SKIP_TAG == child.getTag()
        }
    }
}
