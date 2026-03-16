package 自定义.列表类;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import zwkfb.view.R;
import 安卓x.回收视图.组件.回收视图;
import 自定义.动画类.动画类;
import 自定义.系统类.屏幕类;
import 自定义.资源类.资源类;

public class 文本高级列表框 extends 回收视图 {

    public static final int 文字变灰效果 = 0;
    public static final int 整体变灰效果 = 1;

    //==============================================================================================

    private Context 上下文;
    private LinearLayoutManager 布局参数;
    private DividerItemDecoration 分割线;

    private final 文本高级列表框适配器 适配器;

    public 文本高级列表框(Context 上下文) {
        this(上下文,null);
    }

    public 文本高级列表框(Context 上下文, AttributeSet 属性) {
        this(上下文, 属性,0);
    }

    public 文本高级列表框(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
        this.上下文 = 上下文;
        this.适配器 = new 文本高级列表框适配器(上下文);
        this.setAdapter(适配器);
        布局参数 = new LinearLayoutManager(上下文, LinearLayoutManager.VERTICAL, false);
        this.setLayoutManager(布局参数); //列表布局,垂直,必须写
        分割线 = new DividerItemDecoration(上下文, 布局参数.getOrientation());
    }


    public void 置项目单击效果(int 效果) {
        if (效果 == 文字变灰效果) {
            适配器.是否文字变灰效果 = true;
        } else if (效果 == 整体变灰效果) {
            适配器.是否文字变灰效果 = false;
        }
        适配器.刷新();
    }

    public void 添加项目(String 标题) {
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

    public void 置分割线可视(boolean 值) {
        if (值) {
            if (this.getItemDecorationCount() == 0) {
                this.addItemDecoration(分割线);
            }
        } else {
            this.removeItemDecoration(分割线);
        }
    }

    public void 置单击项目事件(项目单击事件 单击项目事件) {
        适配器.单击事件 = 单击项目事件;
    }
    public void 置长按项目事件(项目长按事件 长按项目事件) {
        适配器.长按事件 = 长按项目事件;
    }

    private static class 文本高级列表框适配器  extends Adapter<viewHolder> {

        private Context 上下文;
        public 文本高级列表框适配器(Context 上下文) {
            this.上下文 = 上下文;
        }


        public 项目单击事件 单击事件 = null; // 定义接口
        public 项目长按事件 长按事件 = null;

        public boolean 是否文字变灰效果 = false;

        public ArrayList<String> 标题 = new ArrayList<>();

        public boolean 标题单行 = false;
        public int 背景颜色 = Color.TRANSPARENT;
        public float 标题字体大小 = 16;
        public int 标题字体颜色 = Color.BLACK;


        @NonNull
        @Override
        public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LinearLayout 布局 = new LinearLayout(上下文);
            布局.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new viewHolder(布局); // 返回ViewHolder对象
        }

        @Override
        public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            String 项目文本 = 标题.get(position);
            holder.列表标题.setText(项目文本);
            holder.列表标题.setTextSize(标题字体大小);
            holder.列表标题.setSingleLine(标题单行);
            holder.列表标题.setTextColor(标题字体颜色);


            if (是否文字变灰效果) {
                动画类.变灰效果(holder.itemView);
            } else {
                holder.列表框.setBackgroundResource(
                        资源类.attr转int(上下文,android.R.attr.selectableItemBackground)
                );
            }

            holder.itemView.setBackgroundColor(背景颜色);

            holder.itemView.setOnClickListener ( v -> {
                if (单击事件 != null) {
                    单击事件.列表项目单击事件(holder.getAdapterPosition());
                }
            });

            holder.itemView.setOnLongClickListener ( v -> {
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
            this.notifyDataSetChanged();
        }

    }
    private static class viewHolder extends ViewHolder {

        public LinearLayout 列表框;
        public TextView 列表标题;

        public viewHolder(ViewGroup 项目视图) {
            super(项目视图);

            列表框 = new LinearLayout(项目视图.getContext());
            列表框.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            列表框.setOrientation(LinearLayout.HORIZONTAL);
            列表框.setBackgroundResource(资源类.attr转int(项目视图.getContext(), android.R.attr.selectableItemBackground));
            列表框.setGravity(Gravity.CENTER);

            列表标题 = new TextView(项目视图.getContext());
            LinearLayout.LayoutParams 标题布局参数 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1f);
            列表标题.setLayoutParams(标题布局参数);
            列表标题.setPadding(屏幕类.dp转px(项目视图.getContext(), 15), 屏幕类.dp转px(项目视图.getContext(), 15),
                    屏幕类.dp转px(项目视图.getContext(), 15), 屏幕类.dp转px(项目视图.getContext(), 15));
            列表标题.setText("标题");

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
