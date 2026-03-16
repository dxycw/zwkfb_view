package 自定义.文本类;

import android.annotation.SuppressLint;
import android.app.SearchableInfo;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import 安卓x.应用兼容.组件.搜索视图;

public class 搜索栏 extends 搜索视图 {

    public 搜索栏(Context 上下文) {
        super(上下文);
    }

    public 搜索栏(Context 上下文, AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 搜索栏(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }


    private Drawable 搜索提示图标 = null;
    private TextView 文本视图 = null;


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        try {
            if (文本视图 == null) {
                文本视图 = findViewById(androidx.appcompat.R.id.search_src_text);
                搜索提示图标 = this.getContext().getDrawable(zwkfb.view.R.drawable.ic_search);
            }
            // 改变字体
            文本视图.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
            文本视图.setGravity(Gravity.CENTER_VERTICAL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                文本视图.setLocalePreferredLineHeightForMinimumUsed(false);
            }
            更新查询提示();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private CharSequence getDecoratedHint(CharSequence 提示文本) {
        if (搜索提示图标 == null) { return 提示文本; }
        int textSize = (int) 文本视图.getTextSize();
        搜索提示图标.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder ssb = new SpannableStringBuilder("   ");
        ssb.setSpan(new CenteredImageSpan(搜索提示图标), 1, 2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(提示文本);
        return ssb;
    }

    private void 更新查询提示() {
        if (文本视图 != null) {
            文本视图.setHint(getDecoratedHint(getQueryHint()));
        }
    }


    @Override
    public void setIconifiedByDefault(boolean iconified) {
        super.setIconifiedByDefault(iconified);
        更新查询提示();
    }

    @Override
    public void setSearchableInfo(SearchableInfo searchable) {
        super.setSearchableInfo(searchable);
        if (searchable != null) {
            更新查询提示 ();
        }
    }

    @Override
    public void setQueryHint(@Nullable CharSequence hint) {
        super.setQueryHint(hint);
        更新查询提示();
    }

    static class CenteredImageSpan extends ImageSpan {
        public CenteredImageSpan(@NonNull Drawable 可绘制) {
            super(可绘制);
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
            super.draw(canvas, text, start, end, x, top, y, bottom, paint);
            // image to draw
            Drawable b = getDrawable();
            // font metrics of text to be replaced
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
            int transY = ((y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2);
            canvas.save();
            canvas.translate(x, transY);
            b.draw(canvas);
            canvas.restore();
        }

    }



}
