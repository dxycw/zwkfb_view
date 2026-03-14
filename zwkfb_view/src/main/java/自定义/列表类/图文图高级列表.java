package 自定义.列表类;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import 安卓x.回收视图.组件.回收视图;
import 自定义.动画类.动画类;
import 自定义.系统类.屏幕类;
import 自定义.资源类.资源类;

public class 图文图高级列表 extends 回收视图 {

    private Context 上下文;
    private 图文图高级列表适配器 适配器;

    public 图文图高级列表(Context 上下文) {
        this(上下文, null);
    }

    public 图文图高级列表(Context 上下文, AttributeSet 属性) {
        this(上下文, 属性, 0);
    }

    public 图文图高级列表(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
        this.上下文 = 上下文;
        this.适配器 = new 图文图高级列表适配器(上下文);
        this.setAdapter(适配器);
        this.setLayoutManager(new GridLayoutManager(上下文, 1)); //列表布局,垂直,必须写
    }


    public void 添加项目(@NonNull Drawable 图标, @NonNull String 标题, @NonNull Drawable 按钮) {
        适配器.图标.add(图标);
        适配器.标题.add(标题);
        适配器.按钮.add(按钮);
        适配器.刷新();
    }

    public void 添加项目(int 图标, @NonNull String 标题, int 按钮) {
        适配器.图标.add( (图标 == -1) ? null : 资源类.int转Drawable(上下文, 图标));
        适配器.标题.add(标题);
        适配器.按钮.add( (按钮 == -1) ? null : 资源类.int转Drawable(上下文, 按钮));
        适配器.刷新();
    }

    public void 删除项目(int 项目序数) {
        适配器.图标.remove(项目序数);
        适配器.标题.remove(项目序数);
        适配器.按钮.remove(项目序数);
        适配器.刷新();
    }

    public void 清空项目() {
        适配器.图标.clear();
        适配器.标题.clear();
        适配器.按钮.clear();
        适配器.刷新();
    }

    public String 获取项目标题(int 项目序数) {
        if (项目序数 >= 0 && 项目序数 < 适配器.标题.size()) {
            return 适配器.标题.get(项目序数);
        } else {
            return 适配器.标题.get((项目序数 < 0) ? 0 : 适配器.标题.size() - 1);
        }
    }

    public int 获取项目总数() {
        return 适配器.标题.size();
    }

    public ArrayList<String> 获取项目标题() {
        return 适配器.标题;
    }

    public void 置标题单行(boolean 值) {
        适配器.标题单行 = 值;
        适配器.刷新();
    }


    public void 置图标宽高(int 宽度, int 高度) {
        适配器.图标宽度 = 宽度;
        适配器.图标高度 = 高度;
        适配器.刷新();
    }

    public void 置按钮宽高(int 宽度, int 高度) {
        适配器.按钮宽度 = 宽度;
        适配器.按钮高度 = 高度;
        适配器.刷新();
    }


    public void 置单击项目事件(项目单击事件 单击项目事件) {
        适配器.单击事件 = 单击项目事件;
    }

    public void 置长按项目事件(项目长按事件 长按项目事件) {
        适配器.长按事件 = 长按项目事件;
    }


    public void 置单击按钮事件(项目单击事件 单击项目事件) {
        适配器.单击按钮事件 = 单击项目事件;
    }


    public void 置长按按钮事件(项目长按事件 长按项目事件) {
        适配器.长按按钮事件 = 长按项目事件;
    }


    private static class 图文图高级列表适配器 extends Adapter<viewHolder> {

        private Context 上下文;

        //=========================================================================================

        public 项目单击事件 单击事件 = null; // 定义接口
        public 项目长按事件 长按事件 = null; // 定义接口

        public 项目单击事件 单击按钮事件 = null; // 定义接口
        public 项目长按事件 长按按钮事件 = null; // 定义接口

        //=========================================================================================

        public ArrayList<Drawable> 图标 = new ArrayList<>();
        public ArrayList<String> 标题 = new ArrayList<>();
        public ArrayList<Drawable> 按钮 = new ArrayList<>();

        //=========================================================================================

        public boolean 标题单行 = false;

        public int 图标宽度 = 25;
        public int 图标高度 = 25;

        public int 按钮宽度 = 25;
        public int 按钮高度 = 25;

        //=========================================================================================


        public 图文图高级列表适配器(Context 上下文) {
            this.上下文 = 上下文;
        }

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
            String 项目文本 = 标题.get(position);
            Drawable 项目按钮 = 按钮.get(position);

            if (项目图标 != null){
                holder.列表图标.setVisibility(View.VISIBLE);
                holder.列表图标.setImageDrawable(项目图标);
                LinearLayout.LayoutParams 图标布局参数 = new LinearLayout.LayoutParams(holder.列表图标.getLayoutParams());
                图标布局参数.width = 屏幕类.dp转px(上下文, 图标宽度);
                图标布局参数.height = 屏幕类.dp转px(上下文, 图标高度);
                图标布局参数.setMargins(屏幕类.dp转px(上下文, 15), 屏幕类.dp转px(上下文, 15), 屏幕类.dp转px(上下文, 15),
                        屏幕类.dp转px(上下文, 15));
                holder.列表图标.setLayoutParams(图标布局参数);
            }else{
                holder.列表图标.setVisibility(View.GONE);
            }

            holder.列表标题.setText(项目文本);
            holder.列表标题.setTextSize(16f);
            holder.列表标题.setSingleLine(标题单行);
            LinearLayout.LayoutParams 标题布局参数 = new LinearLayout.LayoutParams(holder.列表标题.getLayoutParams());
            标题布局参数.weight = 1f;
            标题布局参数.width = LinearLayout.LayoutParams.MATCH_PARENT;
            标题布局参数.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            if (项目图标 == null) {
                标题布局参数.setMargins(屏幕类.dp转px(上下文, 15), 屏幕类.dp转px(上下文, 15), 屏幕类.dp转px(上下文, 15),
                        屏幕类.dp转px(上下文, 15));
            }
            holder.列表标题.setLayoutParams(标题布局参数);

            if (项目按钮 != null){
                holder.列表按钮.setVisibility(View.VISIBLE);
                holder.列表按钮.setImageDrawable(项目按钮);
                LinearLayout.LayoutParams 按钮布局参数 = new LinearLayout.LayoutParams(holder.列表按钮.getLayoutParams());
                按钮布局参数.width = 屏幕类.dp转px(上下文, 按钮宽度);
                按钮布局参数.height = 屏幕类.dp转px(上下文, 按钮高度);
                按钮布局参数.setMargins(屏幕类.dp转px(上下文, 15), 屏幕类.dp转px(上下文, 15), 屏幕类.dp转px(上下文, 15),
                        屏幕类.dp转px(上下文, 15));
                holder.列表按钮.setLayoutParams(按钮布局参数);
                动画类.变灰效果(holder.列表按钮);
                holder.列表按钮.setOnClickListener(v -> {
                    if (单击按钮事件 != null) {
                        单击按钮事件.列表项目单击事件(holder.getAdapterPosition());
                    }
                });
                holder.列表按钮.setOnLongClickListener(v -> {
                    if (长按按钮事件 != null) {
                        长按按钮事件.列表项目长按事件(holder.getAdapterPosition());
                    }
                    return true;
                });
            }else{
                holder.列表按钮.setVisibility(View.GONE);
            }


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
            this.notifyDataSetChanged();
        }

    }

    private static class viewHolder extends ViewHolder {

        LinearLayout 列表框;
        ImageView 列表图标;
        TextView 列表标题;
        ImageView 列表按钮;

        public viewHolder(@NonNull View 项目视图) {
            super(项目视图);

            ViewGroup 项目视图1 = (ViewGroup) 项目视图;


            列表框 = new LinearLayout(项目视图.getContext());
            列表框.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            列表框.setOrientation(LinearLayout.HORIZONTAL);
            列表框.setBackgroundResource(资源类.attr转int(项目视图.getContext(), android.R.attr.selectableItemBackground));
            列表框.setGravity(Gravity.CENTER);


            列表图标 = new ImageView(项目视图.getContext());
            LinearLayout.LayoutParams 图标布局参数 = new LinearLayout.LayoutParams(
                    屏幕类.dp转px(项目视图.getContext(), 25), 屏幕类.dp转px(项目视图.getContext(), 25));
            图标布局参数.setMargins(屏幕类.dp转px(项目视图.getContext(), 15), 屏幕类.dp转px(项目视图.getContext(), 15),
                    屏幕类.dp转px(项目视图.getContext(), 15), 屏幕类.dp转px(项目视图.getContext(), 15));
            列表图标.setLayoutParams(图标布局参数);


            列表标题 = new TextView(项目视图.getContext());
            LinearLayout.LayoutParams 标题布局参数 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1f);
            列表标题.setLayoutParams(标题布局参数);
            列表标题.setText("标题");


            列表按钮 = new ImageView(项目视图.getContext());
            LinearLayout.LayoutParams 按钮布局参数 = new LinearLayout.LayoutParams(
                    屏幕类.dp转px(项目视图.getContext(), 25), 屏幕类.dp转px(项目视图.getContext(), 25));
            按钮布局参数.setMargins(屏幕类.dp转px(项目视图.getContext(), 15), 屏幕类.dp转px(项目视图.getContext(), 15),
                    屏幕类.dp转px(项目视图.getContext(), 15), 屏幕类.dp转px(项目视图.getContext(), 15));
            列表按钮.setLayoutParams(按钮布局参数);


            列表框.addView(列表图标);
            列表框.addView(列表标题);
            列表框.addView(列表按钮);
            项目视图1.addView(列表框);

        }

    }


    // 接口定义
    public interface 项目单击事件 {
        void 列表项目单击事件(int 项目序数);
    }

    public interface 项目长按事件 {
        void 列表项目长按事件(int 项目序数);
    }


}
