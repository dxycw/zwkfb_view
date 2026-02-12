package 安卓x.碎片.应用

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.FragmentContainerView


//class 碎片容器视图 : FragmentContainerView {
//    constructor(context: Context) : super(context)
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    )
//}

//class FragmentContainerView : FrameLayout {
//    private val disappearingFragmentChildren: MutableList<View> = mutableListOf()
//    private val transitioningFragmentViews: MutableList<View> = mutableListOf()
//    private var applyWindowInsetsListener: OnApplyWindowInsetsListener? = null
//
//    private var drawDisappearingViewsFirst = true
//
//    constructor(context: Context) : super(context)
//
//    @JvmOverloads
//    constructor(
//        context: Context,
//        attrs: AttributeSet?,
//        defStyleAttr: Int = 0
//    ) : super(context, attrs, defStyleAttr) {
//        if (attrs != null) {
//            var name = attrs.classAttribute
//            var attribute = "class"
//            context.withStyledAttributes(attrs, R.styleable.FragmentContainerView) {
//                if (name == null) {
//                    name = getString(R.styleable.FragmentContainerView_android_name)
//                    attribute = "android:name"
//                }
//            }
//            if (name != null && !isInEditMode) {
//                throw UnsupportedOperationException(
//                    "FragmentContainerView must be within a FragmentActivity to use $attribute" +
//                            "=\"$name\""
//                )
//            }
//        }
//    }
//
//    internal constructor(
//        context: Context,
//        attrs: AttributeSet,
//        fm: FragmentManager
//    ) : super(context, attrs) {
//        var name = attrs.classAttribute
//        var tag: String? = null
//        context.withStyledAttributes(attrs, R.styleable.FragmentContainerView) {
//            if (name == null) {
//                name = getString(R.styleable.FragmentContainerView_android_name)
//            }
//            tag = getString(R.styleable.FragmentContainerView_android_tag)
//        }
//        val id = id
//        val existingFragment: Fragment? = fm.findFragmentById(id)
//        // If there is a name and there is no existing fragment,
//        // we should add an inflated Fragment to the view.
//        if (name != null && existingFragment == null) {
//            if (id == View.NO_ID) {
//                val tagMessage = if (tag != null) " with tag $tag" else ""
//                throw IllegalStateException(
//                    "FragmentContainerView must have an android:id to add Fragment $name$tagMessage"
//                )
//            }
//            val containerFragment: Fragment =
//                fm.fragmentFactory.instantiate(context.classLoader, name)
//            containerFragment.onInflate(context, attrs, null)
//            fm.beginTransaction()
//                .setReorderingAllowed(true)
//                .add(this.id, containerFragment, tag)
//                .commitNowAllowingStateLoss()
//        }
//        fm.onContainerAvailable(this)
//    }
//
//
//
//
//    override fun setLayoutTransition(transition: LayoutTransition?) {
//        if (Build.VERSION.SDK_INT < 18) {
//            // Transitions on APIs below 18 are using an empty LayoutTransition as a replacement
//            // for suppressLayout(true) and null LayoutTransition to then unsuppress it. If the
//            // API is below 18, we should allow FrameLayout to handle this call.
//            super.setLayoutTransition(transition)
//            return
//        }
//        throw UnsupportedOperationException(
//            "FragmentContainerView does not support Layout Transitions or " +
//                    "animateLayoutChanges=\"true\"."
//        )
//    }
//
//    override fun setOnApplyWindowInsetsListener(listener: OnApplyWindowInsetsListener) {
//        applyWindowInsetsListener = listener
//    }
//
//    @RequiresApi(20)
//    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets = insets
//
//    @RequiresApi(20)
//    override fun dispatchApplyWindowInsets(insets: WindowInsets): WindowInsets {
//        val insetsCompat = WindowInsetsCompat.toWindowInsetsCompat(insets)
//        val dispatchInsets = if (applyWindowInsetsListener != null) {
//            WindowInsetsCompat.toWindowInsetsCompat(
//                Api20Impl.onApplyWindowInsets(applyWindowInsetsListener!!, this, insets)
//            )
//        } else {
//            ViewCompat.onApplyWindowInsets(this, insetsCompat)
//        }
//        if (!dispatchInsets.isConsumed) {
//            for (i in 0 until childCount) {
//                ViewCompat.dispatchApplyWindowInsets(getChildAt(i), dispatchInsets)
//            }
//        }
//        return insets
//    }
//
//    protected override fun dispatchDraw(canvas: Canvas) {
//        if (drawDisappearingViewsFirst) {
//            disappearingFragmentChildren.forEach { child ->
//                super.drawChild(canvas, child, drawingTime)
//            }
//        }
//        super.dispatchDraw(canvas)
//    }
//
//    protected override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
//        if (drawDisappearingViewsFirst && disappearingFragmentChildren.isNotEmpty()) {
//            // If the child is disappearing, we have already drawn it so skip.
//            if (disappearingFragmentChildren.contains(child)) {
//                return false
//            }
//        }
//        return super.drawChild(canvas, child, drawingTime)
//    }
//
//    override fun startViewTransition(view: View) {
//        if (view.parent === this) {
//            transitioningFragmentViews.add(view)
//        }
//        super.startViewTransition(view)
//    }
//
//    override fun endViewTransition(view: View) {
//        transitioningFragmentViews.remove(view)
//        if (disappearingFragmentChildren.remove(view)) {
//            drawDisappearingViewsFirst = true
//        }
//        super.endViewTransition(view)
//    }
//
//    // Used to indicate the container should change the default drawing order.
//    @JvmName("setDrawDisappearingViewsLast")
//    internal fun setDrawDisappearingViewsLast(drawDisappearingViewsFirst: Boolean) {
//        this.drawDisappearingViewsFirst = drawDisappearingViewsFirst
//    }
//
//    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams?) {
//        checkNotNull(getViewFragment(child)) {
//            ("Views added to a FragmentContainerView must be associated with a Fragment. View " +
//                    "$child is not associated with a Fragment.")
//        }
//        super.addView(child, index, params)
//    }
//
//    fun getViewFragment(view : View) : Fragment?{
//        val tag = view.getTag(R.id.fragment_container_view_tag);
//        if (tag is Fragment) {
//            return tag
//        }
//        return null
//    }
//
//    override fun removeViewAt(index: Int) {
//        val view = getChildAt(index)
//        addDisappearingFragmentView(view)
//        super.removeViewAt(index)
//    }
//
//    override fun removeViewInLayout(view: View) {
//        addDisappearingFragmentView(view)
//        super.removeViewInLayout(view)
//    }
//
//    override fun removeView(view: View) {
//        addDisappearingFragmentView(view)
//        super.removeView(view)
//    }
//
//    override fun removeViews(start: Int, count: Int) {
//        for (i in start until start + count) {
//            val view = getChildAt(i)
//            addDisappearingFragmentView(view)
//        }
//        super.removeViews(start, count)
//    }
//
//    override fun removeViewsInLayout(start: Int, count: Int) {
//        for (i in start until start + count) {
//            val view = getChildAt(i)
//            addDisappearingFragmentView(view)
//        }
//        super.removeViewsInLayout(start, count)
//    }
//
//    override fun removeAllViewsInLayout() {
//        for (i in childCount - 1 downTo 0) {
//            val view = getChildAt(i)
//            addDisappearingFragmentView(view)
//        }
//        super.removeAllViewsInLayout()
//    }
//
//    private fun addDisappearingFragmentView(v: View) {
//        if (transitioningFragmentViews.contains(v)) {
//            disappearingFragmentChildren.add(v)
//        }
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    fun <F : Fragment?> getFragment(): F =
//        FragmentManager.findFragmentManager(this).findFragmentById(this.id) as F
//
//    @RequiresApi(20)
//    internal object Api20Impl {
//        fun onApplyWindowInsets(
//            onApplyWindowInsetsListener: OnApplyWindowInsetsListener,
//            v: View,
//            insets: WindowInsets
//        ): WindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(v, insets)
//    }
//
//}