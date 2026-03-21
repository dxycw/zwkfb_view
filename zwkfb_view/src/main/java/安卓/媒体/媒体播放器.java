package 安卓.媒体;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioRouting;
import android.media.DeniedByServerException;
import android.media.MediaDataSource;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.media.PlaybackParams;
import android.media.ResourceBusyException;
import android.media.SubtitleData;
import android.media.SyncParams;
import android.media.TimedMetaData;
import android.media.TimedText;
import android.media.UnsupportedSchemeException;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：媒体播放器
 * @author dxyc
 */
public class 媒体播放器 extends MediaPlayer {

    public 媒体播放器() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public 媒体播放器(@NonNull Context 上下文) {
        super(上下文);
    }

    //===========================================================================================

    public void 置显示屏(SurfaceHolder sh) {
        this.setDisplay(sh);
    }

    public void 置表面(Surface 表面) {
        this.setSurface(表面);
    }

    //===========================================================================================

    public static final int 视频_扩展_模式_规模_到_合适 = MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT;

    public static final int 视频_扩展_模式_规模_到_合适_与_裁剪 = MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING;

    //===========================================================================================


    public void 置视频缩放模式(int 模式) {
        this.setVideoScalingMode(模式);
    }

    //===========================================================================================

    public static 媒体播放器 创建(Context 上下文, Uri 网址) {
        return 创建(上下文, 网址, null);
    }

    public static 媒体播放器 创建(Context 上下文, Uri 网址, SurfaceHolder 持有者) {
        return 创建(上下文, 网址, 持有者, null, 0);
    }

    public static 媒体播放器 创建(Context 上下文, Uri 网址, SurfaceHolder 持有者, AudioAttributes 音频属性, int 音频会话Id) {
        try {
            媒体播放器 mp = new 媒体播放器();
            mp.setAudioSessionId(音频会话Id);
            final AudioAttributes aa = 音频属性 != null ? 音频属性 : new AudioAttributes.Builder().build();
            mp.setAudioAttributes(aa);
            mp.setDataSource(上下文, 网址);
            if (持有者 != null) {
                mp.setDisplay(持有者);
            }
            mp.prepare();
            return mp;
        } catch (IOException | IllegalArgumentException | SecurityException ex) {
            Log.d("媒体播放器", "创建 失败:", ex);
        }
        return null;
    }

    public static 媒体播放器 创建(Context 上下文, int 资源Id) {
        return 创建(上下文, 资源Id, null, 0) ;
    }

    public static 媒体播放器 创建(Context 上下文, int 资源Id, AudioAttributes 音频属性, int 音频会话Id)  {
        try {
            AssetFileDescriptor afd = 上下文.getResources().openRawResourceFd(资源Id);
            if (afd == null) return null;
            媒体播放器 mp = new 媒体播放器();
            mp.setAudioSessionId(音频会话Id);
            final AudioAttributes aa = 音频属性 != null ? 音频属性 : new AudioAttributes.Builder().build();
            mp.setAudioAttributes(aa);
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mp.prepare();
            return mp;
        } catch (IOException | IllegalArgumentException | SecurityException ex) {
            Log.d("媒体播放器", "创建 失败:", ex);
        }
        return null;
    }

    //===========================================================================================


    public void 置数据源(@NonNull Context 上下文, @NonNull Uri 网址) throws IOException {
        this.setDataSource(上下文, 网址);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置数据源(@NonNull Context 上下文, @NonNull Uri 网址, Map<String, String> 头部, List<HttpCookie> cookies) throws IOException {
        this.setDataSource(上下文, 网址, 头部, cookies);
    }

    public void 置数据源(@NonNull Context 上下文, @NonNull Uri 网址, Map<String, String> 头部) throws IOException {
        this.setDataSource(上下文, 网址, 头部);
    }

    public void 置数据源(String 路径) throws IOException {
        this.setDataSource(路径);
    }

    public void 置数据源(@NonNull AssetFileDescriptor 资源文件) throws IOException {
        this.setDataSource(资源文件);
    }

    public void 置数据源(FileDescriptor 文件) throws IOException {
        this.setDataSource(文件);
    }

    public void 置数据源(FileDescriptor 文件, long 偏移, long 长度) throws IOException {
        this.setDataSource(文件, 偏移, 长度);
    }

    public void 置数据源(MediaDataSource 数据源) {
        this.setDataSource(数据源);
    }


    public void 准备() throws IOException {
        this.prepare();
    }

    public void 准备异步() {
        this.prepareAsync();
    }

    public void 开始() {
        this.start();
    }

    public void 停止() {
        this.stop();
    }


    public void 暂停() {
        this.pause();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public VolumeShaper 创建音量调节器(VolumeShaper.Configuration 配置) {
        return this.createVolumeShaper(配置);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public boolean 置首选设备(AudioDeviceInfo 设备信息) {
        return this.setPreferredDevice(设备信息);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public AudioDeviceInfo 取首选设备() {
        return this.getPreferredDevice();
    }



    @RequiresApi(api = Build.VERSION_CODES.P)
    public AudioDeviceInfo 取路由设备() {
        return this.getRoutedDevice();
    }

    @RequiresApi(api = Build.VERSION_CODES.BAKLAVA)
    public @NonNull List<AudioDeviceInfo> 取路由设备组() {
        return this.getRoutedDevices();
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 添加路由改变监听器(AudioRouting.OnRoutingChangedListener 监听器, Handler 处理器) {
        this.addOnRoutingChangedListener(监听器, 处理器);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 移除路由改变监听器(AudioRouting.OnRoutingChangedListener 监听器) {
        this.removeOnRoutingChangedListener(监听器);
    }

    public void 置唤醒模式(Context 上下文, int 模式) {
        this.setWakeMode(上下文, 模式);
    }

    public void 置屏幕常亮播放时(boolean 屏幕常亮) {
        this.setScreenOnWhilePlaying(屏幕常亮);
    }

    public int 取视频宽度() {
        return this.getVideoWidth();
    }

    public int 取视频高度() {
        return this.getVideoHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PersistableBundle 取指标() {
        return this.getMetrics();
    }

    public boolean 是否播放(){
        return this.isPlaying();
    }

    public void 置播放参数(@NonNull PlaybackParams 参数){
        this.setPlaybackParams(参数);
    }

    public PlaybackParams 取播放参数(){
        return this.getPlaybackParams();
    }

    public void 置同步参数(@NonNull SyncParams 参数){
        this.setSyncParams(参数);
    }

    public SyncParams 取同步参数(){
        return this.getSyncParams();
    }


    //======================================================================================

    /**
     * This mode is used with {@link #seekTo(long, int)} to move media position to
     * a sync (or key) frame associated with a data source that is located
     * right before or at the given time.
     *
     * @see #seekTo(long, int)
     */
    public static final int 跳转_上一个_同步    = MediaPlayer.SEEK_PREVIOUS_SYNC;
    /**
     * This mode is used with {@link #seekTo(long, int)} to move media position to
     * a sync (or key) frame associated with a data source that is located
     * right after or at the given time.
     *
     * @see #seekTo(long, int)
     */
    public static final int 跳转_写一个_同步        = MediaPlayer.SEEK_NEXT_SYNC;
    /**
     * This mode is used with {@link #seekTo(long, int)} to move media position to
     * a sync (or key) frame associated with a data source that is located
     * closest to (in time) or at the given time.
     *
     * @see #seekTo(long, int)
     */
    public static final int 跳转_最近_同步     = MediaPlayer.SEEK_CLOSEST_SYNC;
    /**
     * This mode is used with {@link #seekTo(long, int)} to move media position to
     * a frame (not necessarily a key frame) associated with a data source that
     * is located closest to or at the given time.
     *
     * @see #seekTo(long, int)
     */
    public static final int 跳转_最近          = MediaPlayer.SEEK_CLOSEST;

    //======================================================================================

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 跳转到(long 毫秒, int 模式){
        this.seekTo(毫秒, 模式);
    }

    public void 跳转到(int 毫秒){
        this.seekTo(毫秒);
    }

    public MediaTimestamp 取时间戳(){
        return this.getTimestamp();
    }

    public int 取当前位置(){
        return this.getCurrentPosition();
    }


    public int 取持续时间(){
        return this.getDuration();
    }

    public void 置下一个媒体播放器(MediaPlayer 下一个){
        this.setNextMediaPlayer(下一个);
    }

    public void 释放(){
        this.release();
    }

    public void 重置(){
        this.reset();
    }

    public void 置音频流类型(int 类型){
        this.setAudioStreamType(类型);
    }

    public void 置音频属性(AudioAttributes 属性){
        this.setAudioAttributes(属性);
    }

    public void 置循环播放(boolean 循环){
        this.setLooping(循环);
    }

    public boolean 是否循环播放(){
        return this.isLooping();
    }


    public void 置音量(float 左声道音量, float 右声道音量){
        this.setVolume(左声道音量, 右声道音量);
    }

    public void 置音量(float 音量){
        this.setVolume(音量, 音量);
    }

    public void 置音频会话Id(int 会话Id){
        this.setAudioSessionId(会话Id);
    }

    public int 取音频会话Id(){
        return this.getAudioSessionId();
    }


    public void 附加辅助音效(int 音效Id){
        this.attachAuxEffect(音效Id);
    }

    public void 置辅助音效发送级别(float 级别){
        this.setAuxEffectSendLevel(级别);
    }

    //======================================================================================

    public static class 轨道信息{

        public static final int 媒体_轨道_类型_未知 = TrackInfo.MEDIA_TRACK_TYPE_UNKNOWN;
        public static final int 媒体_轨道_类型_视频 = TrackInfo.MEDIA_TRACK_TYPE_VIDEO;
        public static final int 媒体_轨道_类型_音频 = TrackInfo.MEDIA_TRACK_TYPE_AUDIO;
        public static final int 媒体_轨道_类型_定时文本 = TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT;
        public static final int 媒体_轨道_类型_字幕 = TrackInfo.MEDIA_TRACK_TYPE_SUBTITLE;
        public static final int 媒体_轨道_类型_元数据 = TrackInfo.MEDIA_TRACK_TYPE_METADATA;

    }

    //======================================================================================

    public TrackInfo[] 取轨迹信息() {
        return this.getTrackInfo();
    }

    //======================================================================================

    public static final String 媒体_媒体类型_文本_字幕 = MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP;

    //======================================================================================


    public void 添加定时文本源(String 路径, String 媒体类型) throws IOException {
        this.addTimedTextSource(路径, 媒体类型);
    }

    public void 添加定时文本源(Context 上下文, Uri 网址, String 媒体类型) throws IOException {
        this.addTimedTextSource(上下文, 网址, 媒体类型);
    }

    public void 添加定时文本源(FileDescriptor 文件, String 媒体类型) {
        this.addTimedTextSource(文件, 媒体类型);
    }

    public void 添加定时文本源(FileDescriptor 文件, long 偏移, long 长度, String 媒体类型) {
        this.addTimedTextSource(文件, 偏移, 长度, 媒体类型);
    }


    public int 取选择轨道(int 轨道类型){
        return this.getSelectedTrack(轨道类型);
    }

    public void 选择轨道(int 索引){
        this.selectTrack(索引);
    }

    public void 取消选择轨道(int 索引){
        this.deselectTrack(索引);
    }


    public interface 准备完成监听器 extends OnPreparedListener {
        @Override
        default void onPrepared(MediaPlayer mp){
            准备完成(mp);
        }

        void 准备完成(MediaPlayer 播放器);
    }


    public void 置准备监听器(OnPreparedListener 监听器) {
        this.setOnPreparedListener(监听器);
    }


    public interface 完成监听器 extends OnCompletionListener {
        @Override
        default void onCompletion(MediaPlayer mp){
            完成(mp);
        }

        void 完成(MediaPlayer 播放器);
    }

    public void 置完成监听器(OnCompletionListener 监听器) {
        this.setOnCompletionListener(监听器);
    }


    public interface 缓冲更新监听器 extends OnBufferingUpdateListener {
        @Override
        default void onBufferingUpdate(MediaPlayer mp, int percent){
            缓冲更新(mp, percent);
        }

        void 缓冲更新(MediaPlayer 播放器, int 百分比);
    }

    public void 置缓冲更新监听器(OnBufferingUpdateListener 监听器) {
        this.setOnBufferingUpdateListener(监听器);
    }


    public interface 跳转完成监听器 extends OnSeekCompleteListener {
        @Override
        default void onSeekComplete(MediaPlayer mp){
            跳转完成(mp);
        }

        void 跳转完成(MediaPlayer 播放器);

    }

    public void 置跳转完成监听器(OnSeekCompleteListener 监听器) {
        this.setOnSeekCompleteListener(监听器);
    }


    public interface 视频大小改变监听器 extends OnVideoSizeChangedListener {
        @Override
        default void onVideoSizeChanged(MediaPlayer mp, int width, int height){
            视频大小改变(mp, width, height);
        }

        void 视频大小改变(MediaPlayer 播放器, int 宽度, int 高度);
    }

    public void 置视频大小改变监听器(OnVideoSizeChangedListener 监听器) {
        this.setOnVideoSizeChangedListener(监听器);
    }


    public interface 定时文本监听器 extends OnTimedTextListener {

        @Override
        default void onTimedText(MediaPlayer mp, TimedText text){
            定时文本(mp, text);
        }
        void 定时文本(MediaPlayer 播放器, TimedText 文本);
    }


    public void 置定时文本监听器(OnTimedTextListener 监听器) {
        this.setOnTimedTextListener(监听器);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public interface 字幕数据监听器 extends OnSubtitleDataListener {
        @Override
        default void onSubtitleData(@NonNull MediaPlayer mp, @NonNull SubtitleData data){
            字幕数据(mp, data);
        }

        void 字幕数据(@NonNull MediaPlayer 播放器, @NonNull SubtitleData 数据);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 置字幕数据监听器(@NonNull OnSubtitleDataListener 监听器, @NonNull Handler 处理器) {
        this.setOnSubtitleDataListener(监听器, 处理器);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 置字幕数据监听器(@NonNull OnSubtitleDataListener 监听器) {
        this.setOnSubtitleDataListener(监听器);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 清除字幕数据监听器() {
        this.clearOnSubtitleDataListener();
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public interface 媒体时间不连续监听器 extends OnMediaTimeDiscontinuityListener {
        @Override
        default void onMediaTimeDiscontinuity(@NonNull MediaPlayer mp, @NonNull MediaTimestamp mts){
            媒体时间不连续(mp, mts);
        }

        void 媒体时间不连续(MediaPlayer 播放器, MediaTimestamp 媒体时间戳);

    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 置媒体时间不连续监听器(@NonNull OnMediaTimeDiscontinuityListener 监听器, @NonNull Handler 处理器) {
        this.setOnMediaTimeDiscontinuityListener(监听器, 处理器);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 置媒体时间不连续监听器(@NonNull OnMediaTimeDiscontinuityListener 监听器) {
        this.setOnMediaTimeDiscontinuityListener(监听器);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void 清除媒体时间不连续监听器() {
        this.clearOnMediaTimeDiscontinuityListener();
    }



    public interface 定时元数据可用监听器 extends OnTimedMetaDataAvailableListener {
        @Override
        default void onTimedMetaDataAvailable(MediaPlayer mp, TimedMetaData data){
            定时元数据可用(mp, data);
        }

        void 定时元数据可用(MediaPlayer 播放器, TimedMetaData 数据);
    }

    public void 置定时元数据可用监听器(OnTimedMetaDataAvailableListener 监听器) {
        this.setOnTimedMetaDataAvailableListener(监听器);
    }


    //===========================================================================================

    public static final int 媒体_错误_未知 = MediaPlayer.MEDIA_ERROR_UNKNOWN;

    public static final int 媒体_错误_服务器_死亡 = MediaPlayer.MEDIA_ERROR_SERVER_DIED;

    public static final int 媒体_错误_不是_有效_为了_进步的_回放 = MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK;

    public static final int 媒体_错误_IO = MediaPlayer.MEDIA_ERROR_IO;

    public static final int 媒体_错误_格式错误 = MediaPlayer.MEDIA_ERROR_MALFORMED;

    public static final int 媒体_错误_不支持 = MediaPlayer.MEDIA_ERROR_UNSUPPORTED;

    public static final int 媒体_错误_定时_出 = MediaPlayer.MEDIA_ERROR_TIMED_OUT;

    //===========================================================================================


    public interface 错误监听器 extends OnErrorListener {
        @Override
        default boolean onError(MediaPlayer mp, int what, int extra){
            return 错误(mp, what, extra);
        }

        boolean 错误(MediaPlayer 播放器, int 错误码, int 额外信息);
    }


    public void 置错误监听器(OnErrorListener 监听器) {
        this.setOnErrorListener(监听器);
    }

    //===========================================================================================

    public static final int 媒体_信息_未知 = MediaPlayer.MEDIA_INFO_UNKNOWN;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static final int 媒体_信息_开始_如同_下一个 = MediaPlayer.MEDIA_INFO_STARTED_AS_NEXT;

    public static final int 媒体_信息_视频_渲染_开始 = MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START;

    public static final int 媒体_信息_视频_轨道_滞后 = MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING;

    public static final int 媒体_信息_缓冲中_开始 = MediaPlayer.MEDIA_INFO_BUFFERING_START;

    public static final int 媒体_信息_缓冲中_结束 = MediaPlayer.MEDIA_INFO_BUFFERING_END;

    public static final int 媒体_信息_坏_交错 = MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING;

    public static final int 媒体_信息_不是_可搜索 = MediaPlayer.MEDIA_INFO_NOT_SEEKABLE;

    public static final int 媒体_信息_元数据_更新 = MediaPlayer.MEDIA_INFO_METADATA_UPDATE;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final int 媒体_信息_音频_不是_正在播放 = MediaPlayer.MEDIA_INFO_AUDIO_NOT_PLAYING;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final int 媒体_信息_视频_不是_正在播放 = MediaPlayer.MEDIA_INFO_VIDEO_NOT_PLAYING;


    public static final int 媒体_信息_不支持_字幕 = MediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE;

    public static final int 媒体_信息_字幕_定时_出 = MediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT;


    //===========================================================================================

    public interface 信息监听器 extends OnInfoListener {
        @Override
        default boolean onInfo(MediaPlayer mp, int what, int extra){
            return 信息(mp, what, extra);
        }

        boolean 信息(MediaPlayer 播放器, int 信息码, int 额外信息);
    }

    public void 置信息监听器(OnInfoListener 监听器) {
        this.setOnInfoListener(监听器);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public interface DRM配置助手 extends OnDrmConfigHelper {
        @Override
        default void onDrmConfig(MediaPlayer mp){
            DRM配置(mp);
        }

        void DRM配置(MediaPlayer 播放器);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置DRM配置助手(OnDrmConfigHelper 监听器) {
        this.setOnDrmConfigHelper(监听器);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public interface DRM信息监听器 extends OnDrmInfoListener {
        @Override
        default void onDrmInfo(MediaPlayer mp, DrmInfo drmInfo){
            DRM信息(mp, drmInfo);
        }
        void DRM信息(MediaPlayer 播放器, DrmInfo drm信息);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置DRM信息监听器(OnDrmInfoListener 监听器) {
        this.setOnDrmInfoListener(监听器);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置DRM信息监听器(OnDrmInfoListener 监听器, Handler 处理器) {
        this.setOnDrmInfoListener(监听器, 处理器);
    }

    //===========================================================================================

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final int 准备_DRM_状态_成功 = MediaPlayer.PREPARE_DRM_STATUS_SUCCESS;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final int 准备_DRM_状态_配置_网络_错误 = MediaPlayer.PREPARE_DRM_STATUS_PROVISIONING_NETWORK_ERROR;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final int 准备_DRM_状态_配置_服务器_错误 = MediaPlayer.PREPARE_DRM_STATUS_PROVISIONING_SERVER_ERROR;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final int 准备_DRM_状态_准备_错误 = MediaPlayer.PREPARE_DRM_STATUS_PREPARATION_ERROR;

    //===========================================================================================

    @RequiresApi(api = Build.VERSION_CODES.O)
    public interface Drm准备监听器 extends OnDrmPreparedListener {
        @Override
        default void onDrmPrepared(MediaPlayer mp, int status){
            Drm准备(mp, status);
        }
        void Drm准备(MediaPlayer 播放器, int 状态);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置Drm准备监听器(OnDrmPreparedListener 监听器) {
        this.setOnDrmPreparedListener(监听器);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置Drm准备监听器(OnDrmPreparedListener 监听器, Handler 处理器) {
        this.setOnDrmPreparedListener(监听器, 处理器);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DrmInfo 取Drm信息() {
        return this.getDrmInfo();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 准备Drm(@NonNull UUID uuid) throws ProvisioningNetworkErrorException, ProvisioningServerErrorException,
            ResourceBusyException, UnsupportedSchemeException {
        this.prepareDrm(uuid);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 释放Drm() throws NoDrmSchemeException {
        this.releaseDrm();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MediaDrm.KeyRequest 取密钥请求(byte[] 密钥集合Id, byte[] 初始化数据, String 媒体类型, int 键类型,
                                          Map<String, String> 可选参数) throws NoDrmSchemeException {
        return this.getKeyRequest(密钥集合Id, 初始化数据, 媒体类型, 键类型, 可选参数);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public byte[] 提供关键响应(byte[] 密钥集合Id, @NonNull byte[] 响应) throws DeniedByServerException, NoDrmSchemeException {
        return this.provideKeyResponse(密钥集合Id, 响应);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 恢复密钥(@NonNull byte[] 密钥集合Id) throws NoDrmSchemeException {
        this.restoreKeys(密钥集合Id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String 取Drm属性字符串(@NonNull String 属性名) throws NoDrmSchemeException {
        return this.getDrmPropertyString(属性名);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置Drm属性字符串(@NonNull String 属性名, @NonNull String 值) throws NoDrmSchemeException {
        this.setDrmPropertyString(属性名, 值);
    }

    //===========================================================================================

    public static final class 无DRM方案异常 extends MediaDrmException {
        public 无DRM方案异常(String 详细信息) {
            super(详细信息);
        }
    }
    public static final class 网络配置错误异常 extends MediaDrmException {
        public 网络配置错误异常(String 详细信息) {
            super(详细信息);
        }
    }


    public static final class 配置服务器错误异常 extends MediaDrmException {
        public 配置服务器错误异常(String 详细信息) {
            super(详细信息);
        }
    }

    public final static class 度量常量{ // MetricsConstants
        private 度量常量() {}
        /**
         * Key to extract the MIME type of the video track
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is a String.
         */
        public static final String 媒体_类型_视频 = MetricsConstants.MIME_TYPE_VIDEO;

        /**
         * Key to extract the codec being used to decode the video track
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is a String.
         */
        public static final String 编解码器_视频 = MetricsConstants.CODEC_VIDEO;

        /**
         * Key to extract the width (in pixels) of the video track
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is an integer.
         */
        public static final String 宽度 = MetricsConstants.WIDTH;

        /**
         * Key to extract the height (in pixels) of the video track
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is an integer.
         */
        public static final String 高度 = MetricsConstants.HEIGHT;

        /**
         * Key to extract the count of video frames played
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is an integer.
         */
        public static final String 框架 = MetricsConstants.FRAMES;

        /**
         * Key to extract the count of video frames dropped
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is an integer.
         */
        public static final String 框架_已丢弃 = MetricsConstants.FRAMES_DROPPED;

        /**
         * Key to extract the MIME type of the audio track
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is a String.
         */
        public static final String 媒体_类型_音频 = MetricsConstants.MIME_TYPE_AUDIO;

        /**
         * Key to extract the codec being used to decode the audio track
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is a String.
         */
        public static final String 编解码器_音频 = MetricsConstants.CODEC_AUDIO;

        /**
         * Key to extract the duration (in milliseconds) of the
         * media being played
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is a long.
         */
        public static final String 持续时间 = MetricsConstants.DURATION;

        /**
         * Key to extract the playing time (in milliseconds) of the
         * media being played
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is a long.
         */
        public static final String 正在播放 = MetricsConstants.PLAYING;

        /**
         * Key to extract the count of errors encountered while
         * playing the media
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is an integer.
         */
        public static final String 错误 = MetricsConstants.ERRORS;

        /**
         * Key to extract an (optional) error code detected while
         * playing the media
         * from the {@link MediaPlayer#getMetrics} return value.
         * The value is an integer.
         */
        public static final String 错误_代码 = MetricsConstants.ERROR_CODE;
    }


}
