package 安卓.图形;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Matrix44;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;
import android.os.Build;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorLong;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;

/**
 * 创建时间：2025年11月23日.
 * <p>
 * 描述：画布
 * @author dxyc
 */
public class 画布 extends Canvas {

    public 画布() {
        super();
    }

    public 画布(@NonNull Bitmap 位图) {
        super(位图);
    }

    //===========================================================================================

    public boolean 是否硬件加速() {
        return isHardwareAccelerated() ;
    }

    public void 置位图(Bitmap 位图) {
        this.setBitmap(位图);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 启用Z() {
        this.enableZ();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 禁用Z() {
        this.disableZ();
    }

    public boolean 是否不透明() {
        return this.isOpaque();
    }

    public int 取宽度() {
        return this.getWidth();
    }

    public int 取高度() {
        return this.getHeight();
    }

    public int 取密度() {
        return this.getDensity();
    }

    public void 置密度(int 密度) {
        this.setDensity(密度);
    }

    public int 取最大位图宽度() {
        return this.getMaximumBitmapWidth();
    }

    public int 取最大位图高度() {
        return this.getMaximumBitmapHeight();
    }

    //============================================================================================

    public static final int 所有_保存_标志 = Canvas.ALL_SAVE_FLAG;

    //============================================================================================

    public int 保存() {
        return this.save();
    }

    public int 保存图层(RectF 界限, Paint 画, int 保存标志) {
        return this.saveLayer(界限, 画, 保存标志);
    }

    public int 保存图层(RectF 界限, Paint 画) {
        return this.saveLayer(界限, 画);
    }

    public int 保存图层(float 左, float 上, float 右, float 下, Paint 画, int 保存标志) {
        return this.saveLayer(左, 上, 右, 下, 画, 保存标志);
    }

    public int 保存图层(float 左, float 上, float 右, float 下, Paint 画) {
        return this.saveLayer(左, 上, 右, 下, 画);
    }

    public int 保存图层透明度(RectF 界限, int 透明度, int 保存标志) {
        return this.saveLayerAlpha(界限, 透明度, 保存标志);
    }

    public int 保存图层透明度(RectF 界限, int 透明度) {
        return this.saveLayerAlpha(界限, 透明度);
    }

    public int 保存图层透明度(float 左, float 上, float 右, float 下, int 透明度, int 保存标志) {
        return this.saveLayerAlpha(左, 上, 右, 下, 透明度, 保存标志);
    }

    public int 保存图层透明度(float 左, float 上, float 右, float 下, int 透明度) {
        return this.saveLayerAlpha(左, 上, 右, 下, 透明度);
    }

    public void 恢复() {
        this.restore();
    }

    public int 取保存计算() {
        return this.getSaveCount();
    }

    public void 恢复到计算(int 保存计算) {
        this.restoreToCount(保存计算) ;
    }

    public void 转换(float dx, float dy) {
        this.translate(dx, dy);
    }

    public void 规模(float sx, float sy) {
        this.scale(sx, sy);
    }

    public final void 规模(float sx, float sy, float px, float py) {
        this.scale(sx, sy, px, py);
    }

    public void 旋转(float 度) {
        this.rotate(度);
    }

    public final void 旋转(float 度, float px, float py) {
        this.rotate(度, px, py);
    }

    public void 偏斜(float sx, float sy) {
        this.skew(sx, sy);
    }

    public void 连接(Matrix 矩阵) {
        this.concat(矩阵);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 连接(Matrix44 m) {
        this.concat(m);
    }

    public void 置矩阵(Matrix 矩阵) {
        this.setMatrix(矩阵);
    }

    public void 取矩阵(@NonNull Matrix ctm) {
        this.getMatrix(ctm);
    }

    public final @NonNull Matrix 取矩阵() {
        return this.getMatrix();
    }

    public boolean 裁剪矩形(@NonNull RectF 矩形, @NonNull Region.Op op) {
        return this.clipRect(矩形, op);
    }

    public boolean 裁剪矩形(@NonNull Rect 矩形, @NonNull Region.Op op) {
        return this.clipRect(矩形, op);
    }

    public boolean 裁剪矩形(@NonNull RectF 矩形) {
        return this.clipRect(矩形);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean 裁剪出矩形(@NonNull RectF 矩形) {
        return this.clipOutRect(矩形);
    }

    public boolean 裁剪矩形(@NonNull Rect 矩形) {
        return this.clipRect(矩形);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean 裁剪出矩形(@NonNull Rect 矩形) {
        return this.clipOutRect(矩形);
    }


    public boolean 裁剪矩形(float 左, float 上, float 右, float 下, @NonNull Region.Op op) {
        return this.clipRect(左, 上, 右, 下, op);
    }

    public boolean 裁剪矩形(float 左, float 上, float 右, float 下) {
        return this.clipRect(左, 上, 右, 下);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean 裁剪出矩形(float 左, float 上, float 右, float 下) {
        return this.clipOutRect(左, 上, 右, 下);
    }


    public boolean 裁剪矩形(int 左, int 上, int 右, int 下) {
        return this.clipRect(左, 上, 右, 下);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean 裁剪出矩形(int 左, int 上, int 右, int 下) {
        return this.clipOutRect(左, 上, 右, 下);
    }


    public boolean 裁剪路径(@NonNull Path 路径, @NonNull Region.Op op) {
        return this.clipPath(路径, op);
    }

    public boolean 裁剪路径(@NonNull Path 路径) {
        return this.clipPath(路径);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean 裁剪出路径(@NonNull Path 路径) {
        return this.clipOutPath(路径);
    }


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 裁剪着色器(@NonNull Shader 着色器) {
        this.clipShader(着色器);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 裁剪出着色器(@NonNull Shader 着色器) {
        this.clipOutShader(着色器);
    }

    public DrawFilter 取绘图过滤器() {
        return this.getDrawFilter();
    }

    public void 置绘图过滤器(DrawFilter 过滤器) {
        this.setDrawFilter(过滤器);
    }

    //===========================================================================================

    public static class 边缘类型  {
        public static EdgeType 黑白 = EdgeType.BW;
        public static EdgeType 抗锯齿 = EdgeType.AA;
    }

    //===========================================================================================

    public boolean 快速拒绝(@NonNull RectF 矩形, @NonNull EdgeType 类型) {
        return this.quickReject(矩形, 类型);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public boolean 快速拒绝(@NonNull RectF 矩形) {
        return this.quickReject(矩形);
    }

    public boolean 快速拒绝(@NonNull Path 路径, @NonNull EdgeType 类型) {
        return this.quickReject(路径, 类型);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public boolean 快速拒绝(@NonNull Path 路径) {
        return this.quickReject(路径);
    }

    public boolean 快速拒绝(float 左, float 上, float 右, float 下, @NonNull EdgeType 类型) {
        return this.quickReject(左, 上, 右, 下, 类型);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public boolean 快速拒绝(float 左, float 上, float 右, float 下) {
        return this.quickReject(左, 上, 右, 下);
    }

    public boolean 取裁剪边界(@NonNull Rect 界限) {
        return getClipBounds(界限);
    }

    public final @NonNull Rect 取裁剪边界() {
        return this.getClipBounds();
    }

    public void 绘制图片(@NonNull Picture 图片) {
        this.drawPicture(图片);
    }

    public void 绘制图片(@NonNull Picture 图片, @NonNull RectF dst) {
        this.drawPicture(图片, dst);
    }

    public void 绘制图片(@NonNull Picture 图片, @NonNull Rect dst) {
        this.drawPicture(图片, dst);
    }

    //===========================================================================================

    public static class 顶点模式  {
        public static VertexMode 三角形 = VertexMode.TRIANGLES;
        public static VertexMode 三角形_条带 = VertexMode.TRIANGLE_STRIP;
        public static VertexMode 三角形_扇形 = VertexMode.TRIANGLE_FAN;
    }

    //===========================================================================================

    public void 绘制弧线(@NonNull RectF 椭圆, float 起始角度, float 扫过角度, boolean 使用中心, @NonNull Paint 画) {
        this.drawArc(椭圆, 起始角度, 扫过角度, 使用中心, 画);
    }

    public void 绘制弧线(float 左, float 上, float 右, float 下, float 起始角度, float 扫过角度, boolean 使用中心, @NonNull Paint 画) {
        this.drawArc(左, 上, 右, 下, 起始角度, 扫过角度, 使用中心, 画);
    }


    public void 绘制ARGB(int 透明度, int 红, int 绿, int 蓝) {
        this.drawARGB(透明度, 红, 绿, 蓝);
    }


    public void 绘制位图(@NonNull Bitmap 位图, float 左, float 上, Paint 画) {
        this.drawBitmap(位图, 左, 上, 画);
    }

    public void 绘制位图(@NonNull Bitmap 位图, Rect 源, @NonNull RectF 目的, Paint 画) {
        this.drawBitmap(位图, 源, 目的, 画);
    }

    public void 绘制位图(@NonNull Bitmap 位图, Rect 源, @NonNull Rect 目的, Paint 画) {
        this.drawBitmap(位图, 源, 目的, 画);
    }

    public void 绘制位图(@NonNull int[] 颜色组, int 偏移, int 步长, float x, float y, int 宽度, int 高度, boolean 包含字母, Paint 画) {
        this.drawBitmap(颜色组, 偏移, 步长, x, y, 宽度, 高度, 包含字母, 画);
    }

    public void 绘制位图(@NonNull int[] 颜色组, int 偏移, int 步长, int x, int y, int 宽度, int 高度, boolean 包含字母, Paint 画) {
        this.drawBitmap(颜色组, 偏移, 步长, x, y, 宽度, 高度, 包含字母, 画);
    }

    public void 绘制位图(@NonNull Bitmap 位图, @NonNull Matrix 矩阵, Paint 画) {
        this.drawBitmap(位图, 矩阵, 画);
    }


    public void 绘制位图网格(@NonNull Bitmap 位图, int 网格宽度, int 网格高度, @NonNull float[] 垂直, int 垂直偏移, int[] 颜色组, int 颜色偏移, Paint 画) {
        this.drawBitmapMesh(位图, 网格宽度, 网格高度, 垂直, 垂直偏移, 颜色组, 颜色偏移, 画);
    }

    public void 绘制圆(float cx, float cy, float 半径, @NonNull Paint 画) {
        this.drawCircle(cx, cy, 半径, 画);
    }


    public void 绘制颜色(@ColorInt int 颜色) {
        this.drawColor(颜色);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 绘制颜色(@ColorLong long 颜色) {
        this.drawColor(颜色);
    }

    public void 绘制颜色(@ColorInt int 颜色, @NonNull PorterDuff.Mode 模式) {
        this.drawColor(颜色, 模式);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 绘制颜色(@ColorInt int 颜色, @NonNull BlendMode 模式) {
        this.drawColor(颜色, 模式);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 绘制颜色(@ColorLong long 颜色, @NonNull BlendMode 模式) {
        this.drawColor(颜色, 模式);
    }


    public void 绘制线(float 开始X, float 开始Y, float 停止X, float 停止Y, @NonNull Paint 画) {
        this.drawLine(开始X, 开始Y, 停止X, 停止Y, 画);
    }

    public void 绘制线组(@Size(multiple = 4) @NonNull float[] 分数, int 偏移, int 计算, @NonNull Paint 画) {
        this.drawLines(分数, 偏移, 计算, 画);
    }

    public void 绘制线组(@Size(multiple = 4) @NonNull float[] 分数, @NonNull Paint 画) {
        this.drawLines(分数, 画);
    }

    public void 绘制椭圆(@NonNull RectF 椭圆, @NonNull Paint 画) {
        this.drawOval(椭圆, 画);
    }

    public void 绘制椭圆(float 左, float 上, float 右, float 下, @NonNull Paint 画) {
        this.drawOval(左, 上, 右, 下, 画);
    }

    public void 绘制画(@NonNull Paint 画) {
        this.drawPaint(画);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void 绘制九宫格图(@NonNull NinePatch 九宫格图, @NonNull Rect 目的, Paint 画) {
        this.drawPatch(九宫格图, 目的, 画);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void 绘制九宫格图(@NonNull NinePatch 九宫格图, @NonNull RectF 目的, Paint 画) {
        this.drawPatch(九宫格图, 目的, 画);
    }

    public void 绘制路径(@NonNull Path 路径, @NonNull Paint 画) {
        this.drawPath(路径, 画);
    }

    @RequiresApi(api = Build.VERSION_CODES.BAKLAVA)
    public void 绘制区域(@NonNull Region 区域, @NonNull Paint 画) {
        this.drawRegion(区域, 画);
    }

    public void 绘制点(float x, float y, @NonNull Paint 画) {
        this.drawPoint(x, y, 画);
    }

    public void 绘制点组(@Size(multiple = 2) float[] 分数, int 偏移, int 计算, @NonNull Paint 画) {
        this.drawPoints(分数, 偏移, 计算, 画);
    }

    public void 绘制点组(@Size(multiple = 2) @NonNull float[] 分数, @NonNull Paint 画) {
        this.drawPoints(分数, 画);
    }

    public void 绘制位置文本(@NonNull char[] 文本, int 索引, int 计算, @NonNull @Size(multiple = 2) float[] 位置, @NonNull Paint 画) {
        this.drawPosText(文本, 索引, 计算, 位置, 画);
    }

    public void 绘制位置文本(@NonNull String 文本, @NonNull @Size(multiple = 2) float[] 位置, @NonNull Paint 画) {
        this.drawPosText(文本, 位置, 画);
    }


    public void 绘制矩形(@NonNull RectF 矩形, @NonNull Paint 画) {
        this.drawRect(矩形, 画);
    }

    public void 绘制矩形(@NonNull Rect 矩形, @NonNull Paint 画) {
        this.drawRect(矩形, 画);
    }

    public void 绘制矩形(float 左, float 上, float 右, float 下, @NonNull Paint 画) {
        this.drawRect(左, 上, 右, 下, 画);
    }


    public void 绘制RGB(int 红, int 绿, int 蓝) {
        this.drawRGB(红, 绿, 蓝);
    }


    public void 绘制圆角矩形(@NonNull RectF 矩形, float rx, float ry, @NonNull Paint 画) {
        this.drawRoundRect(矩形, rx, ry, 画);
    }

    public void 绘制圆角矩形(float 左, float 上, float 右, float 下, float rx, float ry, @NonNull Paint 画) {
        this.drawRoundRect(左, 上, 右, 下, rx, ry, 画);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 绘制双圆角矩形(@NonNull RectF 外部, float 外部Rx, float 外部Ry, @NonNull RectF 内部, float 内部Rx, float 内部Ry, @NonNull Paint 画) {
        this.drawDoubleRoundRect(外部, 外部Rx, 外部Ry, 内部, 内部Rx, 内部Ry, 画);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 绘制双圆角矩形(@NonNull RectF 外部, @NonNull float[] 外半径, @NonNull RectF 内部, @NonNull float[] 内半径, @NonNull Paint 画) {
        this.drawDoubleRoundRect(外部, 外半径, 内部, 内半径, 画);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void 绘制字形(@NonNull int[] 字形Id组, @IntRange(from = 0) int 字形Id偏移, @NonNull float[] 位置组,
                         @IntRange(from = 0) int 位置偏移, @IntRange(from = 0) int 字形计数, @NonNull Font 字体, @NonNull Paint 画) {
        this.drawGlyphs(字形Id组, 字形Id偏移, 位置组, 位置偏移, 字形计数, 字体,画);
    }



    public void 绘制文本(@NonNull char[] 文本, int 索引, int 计算, float x, float y, @NonNull Paint 画) {
        this.drawText(文本, 索引, 计算, x, y, 画);
    }

    public void 绘制文本(@NonNull String 文本, float x, float y, @NonNull Paint 画) {
        this.drawText(文本, x, y, 画);
    }

    public void 绘制文本(@NonNull String 文本, int 开始, int 结束, float x, float y, @NonNull Paint 画) {
        this.drawText(文本, 开始, 结束, x, y, 画);
    }

    public void 绘制文本(@NonNull CharSequence 文本, int 开始, int 结束, float x, float y, @NonNull Paint 画) {
        this.drawText(文本, 开始, 结束, x, y, 画);
    }


    public void 绘制文本在路径上(@NonNull char[] 文本, int 索引, int 计算, @NonNull Path 路径, float 水平偏移, float 垂直偏移, @NonNull Paint 画) {
        this.drawTextOnPath(文本, 索引, 计算, 路径, 水平偏移, 垂直偏移, 画);
    }

    public void 绘制文本在路径上(@NonNull String 文本, @NonNull Path 路径, float 水平偏移, float 垂直偏移, @NonNull Paint 画) {
        this.drawTextOnPath(文本, 路径, 水平偏移, 垂直偏移, 画);
    }


    public void 绘制文本运行(@NonNull char[] 文本, int 索引, int 计算, int 上下文索引, int 上下文计算, float x, float y, boolean 是否从右到左, @NonNull Paint 画) {
        this.drawTextRun(文本, 索引, 计算, 上下文索引, 上下文计算, x, y, 是否从右到左, 画);
    }

    public void 绘制文本运行(@NonNull CharSequence 文本, int 开始, int 结束, int 上下文开始, int 上下文结束, float x, float y, boolean 是否从右到左, @NonNull Paint 画) {
        this.drawTextRun(文本, 开始, 结束, 上下文开始, 上下文结束, x, y, 是否从右到左, 画);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 绘制文本运行(@NonNull MeasuredText 文本, int 开始, int 结束, int 上下文开始, int 上下文结束, float x, float y, boolean 是否从右到左, @NonNull Paint 画) {
        this.drawTextRun(文本, 开始, 结束, 上下文开始, 上下文结束, x, y, 是否从右到左, 画);
    }


    public void 绘制顶点(@NonNull VertexMode 模式, int 顶点计数, @NonNull float[] 垂直组, int 垂直偏移, float[] 纹理组, int 纹理偏移, int[] 颜色组,
                             int 颜色偏移, short[] 索引组, int 索引偏移, int 索引计数, @NonNull Paint 画) {
        this.drawVertices(模式, 顶点计数, 垂直组, 垂直偏移, 纹理组, 纹理偏移, 颜色组, 颜色偏移, 索引组, 索引偏移, 索引计数, 画);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 绘制渲染节点(@NonNull RenderNode 渲染节点) {
        this.drawRenderNode(渲染节点);
    }

}
