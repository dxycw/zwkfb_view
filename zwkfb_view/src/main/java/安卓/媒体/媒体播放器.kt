package 安卓.媒体

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioDeviceInfo
import android.media.AudioRouting
import android.media.MediaDataSource
import android.media.MediaDrm
import android.media.MediaPlayer
import android.media.MediaPlayer.NoDrmSchemeException
import android.media.MediaPlayer.OnDrmConfigHelper
import android.media.MediaPlayer.OnDrmInfoListener
import android.media.MediaPlayer.OnDrmPreparedListener
import android.media.MediaPlayer.OnMediaTimeDiscontinuityListener
import android.media.MediaPlayer.OnSubtitleDataListener
import android.media.MediaPlayer.OnTimedMetaDataAvailableListener
import android.media.MediaPlayer.ProvisioningNetworkErrorException
import android.media.MediaPlayer.ProvisioningServerErrorException
import android.media.MediaTimestamp
import android.media.PlaybackParams
import android.media.SyncParams
import android.media.VolumeShaper
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.PersistableBundle
import android.view.Surface
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import java.io.FileDescriptor
import java.net.HttpCookie
import java.util.UUID

//===========================================================================================

fun MediaPlayer.置显示屏(sh: SurfaceHolder?) {
    this.setDisplay(sh)
}

//===========================================================================================

fun MediaPlayer.置表面(表面: Surface?) {
    this.setSurface(表面)
}

//===========================================================================================

fun MediaPlayer.置视频缩放模式(模式: kotlin.Int) {
    this.setVideoScalingMode(模式)
}

//===========================================================================================

fun MediaPlayer.置数据源(上下文: Context, 网址: Uri) {
    this.setDataSource(上下文, 网址)
}

@RequiresApi(Build.VERSION_CODES.O)
fun MediaPlayer.置数据源(上下文: Context, 网址: Uri, 头部: MutableMap<String?, String?>?, cookies: MutableList<HttpCookie?>?) {
    this.setDataSource(上下文, 网址, 头部, cookies)
}

fun MediaPlayer.置数据源(上下文: Context, 网址: Uri, 头部: MutableMap<String?, String?>?) {
    this.setDataSource(上下文, 网址, 头部)
}

fun MediaPlayer.置数据源(路径: String?) {
    this.setDataSource(路径)
}

fun MediaPlayer.置数据源(资源文件: AssetFileDescriptor) {
    this.setDataSource(资源文件)
}

fun MediaPlayer.置数据源(文件: FileDescriptor?) {
    this.setDataSource(文件)
}


fun MediaPlayer.置数据源(文件: FileDescriptor?, 偏移: Long, 长度: Long) {
    this.setDataSource(文件, 偏移, 长度)
}

fun MediaPlayer.置数据源(数据源: MediaDataSource?) {
    this.setDataSource(数据源)
}

//===========================================================================================

fun MediaPlayer.准备() {
    this.prepare()
}

//===========================================================================================

fun MediaPlayer.准备异步() {
    this.prepareAsync()
}

//===========================================================================================

fun MediaPlayer.开始() {
    this.start()
}

//===========================================================================================

fun MediaPlayer.停止() {
    this.stop()
}

//===========================================================================================

fun MediaPlayer.暂停() {
    this.pause()
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.O)
fun MediaPlayer.创建音量调节器(配置: VolumeShaper.Configuration): VolumeShaper {
    return this.createVolumeShaper(配置)
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.置首选设备(设备信息: AudioDeviceInfo?): kotlin.Boolean {
    return this.setPreferredDevice(设备信息)
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.取首选设备(): AudioDeviceInfo? {
    return this.getPreferredDevice()
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.取路由设备(): AudioDeviceInfo? {
    return this.getRoutedDevice()
}

//===========================================================================================


@RequiresApi(Build.VERSION_CODES.BAKLAVA)
fun MediaPlayer.取路由设备组(): MutableList<AudioDeviceInfo?> {
    return this.getRoutedDevices()
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.添加路由改变监听器(监听器: AudioRouting.OnRoutingChangedListener?, 处理器: android.os.Handler?) {
    this.addOnRoutingChangedListener(监听器, 处理器)
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.移除路由改变监听器(监听器: AudioRouting.OnRoutingChangedListener?) {
    this.removeOnRoutingChangedListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置唤醒模式(上下文: Context?, 模式: kotlin.Int) {
    this.setWakeMode(上下文, 模式)
}

//===========================================================================================

fun MediaPlayer.置屏幕常亮播放时(屏幕常亮: kotlin.Boolean) {
    this.setScreenOnWhilePlaying(屏幕常亮)
}


//===========================================================================================

fun MediaPlayer.取视频宽度(): kotlin.Int {
    return this.getVideoWidth()
}

//===========================================================================================

fun MediaPlayer.取视频高度(): kotlin.Int {
    return this.getVideoHeight()
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.O)
fun MediaPlayer.取指标(): PersistableBundle? {
    return this.getMetrics()
}

//===========================================================================================

fun MediaPlayer.是否播放(): kotlin.Boolean {
    return this.isPlaying()
}

//===========================================================================================

fun MediaPlayer.置播放参数(参数: PlaybackParams) {
    this.setPlaybackParams(参数)
}


//===========================================================================================

fun MediaPlayer.取播放参数(): PlaybackParams {
    return this.getPlaybackParams()
}

//===========================================================================================

fun MediaPlayer.置同步参数(参数: SyncParams) {
    this.setSyncParams(参数)
}

//===========================================================================================

fun MediaPlayer.取同步参数(): SyncParams {
    return this.getSyncParams()
}


//===========================================================================================

@RequiresApi(Build.VERSION_CODES.O)
fun MediaPlayer.跳转到(毫秒: Long, 模式: Int) {
    this.seekTo(毫秒, 模式)
}

fun MediaPlayer.跳转到(毫秒: Int) {
    this.seekTo(毫秒)
}

//===========================================================================================

fun MediaPlayer.取时间戳(): MediaTimestamp? {
    return this.getTimestamp()
}

//===========================================================================================

fun MediaPlayer.取当前位置(): Int {
    return this.getCurrentPosition()
}

//===========================================================================================

fun MediaPlayer.取持续时间(): Int {
    return this.getDuration()
}

//===========================================================================================

fun MediaPlayer.置下一个媒体播放器(下一个: MediaPlayer?) {
    this.setNextMediaPlayer(下一个)
}

//===========================================================================================

fun MediaPlayer.释放() {
    this.release()
}

//===========================================================================================

fun MediaPlayer.重置() {
    this.reset()
}

//===========================================================================================

fun MediaPlayer.置音频流类型(类型: Int) {
    this.setAudioStreamType(类型)
}

//===========================================================================================

fun MediaPlayer.置音频属性(属性: AudioAttributes?) {
    this.setAudioAttributes(属性)
}

//===========================================================================================

fun MediaPlayer.置循环播放(循环: Boolean) {
    this.setLooping(循环)
}

//===========================================================================================

fun MediaPlayer.是否循环播放(): Boolean {
    return this.isLooping()
}

//===========================================================================================

fun MediaPlayer.置音量(左声道音量: Float, 右声道音量: Float) {
    this.setVolume(左声道音量, 右声道音量)
}

fun MediaPlayer.置音量(音量: Float) {
    this.setVolume(音量, 音量)
}

//===========================================================================================

fun MediaPlayer.置音频会话Id(会话Id: Int) {
    this.setAudioSessionId(会话Id)
}

fun MediaPlayer.取音频会话Id(): Int {
    return this.getAudioSessionId()
}

//===========================================================================================

fun MediaPlayer.附加辅助音效(音效Id: Int) {
    this.attachAuxEffect(音效Id)
}

//===========================================================================================

fun MediaPlayer.置辅助音效发送级别(级别: Float) {
    this.setAuxEffectSendLevel(级别)
}

//===========================================================================================

fun MediaPlayer.取轨迹信息(): Array<MediaPlayer.TrackInfo?>? {
    return this.getTrackInfo()
}

//===========================================================================================

fun MediaPlayer.添加定时文本源(路径: String?, 媒体类型: String?) {
    this.addTimedTextSource(路径, 媒体类型)
}

fun MediaPlayer.添加定时文本源(上下文: Context?, 网址: Uri?, 媒体类型: String?) {
    this.addTimedTextSource(上下文, 网址, 媒体类型)
}

fun MediaPlayer.添加定时文本源(文件: FileDescriptor?, 媒体类型: String?) {
    this.addTimedTextSource(文件, 媒体类型)
}

fun MediaPlayer.添加定时文本源(文件: FileDescriptor?, 偏移: Long, 长度: Long, 媒体类型: String?) {
    this.addTimedTextSource(文件, 偏移, 长度, 媒体类型)
}

//===========================================================================================

fun MediaPlayer.取选择轨道(轨道类型: Int): Int {
    return this.getSelectedTrack(轨道类型)
}

fun MediaPlayer.选择轨道(索引: Int) {
    this.selectTrack(索引)
}

fun MediaPlayer.取消选择轨道(索引: Int) {
    this.deselectTrack(索引)
}

//===========================================================================================

fun MediaPlayer.置准备监听器(监听器: MediaPlayer.OnPreparedListener?) {
    this.setOnPreparedListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置完成监听器(监听器: MediaPlayer.OnCompletionListener?) {
    this.setOnCompletionListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置缓冲更新监听器(监听器: MediaPlayer.OnBufferingUpdateListener?) {
    this.setOnBufferingUpdateListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置跳转完成监听器(监听器: MediaPlayer.OnSeekCompleteListener?) {
    this.setOnSeekCompleteListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置视频大小改变监听器(监听器: MediaPlayer.OnVideoSizeChangedListener?) {
    this.setOnVideoSizeChangedListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置定时文本监听器(监听器: MediaPlayer.OnTimedTextListener?) {
    this.setOnTimedTextListener(监听器)
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.置字幕数据监听器(监听器: OnSubtitleDataListener, 处理器: android.os.Handler) {
    this.setOnSubtitleDataListener(监听器, 处理器)
}

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.置字幕数据监听器(监听器: OnSubtitleDataListener) {
    this.setOnSubtitleDataListener(监听器)
}

@RequiresApi(Build.VERSION_CODES.P)
fun MediaPlayer.清除字幕数据监听器() {
    this.clearOnSubtitleDataListener()
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.P)
fun MediaPlayer.置媒体时间不连续监听器(监听器: OnMediaTimeDiscontinuityListener, 处理器: Handler) {
    this.setOnMediaTimeDiscontinuityListener(监听器, 处理器)
}

@RequiresApi(api = Build.VERSION_CODES.P)
fun MediaPlayer.置媒体时间不连续监听器(监听器: OnMediaTimeDiscontinuityListener) {
    this.setOnMediaTimeDiscontinuityListener(监听器)
}

@RequiresApi(api = Build.VERSION_CODES.P)
fun MediaPlayer.清除媒体时间不连续监听器() {
    this.clearOnMediaTimeDiscontinuityListener()
}

//===========================================================================================

fun MediaPlayer.置定时元数据可用监听器(监听器: OnTimedMetaDataAvailableListener?) {
    this.setOnTimedMetaDataAvailableListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置错误监听器(监听器: MediaPlayer.OnErrorListener?) {
    this.setOnErrorListener(监听器)
}

//===========================================================================================

fun MediaPlayer.置信息监听器(监听器: MediaPlayer.OnInfoListener?) {
    this.setOnInfoListener(监听器)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.置DRM配置助手(监听器: OnDrmConfigHelper?) {
    this.setOnDrmConfigHelper(监听器)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.置DRM信息监听器(监听器: OnDrmInfoListener?) {
    this.setOnDrmInfoListener(监听器)
}

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.置DRM信息监听器(监听器: OnDrmInfoListener?, 处理器: Handler?) {
    this.setOnDrmInfoListener(监听器, 处理器)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.置Drm准备监听器(监听器: OnDrmPreparedListener?) {
    this.setOnDrmPreparedListener(监听器)
}

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.置Drm准备监听器(监听器: OnDrmPreparedListener?, 处理器: Handler?) {
    this.setOnDrmPreparedListener(监听器, 处理器)
}

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.取Drm信息(): MediaPlayer.DrmInfo? {
    return this.getDrmInfo()
}

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.准备Drm(uuid: UUID) {
    this.prepareDrm(uuid)
}

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.释放Drm() {
    this.releaseDrm()
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.取密钥请求(密钥集合Id: ByteArray?, 初始化数据: ByteArray?, 媒体类型: String?, 键类型: Int,
                可选参数: MutableMap<String?, String?>?): MediaDrm.KeyRequest {
    return this.getKeyRequest(密钥集合Id, 初始化数据, 媒体类型, 键类型, 可选参数)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.O)
fun MediaPlayer.提供关键响应(密钥集合Id: ByteArray?, 响应: ByteArray): ByteArray? {
    return this.provideKeyResponse(密钥集合Id, 响应)
}

//===========================================================================================

@RequiresApi(Build.VERSION_CODES.O)
fun MediaPlayer.恢复密钥(密钥集合Id: ByteArray) {
    this.restoreKeys(密钥集合Id)
}

//===========================================================================================

@RequiresApi(api = android.os.Build.VERSION_CODES.O)
fun MediaPlayer.取Drm属性字符串(属性名: String): String {
    return this.getDrmPropertyString(属性名)
}

@RequiresApi(api = android.os.Build.VERSION_CODES.O)
fun MediaPlayer.置Drm属性字符串(属性名: String, 值: String) {
    this.setDrmPropertyString(属性名, 值)
}

//===========================================================================================
