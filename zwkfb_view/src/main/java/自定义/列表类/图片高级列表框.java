package 自定义.列表类;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import 安卓x.回收视图.组件.回收视图;
import 自定义.主题类.主题类;
import 自定义.动画类.动画类;
import 自定义.系统类.屏幕类;
import 自定义.资源类.资源类;

public class 图片高级列表框 extends 回收视图 {

    public static final int 图片文字变灰效果 = 0;
    public static final int 整体变灰效果 = 1;

    //==============================================================================================

    private Context 上下文;

    public 图片高级列表框(Context 上下文) {
        this(上下文, null);
    }

    public 图片高级列表框(Context 上下文, AttributeSet 属性) {
        this(上下文, 属性, 0);
    }


    private GridLayoutManager 布局参数;
    private 图片高级列表框适配器 适配器;

    private int 列数 = 3;

    public 图片高级列表框(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
        this.上下文 = 上下文;
        this.适配器 = new 图片高级列表框适配器(上下文);
        this.setAdapter(适配器);
        布局参数 = new GridLayoutManager(上下文, 列数);
        this.setLayoutManager(布局参数); //列表布局,垂直,必须写
    }

    public void 置列数(int 列数) {
        this.列数 = 列数;
        布局参数.setSpanCount(列数);
        this.setLayoutManager(布局参数); //列表
    }

    public void 置图标宽高(int 宽度, int 高度) {
        适配器.图标宽度 = 宽度;
        适配器.图标高度 = 高度;
        适配器.刷新();
    }

    public void 置项目单击效果(int 效果) {
        if (效果 == 图片文字变灰效果) {
            适配器.是否图片文字变灰效果 = true;
        } else if (效果 == 整体变灰效果) {
            适配器.是否图片文字变灰效果 = false;
        }
        适配器.刷新();
    }

    public void 添加项目(@NonNull Drawable 图标, @NonNull String 标题) {
        适配器.图标.add(图标);
        适配器.标题.add(标题);
        适配器.刷新();
    }

    public void 添加项目(int 图标, @NonNull String 标题) {
        适配器.图标.add( (图标 == -1) ? null : 资源类.int转Drawable(上下文, 图标));
        适配器.标题.add(标题);
        适配器.刷新();
    }

    public void 置项目标题单行(boolean 标题单行) {
        适配器.标题单行 = 标题单行;
        适配器.刷新();
    }

    public void 置背景颜色(int 颜色) {
        适配器.背景颜色 = 颜色;
        适配器.刷新();
    }

    public void 置项目标题字体大小(int 字体大小) {
        适配器.标题字体大小 = 字体大小;
        适配器.刷新();
    }

    public void 置项目标题字体颜色(int 颜色) {
        适配器.标题字体颜色 = 颜色;
        适配器.刷新();
    }

    /**
     * 以下为事件方法
     */
    // 设置单击项目事件

    public void 置单击项目事件(项目单击事件 项目单击事件) {
        适配器.单击事件 = 项目单击事件;
    }

    public void 置长按项目事件(项目长按事件 项目长按事件) {
        适配器.长按事件 = 项目长按事件;
    }

    private static class 图片高级列表框适配器 extends Adapter<viewHolder> {

        private Context 上下文;

        public 图片高级列表框适配器(Context 上下文) {
            this.上下文 = 上下文;
        }

        public 项目单击事件 单击事件 = null; // 定义接口
        public 项目长按事件 长按事件 = null; // 定义接口

        public ArrayList<Drawable> 图标 = new ArrayList<>();
        public ArrayList<String> 标题 = new ArrayList<>();

        public int 图标宽度 = 50;
        public int 图标高度 = 50;

        public boolean 是否图片文字变灰效果 = false;

        public boolean 标题单行 = false;
        public int 背景颜色 = Color.TRANSPARENT;
        public int 标题字体大小 = 12;
        public int 标题字体颜色 = -1;

        // 创建ViewHolder
        @NonNull
        @Override
        public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LinearLayout 布局 = new LinearLayout(上下文);
            布局.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new viewHolder(布局); // 返回ViewHolder对象
        }

        @Override
        public void onBindViewHolder(@NonNull viewHolder holder, int position) {

            Drawable 项目图标 = 图标.get(position);
            holder.列表图标.setImageDrawable(项目图标);
            LinearLayout.LayoutParams 布局参数 = new LinearLayout.LayoutParams(holder.列表图标.getLayoutParams());
            布局参数.width = 屏幕类.dp转px(上下文,图标宽度);
            布局参数.height = 屏幕类.dp转px(上下文,图标高度);
            holder.列表图标.setLayoutParams(布局参数);

            String 项目文本 = 标题.get(position);
            holder.列表标题.setText(项目文本);
            holder.列表标题.setTextSize(屏幕类.dp转sp(上下文,标题字体大小));
            holder.列表标题.setSingleLine(标题单行);
            holder.列表标题.setTextColor((标题字体颜色 == -1) ? (主题类.是否是深色模式(上下文)) ? Color.WHITE : Color.BLACK : 标题字体颜色);


            if (是否图片文字变灰效果) {
                动画类.变灰效果(holder.itemView);
            } else {
                holder.列表框.setBackgroundResource(资源类.attr转int(上下文,android.R.attr.selectableItemBackground));
            }
            holder.itemView.setBackgroundColor(背景颜色);
            holder.itemView.setOnClickListener(v -> {
                if (单击事件 != null) {
                    单击事件.列表项目单击事件(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(v -> {
                if (长按事件 != null) {
                    长按事件.列表项目长按事件(holder.getAdapterPosition());
                }
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return 标题.size();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void 刷新() {
            notifyDataSetChanged();
        }

    }

    private static class viewHolder extends ViewHolder {

        public LinearLayout 列表框;
        public ImageView 列表图标;
        public TextView 列表标题;

        public viewHolder(ViewGroup 项目视图){
            super(项目视图);

            列表框 = new LinearLayout(项目视图.getContext());
            LinearLayout.LayoutParams 列表框布局参数 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            列表框.setLayoutParams(列表框布局参数);
            列表框.setPadding(屏幕类.dp转px(项目视图.getContext(),5),屏幕类.dp转px(项目视图.getContext(),5),
                    屏幕类.dp转px(项目视图.getContext(),5),屏幕类.dp转px(项目视图.getContext(),5));
            列表框.setOrientation(LinearLayout.VERTICAL);
            列表框.setGravity(Gravity.CENTER);


            列表图标 = new ImageView(项目视图.getContext());
            列表图标.setLayoutParams(new LinearLayout.LayoutParams(屏幕类.dp转px(项目视图.getContext(),25),屏幕类.dp转px(项目视图.getContext(),25)));


            列表标题 = new TextView(项目视图.getContext());
            LinearLayout.LayoutParams 列表标题布局参数 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            列表标题布局参数.weight = 1f;
            列表标题布局参数.setMargins(屏幕类.dp转px(项目视图.getContext(),5),屏幕类.dp转px(项目视图.getContext(),5),
                    屏幕类.dp转px(项目视图.getContext(),5),屏幕类.dp转px(项目视图.getContext(),5));
            列表标题.setLayoutParams(列表标题布局参数);
            列表标题.setGravity(Gravity.CENTER);
            列表标题.setText("列表项");


            列表框.addView(列表图标);
            列表框.addView(列表标题);
            项目视图.addView(列表框);
        }


    }

    public interface 项目单击事件 {
        void 列表项目单击事件(int 项目序数);
    }

    public interface 项目长按事件 {
        void 列表项目长按事件(int 项目序数);
    }

}
