package 安卓.图形

import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Canvas.VertexMode
import android.graphics.DrawFilter
import android.graphics.Matrix
import android.graphics.Matrix44
import android.graphics.NinePatch
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Picture
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Region
import android.graphics.RenderNode
import android.graphics.Shader
import android.graphics.text.MeasuredText
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.ColorLong
import androidx.annotation.RequiresApi

//===========================================================================================

val Canvas.是否硬件加速: Boolean get() = isHardwareAccelerated

fun Canvas.是否硬件加速(): Boolean {
    return isHardwareAccelerated()
}

//===========================================================================================

fun Canvas.置位图(位图: Bitmap?) {
    this.setBitmap(位图)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.Q)
fun Canvas.启用Z() {
    this.enableZ()
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.Q)
fun Canvas.禁用Z() {
    this.disableZ()
}

//===========================================================================================

val Canvas.是否不透明: Boolean get() = this.isOpaque

fun Canvas.是否不透明(): Boolean {
    return this.isOpaque()
}

//===========================================================================================

val Canvas.宽度: Int get() = this.width

fun Canvas.取宽度(): Int {
    return this.getWidth()
}

//===========================================================================================

val Canvas.高度: Int get() = this.height

fun Canvas.取高度(): Int {
    return this.getHeight()
}

//===========================================================================================

val Canvas.密度: Int get() = this.density

fun Canvas.取密度(): Int {
    return this.getDensity()
}

//===========================================================================================

fun Canvas.置密度(密度: Int) {
    this.setDensity(密度)
}

//===========================================================================================

val Canvas.最大位图宽度: Int get() = this.maximumBitmapWidth

fun Canvas.取最大位图宽度(): Int {
    return this.getMaximumBitmapWidth()
}

//===========================================================================================

val Canvas.最大位图高度: Int get() = this.maximumBitmapHeight

fun Canvas.取最大位图高度(): Int {
    return this.getMaximumBitmapHeight()
}

//===========================================================================================

fun Canvas.保存(): Int {
    return this.save()
}

//===========================================================================================

fun Canvas.保存图层(界限: RectF?, 画: Paint?, 保存标志: Int): Int {
    return this.saveLayer(界限, 画, 保存标志)
}

fun Canvas.保存图层(界限: RectF?, 画: Paint?):Int {
    return this.saveLayer(界限, 画)
}

fun Canvas.保存图层(左: Float, 上: Float, 右: Float, 下: Float, 画: Paint?, 保存标志: Int): Int {
    return this.saveLayer(左, 上, 右, 下, 画, 保存标志)
}

fun Canvas.保存图层(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float, 画: Paint?): kotlin.Int {
    return this.saveLayer(左, 上, 右, 下, 画)
}

//===========================================================================================

fun Canvas.保存图层透明度(界限: RectF?, 透明度: kotlin.Int, 保存标志: kotlin.Int): kotlin.Int {
    return this.saveLayerAlpha(界限, 透明度, 保存标志)
}

fun Canvas.保存图层透明度(界限: RectF?, 透明度: kotlin.Int): kotlin.Int {
    return this.saveLayerAlpha(界限, 透明度)
}

fun Canvas.保存图层透明度(左: Float, 上: Float, 右: kotlin.Float, 下: kotlin.Float, 透明度: kotlin.Int, 保存标志: kotlin.Int): kotlin.Int {
    return this.saveLayerAlpha(左, 上, 右, 下, 透明度, 保存标志)
}

fun Canvas.保存图层透明度(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float, 透明度: kotlin.Int): kotlin.Int {
    return this.saveLayerAlpha(左, 上, 右, 下, 透明度)
}

//===========================================================================================

fun Canvas.恢复() {
    this.restore()
}

//===========================================================================================

val Canvas.保存计算: Int get() = this.saveCount

fun Canvas.取保存计算(): Int {
    return this.getSaveCount()
}

//===========================================================================================

fun Canvas.恢复到计算(保存计算: kotlin.Int) {
    this.restoreToCount(保存计算)
}

//===========================================================================================

fun Canvas.转换(dx: kotlin.Float, dy: kotlin.Float) {
    this.translate(dx, dy)
}

//===========================================================================================

fun Canvas.规模(sx: kotlin.Float, sy: kotlin.Float) {
    this.scale(sx, sy)
}

fun Canvas.规模(sx: kotlin.Float, sy: kotlin.Float, px: kotlin.Float, py: kotlin.Float) {
    this.scale(sx, sy, px, py)
}

//===========================================================================================

fun Canvas.旋转(度: kotlin.Float) {
    this.rotate(度)
}

fun Canvas.旋转(度: kotlin.Float, px: kotlin.Float, py: kotlin.Float) {
    this.rotate(度, px, py)
}

//===========================================================================================

fun Canvas.偏斜(sx: kotlin.Float, sy: kotlin.Float) {
    this.skew(sx, sy)
}

//===========================================================================================

fun Canvas.连接(矩阵: Matrix?) {
    this.concat(矩阵)
}

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Canvas.连接(m: Matrix44?) {
    this.concat(m)
}

//===========================================================================================

fun Canvas.置矩阵(矩阵: Matrix?) {
    this.setMatrix(矩阵)
}

//===========================================================================================

fun Canvas.取矩阵(ctm: Matrix) {
    this.getMatrix(ctm)
}

fun Canvas.取矩阵(): Matrix {
    return this.getMatrix()
}

//===========================================================================================

fun Canvas.裁剪矩形(矩形: RectF, op: Region.Op): kotlin.Boolean {
    return this.clipRect(矩形, op)
}

fun Canvas.裁剪矩形(矩形: Rect, op: Region.Op): kotlin.Boolean {
    return this.clipRect(矩形, op)
}

fun Canvas.裁剪矩形(矩形: RectF): kotlin.Boolean {
    return this.clipRect(矩形)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.O)
fun Canvas.裁剪出矩形(矩形: RectF): kotlin.Boolean {
    return this.clipOutRect(矩形)
}

//===========================================================================================

fun Canvas.裁剪矩形(矩形: android.graphics.Rect): kotlin.Boolean {
    return this.clipRect(矩形)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.O)
fun Canvas.裁剪出矩形(矩形: android.graphics.Rect): kotlin.Boolean {
    return this.clipOutRect(矩形)
}

//===========================================================================================

fun Canvas.裁剪矩形(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float, op: android.graphics.Region.Op): kotlin.Boolean {
    return this.clipRect(左, 上, 右, 下, op)
}

fun Canvas.裁剪矩形(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float): kotlin.Boolean {
    return this.clipRect(左, 上, 右, 下)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.O)
fun Canvas.裁剪出矩形(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float): kotlin.Boolean {
    return this.clipOutRect(左, 上, 右, 下)
}

//===========================================================================================

fun Canvas.裁剪矩形(左: Int, 上: Int, 右: Int, 下: Int): kotlin.Boolean {
    return this.clipRect(左, 上, 右, 下)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.O)
fun Canvas.裁剪出矩形(左: Int, 上: Int, 右: Int, 下: Int): kotlin.Boolean {
    return this.clipOutRect(左, 上, 右, 下)
}

//===========================================================================================

fun Canvas.裁剪路径(路径: Path, op: Region.Op): kotlin.Boolean {
    return this.clipPath(路径, op)
}

fun Canvas.裁剪路径(路径: Path): kotlin.Boolean {
    return this.clipPath(路径)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.O)
fun Canvas.裁剪出路径(路径: android.graphics.Path): kotlin.Boolean {
    return this.clipOutPath(路径)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Canvas.裁剪着色器(着色器: Shader) {
    this.clipShader(着色器)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Canvas.裁剪出着色器(着色器: Shader) {
    this.clipOutShader(着色器)
}

//===========================================================================================

val Canvas.绘图过滤器: DrawFilter? get() = this.drawFilter

fun Canvas.取绘图过滤器(): DrawFilter? {
    return this.getDrawFilter()
}

//===========================================================================================

fun Canvas.置绘图过滤器(过滤器: DrawFilter?) {
    this.setDrawFilter(过滤器)
}

//===========================================================================================

//===========================================================================================
fun Canvas.快速拒绝(矩形: RectF, 类型: android.graphics.Canvas.EdgeType): kotlin.Boolean {
    return this.quickReject(矩形, 类型)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.R)
fun Canvas.快速拒绝(矩形: RectF): kotlin.Boolean {
    return this.quickReject(矩形)
}

fun Canvas.快速拒绝(路径: Path, 类型: Canvas.EdgeType): kotlin.Boolean {
    return this.quickReject(路径, 类型)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.R)
fun Canvas.快速拒绝(路径: Path): kotlin.Boolean {
    return this.quickReject(路径)
}

fun Canvas.快速拒绝(左: kotlin.Float, 上: kotlin.Float, 右: Float, 下: kotlin.Float, 类型: Canvas.EdgeType): kotlin.Boolean {
    return this.quickReject(左, 上, 右, 下, 类型)
}

@RequiresApi(api = Build.VERSION_CODES.R)
fun Canvas.快速拒绝(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float): kotlin.Boolean {
    return this.quickReject(左, 上, 右, 下)
}

//===========================================================================================

fun Canvas.取裁剪边界(界限: Rect): kotlin.Boolean {
    return getClipBounds(界限)
}

fun Canvas.取裁剪边界(): Rect {
    return this.getClipBounds()
}

//===========================================================================================

fun Canvas.绘制图片(图片: Picture) {
    this.drawPicture(图片)
}

fun Canvas.绘制图片(图片: Picture, dst: RectF) {
    this.drawPicture(图片, dst)
}

fun Canvas.绘制图片(图片: Picture, dst: Rect) {
    this.drawPicture(图片, dst)
}

//===========================================================================================

fun Canvas.绘制弧线(椭圆: RectF, 起始角度: kotlin.Float, 扫过角度: kotlin.Float, 使用中心: kotlin.Boolean, 画: Paint) {
    this.drawArc(椭圆, 起始角度, 扫过角度, 使用中心, 画)
}

fun Canvas.绘制弧线(左: Float, 上: Float, 右: Float, 下: Float, 起始角度: Float, 扫过角度: Float, 使用中心: Boolean, 画: Paint) {
    this.drawArc(左, 上, 右, 下, 起始角度, 扫过角度, 使用中心, 画)
}

//===========================================================================================

fun Canvas.绘制ARGB(透明度: kotlin.Int, 红: kotlin.Int, 绿: kotlin.Int, 蓝: kotlin.Int) {
    this.drawARGB(透明度, 红, 绿, 蓝)
}

//===========================================================================================

fun Canvas.绘制位图(位图: android.graphics.Bitmap, 左: kotlin.Float, 上: kotlin.Float, 画: Paint?) {
    this.drawBitmap(位图, 左, 上, 画)
}

fun Canvas.绘制位图(位图: Bitmap, 源: Rect?, 目的: RectF, 画: Paint?) {
    this.drawBitmap(位图, 源, 目的, 画)
}

fun Canvas.绘制位图(位图: Bitmap, 源: Rect?, 目的: Rect, 画: Paint?) {
    this.drawBitmap(位图, 源, 目的, 画)
}

fun Canvas.绘制位图(颜色组: IntArray, 偏移: Int, 步长: Int, x: Float, y: Float, 宽度: Int, 高度: Int, 包含字母: Boolean, 画: Paint?) {
    this.drawBitmap(颜色组, 偏移, 步长, x, y, 宽度, 高度, 包含字母, 画)
}

fun Canvas.绘制位图(颜色组: IntArray, 偏移: Int, 步长: Int, x: Int, y: Int, 宽度: Int, 高度: Int, 包含字母: Boolean, 画: Paint?) {
    this.drawBitmap(颜色组, 偏移, 步长, x, y, 宽度, 高度, 包含字母, 画)
}

fun Canvas.绘制位图(位图: Bitmap, 矩阵: Matrix, 画: Paint?) {
    this.drawBitmap(位图, 矩阵, 画)
}

//===========================================================================================

fun Canvas.绘制位图网格(位图: Bitmap, 网格宽度: Int, 网格高度: Int, 垂直: FloatArray, 垂直偏移: Int, 颜色组: IntArray?, 颜色偏移: Int, 画: Paint?) {
    this.drawBitmapMesh(位图, 网格宽度, 网格高度, 垂直, 垂直偏移, 颜色组, 颜色偏移, 画)
}

//===========================================================================================

fun Canvas.绘制圆(cx: Float, cy: Float, 半径: Float, 画: Paint) {
    this.drawCircle(cx, cy, 半径, 画)
}

//===========================================================================================

fun Canvas.绘制颜色(@ColorInt 颜色: kotlin.Int) {
    this.drawColor(颜色)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.Q)
fun Canvas.绘制颜色(@ColorLong 颜色: kotlin.Long) {
    this.drawColor(颜色)
}

fun Canvas.绘制颜色(@ColorInt 颜色: kotlin.Int, 模式: PorterDuff.Mode) {
    this.drawColor(颜色, 模式)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.Q)
fun Canvas.绘制颜色(@ColorInt 颜色: kotlin.Int, 模式: BlendMode) {
    this.drawColor(颜色, 模式)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.Q)
fun Canvas.绘制颜色(@ColorLong 颜色: kotlin.Long, 模式: BlendMode) {
    this.drawColor(颜色, 模式)
}

//===========================================================================================

fun Canvas.绘制线(开始X: kotlin.Float, 开始Y: kotlin.Float, 停止X: kotlin.Float, 停止Y: kotlin.Float, 画: android.graphics.Paint) {
    this.drawLine(开始X, 开始Y, 停止X, 停止Y, 画)
}

//===========================================================================================

fun Canvas.绘制线组(@androidx.annotation.Size(multiple = 4) 分数: kotlin.FloatArray, 偏移: kotlin.Int, 计算: kotlin.Int, 画: Paint) {
    this.drawLines(分数, 偏移, 计算, 画)
}

fun Canvas.绘制线组(@androidx.annotation.Size(multiple = 4) 分数: kotlin.FloatArray, 画: Paint) {
    this.drawLines(分数, 画)
}

//===========================================================================================

fun Canvas.绘制椭圆(椭圆: RectF, 画: android.graphics.Paint) {
    this.drawOval(椭圆, 画)
}

fun Canvas.绘制椭圆(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float, 画: android.graphics.Paint) {
    this.drawOval(左, 上, 右, 下, 画)
}

//===========================================================================================

fun Canvas.绘制画(画: Paint) {
    this.drawPaint(画)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.S)
fun Canvas.绘制九宫格图(九宫格图: NinePatch, 目的: android.graphics.Rect, 画: android.graphics.Paint?) {
    this.drawPatch(九宫格图, 目的, 画)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.S)
fun Canvas.绘制九宫格图(九宫格图: NinePatch, 目的: RectF, 画: android.graphics.Paint?) {
    this.drawPatch(九宫格图, 目的, 画)
}

//===========================================================================================

fun Canvas.绘制路径(路径: android.graphics.Path, 画: android.graphics.Paint) {
    this.drawPath(路径, 画)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.BAKLAVA)
fun Canvas.绘制区域(区域: android.graphics.Region, 画: android.graphics.Paint) {
    this.drawRegion(区域, 画)
}

//===========================================================================================

fun Canvas.绘制点(x: kotlin.Float, y: kotlin.Float, 画: android.graphics.Paint) {
    this.drawPoint(x, y, 画)
}

//===========================================================================================

fun Canvas.绘制点组(@androidx.annotation.Size(multiple = 2) 分数: kotlin.FloatArray?, 偏移: kotlin.Int, 计算: kotlin.Int, 画: Paint) {
    this.drawPoints(分数, 偏移, 计算, 画)
}

fun Canvas.绘制点组(@androidx.annotation.Size(multiple = 2) 分数: kotlin.FloatArray, 画: Paint) {
    this.drawPoints(分数, 画)
}

//===========================================================================================

fun Canvas.绘制位置文本(文本: CharArray, 索引: kotlin.Int, 计算: Int, @androidx.annotation.Size(multiple = 2) 位置: kotlin.FloatArray, 画: Paint) {
    this.drawPosText(文本, 索引, 计算, 位置, 画)
}

fun Canvas.绘制位置文本(文本: kotlin.String, @androidx.annotation.Size(multiple = 2) 位置: kotlin.FloatArray, 画: Paint) {
    this.drawPosText(文本, 位置, 画)
}

//===========================================================================================

fun Canvas.绘制矩形(矩形: RectF, 画: android.graphics.Paint) {
    this.drawRect(矩形, 画)
}

fun Canvas.绘制矩形(矩形: android.graphics.Rect, 画: android.graphics.Paint) {
    this.drawRect(矩形, 画)
}

fun Canvas.绘制矩形(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float, 画: android.graphics.Paint) {
    this.drawRect(左, 上, 右, 下, 画)
}

//===========================================================================================

fun Canvas.绘制RGB(红: kotlin.Int, 绿: kotlin.Int, 蓝: kotlin.Int) {
    this.drawRGB(红, 绿, 蓝)
}

//===========================================================================================

fun Canvas.绘制圆角矩形(矩形: RectF, rx: kotlin.Float, ry: kotlin.Float, 画: Paint) {
    this.drawRoundRect(矩形, rx, ry, 画)
}

fun Canvas.绘制圆角矩形(左: kotlin.Float, 上: kotlin.Float, 右: kotlin.Float, 下: kotlin.Float, rx: kotlin.Float, ry: kotlin.Float, 画: Paint) {
    this.drawRoundRect(左, 上, 右, 下, rx, ry, 画)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.Q)
fun Canvas.绘制双圆角矩形(外部: RectF, 外部Rx: kotlin.Float, 外部Ry: Float, 内部: RectF, 内部Rx: kotlin.Float, 内部Ry: kotlin.Float, 画: Paint) {
    this.drawDoubleRoundRect(外部, 外部Rx, 外部Ry, 内部, 内部Rx, 内部Ry, 画)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.Q)
fun Canvas.绘制双圆角矩形(外部: RectF, 外半径: kotlin.FloatArray, 内部: RectF, 内半径: kotlin.FloatArray, 画: Paint) {
    this.drawDoubleRoundRect(外部, 外半径, 内部, 内半径, 画)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.S)
fun Canvas.绘制字形(字形Id组: kotlin.IntArray, @androidx.annotation.IntRange(from = 0) 字形Id偏移: kotlin.Int, 位置组: kotlin.FloatArray,
                                                                  @androidx.annotation.IntRange(from = 0) 位置偏移: kotlin.Int, @androidx.annotation.IntRange(from = 0) 字形计数: kotlin.Int, 字体: android.graphics.fonts.Font, 画: android.graphics.Paint) {
    this.drawGlyphs(字形Id组, 字形Id偏移, 位置组, 位置偏移, 字形计数, 字体, 画)
}

//===========================================================================================

fun Canvas.绘制文本(文本: CharArray, 索引: kotlin.Int, 计算: kotlin.Int, x: kotlin.Float, y: kotlin.Float, 画: android.graphics.Paint) {
    this.drawText(文本, 索引, 计算, x, y, 画)
}

fun Canvas.绘制文本(文本: kotlin.String, x: kotlin.Float, y: kotlin.Float, 画: android.graphics.Paint) {
    this.drawText(文本, x, y, 画)
}

fun Canvas.绘制文本(文本: kotlin.String, 开始: kotlin.Int, 结束: kotlin.Int, x: kotlin.Float, y: kotlin.Float, 画: android.graphics.Paint) {
    this.drawText(文本, 开始, 结束, x, y, 画)
}

fun Canvas.绘制文本(文本: kotlin.CharSequence, 开始: kotlin.Int, 结束: kotlin.Int, x: kotlin.Float, y: kotlin.Float, 画: android.graphics.Paint) {
    this.drawText(文本, 开始, 结束, x, y, 画)
}

//===========================================================================================

fun Canvas.绘制文本在路径上(文本: CharArray, 索引: kotlin.Int, 计算: kotlin.Int, 路径: android.graphics.Path, 水平偏移: kotlin.Float, 垂直偏移: kotlin.Float, 画: android.graphics.Paint) {
    this.drawTextOnPath(文本, 索引, 计算, 路径, 水平偏移, 垂直偏移, 画)
}

fun Canvas.绘制文本在路径上(文本: kotlin.String, 路径: android.graphics.Path, 水平偏移: kotlin.Float, 垂直偏移: kotlin.Float, 画: android.graphics.Paint) {
    this.drawTextOnPath(文本, 路径, 水平偏移, 垂直偏移, 画)
}

//===========================================================================================

fun Canvas.绘制文本运行(文本: CharArray, 索引: kotlin.Int, 计算: kotlin.Int, 上下文索引: kotlin.Int, 上下文计算: kotlin.Int, x: kotlin.Float, y: kotlin.Float, 是否从右到左: kotlin.Boolean, 画: android.graphics.Paint) {
    this.drawTextRun(文本, 索引, 计算, 上下文索引, 上下文计算, x, y, 是否从右到左, 画)
}

fun Canvas.绘制文本运行(文本: kotlin.CharSequence, 开始: kotlin.Int, 结束: kotlin.Int, 上下文开始: kotlin.Int, 上下文结束: kotlin.Int, x: kotlin.Float, y: kotlin.Float, 是否从右到左: kotlin.Boolean, 画: android.graphics.Paint) {
    this.drawTextRun(文本, 开始, 结束, 上下文开始, 上下文结束, x, y, 是否从右到左, 画)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.Q)
fun Canvas.绘制文本运行(文本: MeasuredText, 开始: kotlin.Int, 结束: kotlin.Int, 上下文开始: kotlin.Int, 上下文结束: kotlin.Int, x: kotlin.Float, y: kotlin.Float, 是否从右到左: kotlin.Boolean, 画: android.graphics.Paint) {
    this.drawTextRun(文本, 开始, 结束, 上下文开始, 上下文结束, x, y, 是否从右到左, 画)
}

//===========================================================================================

fun Canvas.绘制顶点(模式: VertexMode, 顶点计数: kotlin.Int, 垂直组: kotlin.FloatArray, 垂直偏移: kotlin.Int, 纹理组: kotlin.FloatArray?, 纹理偏移: kotlin.Int, 颜色组: kotlin.IntArray?,
             颜色偏移: kotlin.Int, 索引组: kotlin.ShortArray?, 索引偏移: kotlin.Int, 索引计数: kotlin.Int, 画: android.graphics.Paint) {
    this.drawVertices(模式, 顶点计数, 垂直组, 垂直偏移, 纹理组, 纹理偏移, 颜色组, 颜色偏移, 索引组, 索引偏移, 索引计数, 画)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.Q)
fun Canvas.绘制渲染节点(渲染节点: RenderNode) {
    this.drawRenderNode(渲染节点)
}

//===========================================================================================


