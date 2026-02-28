package 安卓.内容

import android.content.ClipData
import android.content.ComponentName
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.io.Serializable

//============================================================================================

fun Intent.克隆(): Any = this.clone()

//============================================================================================

fun Intent.克隆过滤器(): Intent = this.cloneFilter()

//============================================================================================

val Intent.动作: String? get() = this.action

fun Intent.取动作(): String? {
    return this.getAction()
}

//============================================================================================

val Intent.数据: Uri? get() = this.data

fun Intent.取数据(): Uri? {
    return this.getData()
}

//============================================================================================

val Intent.数据字符串: String? get() = this.dataString

fun Intent.取数据字符串(): String? {
    return this.getDataString()
}

//============================================================================================

val Intent.方案: String? get() = this.scheme

fun Intent.取方案(): String? {
    return this.getScheme()
}

//============================================================================================

val Intent.类型: String? get() = this.type

fun Intent.取类型(): String? {
    return this.getType()
}

//============================================================================================

fun Intent.解析类型(上下文: Context): String? {
    return this.resolveType(上下文)
}

fun Intent.解析类型(解析: ContentResolver): String? {
    return this.resolveType(解析)
}

//============================================================================================

fun Intent.解析类型如有需要(解析: ContentResolver): String? {
    return this.resolveTypeIfNeeded(解析)
}

//============================================================================================

val Intent.标识符: String?
    @RequiresApi(Build.VERSION_CODES.Q)
    get() = this.identifier

@RequiresApi(Build.VERSION_CODES.Q)
fun Intent.取标识符(): String? {
    return this.getIdentifier()
}

//============================================================================================

fun Intent.有类别(类别: String?): Boolean {
    return this.hasCategory(类别)
}

//============================================================================================

val Intent.类别: MutableSet<String?>? get() = this.categories


fun Intent.取类别(): MutableSet<String?>? {
    return this.getCategories()
}

//============================================================================================

val Intent.选择器: Intent? get() = this.selector

fun Intent.取选择器(): Intent? {
    return this.getSelector()
}

//============================================================================================

val Intent.剪贴板数据: ClipData? get() = this.clipData

fun Intent.取剪贴板数据(): ClipData? {
    return this.getClipData()
}

//============================================================================================

fun Intent.置额外类加载器(加载器: ClassLoader?) {
    this.setExtrasClassLoader(加载器)
}

//============================================================================================

fun Intent.有额外(名: String?): Boolean {
    return this.hasExtra(名)
}

//============================================================================================

fun Intent.有文件描述符(): Boolean {
    return this.hasFileDescriptors()
}

//============================================================================================

fun Intent.取布尔额外(名: String?, 默认值: Boolean): Boolean {
    return this.getBooleanExtra(名, 默认值)
}

//============================================================================================

fun Intent.取字节额外(名: String?, 默认值: Byte): Byte {
    return this.getByteExtra(名, 默认值)
}

//============================================================================================

fun Intent.取短额外(名: String?, 默认值: Short): Short {
    return this.getShortExtra(名, 默认值)
}

//============================================================================================

fun Intent.取字符额外(名: String?, 默认值: Char): Char {
    return this.getCharExtra(名, 默认值)
}

//============================================================================================

fun Intent.取整数额外(名: String?, 默认值: Int): Int {
    return this.getIntExtra(名, 默认值)
}

//============================================================================================

fun Intent.取长整数额外(名: String?, 默认值: Long): Long {
    return this.getLongExtra(名, 默认值)
}

//============================================================================================

fun Intent.取浮点额外(名: String?, 默认值: Float): Float {
    return this.getFloatExtra(名, 默认值)
}

//============================================================================================

fun Intent.取双精度额外(名: String?, 默认值: Double): Double {
    return this.getDoubleExtra(名, 默认值)
}

//============================================================================================

fun Intent.取字符串额外(名: String?): String? {
    return this.getStringExtra(名)
}

//============================================================================================

fun Intent.取字符序列额外(名: String?): CharSequence? {
    return this.getCharSequenceExtra(名)
}

//============================================================================================

fun <T : Parcelable?> Intent.取可包裹额外(名: String?): T? {
    return this.getParcelableExtra<T?>(名)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.TIRAMISU)
fun <T> Intent.取可包裹额外(名: String?, 类: Class<T?>): T? {
    return this.getParcelableExtra<T?>(名, 类)
}

//============================================================================================

fun Intent.取可包裹数组额外(名: String?): Array<Parcelable?>? {
    return this.getParcelableArrayExtra(名)
}

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun <T>Intent.取可包裹数组额外(名: String?, 类: Class<T?>): Array<T?>? {
    return this.getParcelableArrayExtra<T?>(名, 类)
}

//============================================================================================

fun <T : Parcelable?>Intent.取可包裹数组列表额外(名: String?): ArrayList<T?>? {
    return this.getParcelableArrayListExtra<T?>(名)
}

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun <T> Intent.取可包裹数组列表额外(名: String?, 类: Class<T?>): ArrayList<T?>? {
    return this.getParcelableArrayListExtra<T?>(名, 类)
}

//============================================================================================

fun Intent.取可序列化额外(名: String?): Serializable? {
    return this.getSerializableExtra(名)
}

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun <T : Serializable?> Intent.取可序列化额外(名: String?, 类: Class<T?>): T? {
    return this.getSerializableExtra<T?>(名, 类)
}

//============================================================================================

fun Intent.取整数数组列表额外(名: String?): ArrayList<Int?>? {
    return this.getIntegerArrayListExtra(名)
}

//============================================================================================

fun Intent.取字符串数组列表额外(名: String?): ArrayList<String?>? {
    return this.getStringArrayListExtra(名)
}

//============================================================================================

fun Intent.取字符序列数组列表额外(名: String?): ArrayList<CharSequence?>? {
    return this.getCharSequenceArrayListExtra(名)
}

//============================================================================================

fun Intent.取布尔数组额外(名: String?): BooleanArray? {
    return this.getBooleanArrayExtra(名)
}

//============================================================================================

fun Intent.取字节数组额外(名: String?): ByteArray? {
    return this.getByteArrayExtra(名)
}

//============================================================================================

fun Intent.取短数组额外(名: String?): ShortArray? {
    return this.getShortArrayExtra(名)
}

//============================================================================================

fun Intent.取字符数组额外(名: String?): CharArray? {
    return this.getCharArrayExtra(名)
}

//============================================================================================

fun Intent.取整数数组额外(名: String?): IntArray? {
    return this.getIntArrayExtra(名)
}

//============================================================================================

fun Intent.取长整数数组额外(名: String?): LongArray? {
    return this.getLongArrayExtra(名)
}

//============================================================================================

fun Intent.取浮点数组额外(名: String?): FloatArray? {
    return this.getFloatArrayExtra(名)
}

//============================================================================================

fun Intent.取双精度数组额外(名: String?): DoubleArray? {
    return this.getDoubleArrayExtra(名)
}

//============================================================================================

fun Intent.取字符串数组额外(名: String?): Array<String?>? {
    return this.getStringArrayExtra(名)
}

//============================================================================================

fun Intent.取字符序列数组额外(名: String?): Array<CharSequence?>? {
    return this.getCharSequenceArrayExtra(名)
}

//============================================================================================

fun Intent.取附加额外(名: String?): Bundle? {
    return this.getBundleExtra(名)
}

//============================================================================================

val Intent.额外: Bundle? get() = this.extras

fun Intent.取额外(): Bundle? {
    return this.getExtras()
}

//============================================================================================

val Intent.标志: Int get() = this.flags

fun Intent.取标志(): Int {
    return this.getFlags()
}

//============================================================================================

fun Intent.取包(): String? {
    return this.getPackage()
}

//============================================================================================

val Intent.组件: ComponentName? get() = this.component

fun Intent.取组件(): ComponentName? {
    return this.getComponent()
}

//============================================================================================

val Intent.源边界: Rect? get() = this.sourceBounds

fun Intent.取源边界(): Rect? {
    return this.getSourceBounds()
}

//============================================================================================

fun Intent.解析活动(包管理器: PackageManager): ComponentName? {
    return this.resolveActivity(包管理器)
}

//============================================================================================

fun Intent.解析活动信息(包管理器: PackageManager, 标志: Int): ActivityInfo? {
    return this.resolveActivityInfo(包管理器, 标志)
}

//============================================================================================

fun Intent.置动作(动作: String?): Intent {
    return this.setAction(动作)
}

//============================================================================================

fun Intent.置数据(数据: Uri?): Intent {
    return this.setData(数据)
}

//============================================================================================

fun Intent.置数据并规范化(数据: Uri): Intent {
    return setDataAndNormalize(数据)
}

//============================================================================================

fun Intent.置类型(类型: String?): Intent {
    return this.setType(类型)
}

//============================================================================================

fun Intent.置类型并规范化(类型: String?): Intent {
    return this.setTypeAndNormalize(类型)
}

//============================================================================================

fun Intent.置数据和类型(数据: Uri?, 类型: String?): Intent {
    return this.setDataAndType(数据, 类型)
}

//============================================================================================

fun Intent.置数据和类型并规范化(数据: Uri, 类型: String?): Intent {
    return this.setDataAndTypeAndNormalize(数据, 类型)
}

//============================================================================================

@RequiresApi(api = Build.VERSION_CODES.Q)
fun Intent.置标识符(标识符: String?): Intent {
    return this.setIdentifier(标识符)
}

//============================================================================================

fun Intent.添加类别(类别: String?): Intent {
    return this.addCategory(类别)
}

//============================================================================================

fun Intent.移除类别(类别: String?) {
    this.removeCategory(类别)
}

//============================================================================================

fun Intent.置选择器(选择器: Intent?) {
    this.setSelector(选择器)
}

//============================================================================================

fun Intent.置剪贴板数据(剪贴板: ClipData?) {
    this.setClipData(剪贴板)
}

//============================================================================================

fun Intent.放置额外(名: String?, 值: Boolean): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Byte): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Char): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Short): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Int): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Long): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Float): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Double): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: String?): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: CharSequence?): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Parcelable?): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: String?, 值: Array<Parcelable?>?): Intent {
    return this.putExtra(名, 值)
}

//============================================================================================

fun Intent.放置可打包数组列表额外(名: String?, 值: ArrayList<out Parcelable?>?): Intent {
    return this.putParcelableArrayListExtra(名, 值)
}

//============================================================================================

fun Intent.放置整数数组列表额外(名: String?, 值: ArrayList<Int?>?): Intent {
    return this.putIntegerArrayListExtra(名, 值)
}

//============================================================================================

fun Intent.放置字符串数组列表额外(名: String?, 值: ArrayList<String?>?): Intent {
    return this.putStringArrayListExtra(名, 值)
}

//============================================================================================

fun Intent.放置字符序列数组列表额外(名: String?, 值: ArrayList<CharSequence?>?): Intent {
    return this.putCharSequenceArrayListExtra(名, 值)
}

//============================================================================================

fun Intent.放置额外(名: String?, 值: Serializable?): Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.BooleanArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.ByteArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.ShortArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: CharArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.IntArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.LongArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.FloatArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.DoubleArray?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.Array<kotlin.String?>?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: kotlin.Array<kotlin.CharSequence?>?): android.content.Intent {
    return this.putExtra(名, 值)
}

fun Intent.放置额外(名: kotlin.String?, 值: android.os.Bundle?): android.content.Intent {
    return this.putExtra(名, 值)
}

//============================================================================================

fun Intent.放置额外(源: Intent): android.content.Intent {
    return this.putExtras(源)
}

fun Intent.放置额外(额外: Bundle): android.content.Intent {
    return this.putExtras(额外)
}

//============================================================================================

fun Intent.替换额外(源: android.content.Intent): android.content.Intent {
    return this.replaceExtras(源)
}

fun Intent.替换额外(额外: android.os.Bundle): android.content.Intent {
    return this.replaceExtras(额外)
}

//============================================================================================

fun Intent.移除额外(名: kotlin.String?) {
    this.removeExtra(名)
}

//============================================================================================

fun Intent.置标志(标志: kotlin.Int): android.content.Intent {
    return this.setFlags(标志)
}

//============================================================================================

fun Intent.添加标志(标志: kotlin.Int): android.content.Intent {
    return this.addFlags(标志)
}

//============================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.O)
fun Intent.移除标志(标志: kotlin.Int) {
    this.removeFlags(标志)
}

//============================================================================================

fun Intent.置包(包名: kotlin.String?): android.content.Intent {
    return this.setPackage(包名)
}

//============================================================================================

fun Intent.置组件(组件: ComponentName?): Intent {
    return this.setComponent(组件)
}

//============================================================================================

fun Intent.置类名(包上下文: Context, 类名: String): Intent {
    return this.setClassName(包上下文, 类名)
}

fun Intent.置类名(包名: String, 类名: String): Intent {
    return this.setClassName(包名, 类名)
}

//============================================================================================

fun Intent.置类(包上下文: Context, 类: Class<*>): Intent {
    return this.setClass(包上下文, 类)
}

//============================================================================================

fun Intent.置源边界(r: Rect?) {
    this.setSourceBounds(r)
}

//============================================================================================

fun Intent.填充在(其他: android.content.Intent, 标志: kotlin.Int): kotlin.Int {
    return this.fillIn(其他, 标志)
}
//============================================================================================

fun Intent.过滤等于(其他: android.content.Intent?): kotlin.Boolean {
    return this.filterEquals(其他)
}

//============================================================================================

fun Intent.过滤哈希码(): kotlin.Int {
    return this.filterHashCode()
}

//============================================================================================

fun Intent.到URI(): kotlin.String? {
    return toURI()
}

//============================================================================================

fun Intent.到Uri(标志: kotlin.Int): kotlin.String? {
    return this.toUri(标志)
}

//============================================================================================

fun Intent.描述内容(): Int {
    return this.describeContents()
}

//============================================================================================

@RequiresApi(api = Build.VERSION_CODES.BAKLAVA)
fun Intent.移除启动安全保护() {
    this.removeLaunchSecurityProtection()
}

//============================================================================================

fun Intent.写到包裹(out: Parcel, 标志: Int) {
    this.writeToParcel(out, 标志)
}

//============================================================================================

fun Intent.读自包裹(`in`: Parcel?) {
    this.readFromParcel(`in`)
}

//============================================================================================

val Intent.是否不匹配过滤器: Boolean
    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    get() = this.isMismatchingFilter

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Intent.是否不匹配过滤器(): Boolean {
    return this.isMismatchingFilter()
}

//============================================================================================

