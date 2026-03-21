package 安卓.操作系统;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import java.util.List;
import java.util.Objects;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：构建
 * @author dxyc
 */
public class 构建 extends Build {

    /** 用于构建属性未知时的值。 */
    public static final String 未知 = Build.UNKNOWN;

    /** 要么是变更列表编号，要么是类似“M4-rc20”的标签。 */
    public static final String ID = Build.ID;

    /** 用于向用户显示屏的构建ID字符串。 */
    public static final String 显示屏 = Build.DISPLAY;

    /** 整体产品的名称。 */
    public static final String 产品 = Build.PRODUCT;

    /** 产品外观设计名称。 */
    public static final String 设备 = Build.DEVICE;

    /** 底层主板名称，例如 'goldfish'。*/
    public static final String 主板 = Build.BOARD;

    /**
     * 本地代码的指令集名称（CPU 架构 + ABI 规范）。
     *
     * @deprecated 使用 {@link #SUPPORTED_ABIS} 代替.
     */
    @Deprecated
    public static final String CPU_ABI = Build.CPU_ABI;

    /**
     * 本地代码的辅助指令集名称（CPU 架构 + ABI 规范）。
     *
     * @deprecated Use {@link #SUPPORTED_ABIS} instead.
     */
    @Deprecated
    public static final String CPU_ABI2 = Build.CPU_ABI2;

    /** 该产品/硬件的制造商。 */
    public static final String 制造商 = Build.MANUFACTURER;

    /** 该产品/硬件将关联的面向消费者的品牌（如果有的话）。 */
    public static final String 品牌 = Build.BRAND;

    /** 终端用户可见的终端产品名称。 */
    public static final String 型号 = Build.MODEL;

    /** "设备主系统级芯片（SOC）的制造商。 */
    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String SOC_制造商 = Build.SOC_MANUFACTURER;

    /** 设备主系统级芯片（SOC）的型号名称。 */
    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String SOC_型号 = Build.SOC_MODEL;

    /** 系统引导加载程序的版本号。 */
    public static final String 引导程序 = Build.BOOTLOADER;


    /**
     * 无线电固件版本号。
     * @deprecated 当此类被初始化时，无线电固件版本通常不可用，导致该字符串显示为空或“未知”值。
     * 使用 {@link #getRadioVersion} 代替.
     */
    @Deprecated
    public static final String 收音机 = Build.RADIO;


    /** 硬件的名称（来自内核命令行或/proc）。 */
    public static final String 硬件 = Build.HARDWARE;


    /**
     * 硬件的SKU（来自内核命令行）。
     * <p>
     * SKU由引导加载程序报告以配置系统软件功能。如果引导加载程序未提供任何值，将报告为 {@link #未知}.
     */
    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String SKU = Build.SKU;


    /**
     * 由原始设计制造商（ODM）设置的设备SKU。
     * <p>
     * 这是在启动期间初始化的运行时属性集，用于配置设备服务。如果未设置值，则会报告为 {@link #未知}.
     * <p>
     * 在制造商对相同设计生产多种变体的情况下，ODM SKU 可能会对应同一系统 SKU 的多个变体。例如，相同的版本可能会
     * 以物理键盘和/或显示硬件的不同变化发布，每种变化对应不同的 ODM SKU。
     */
    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String ODM_SKU = Build.ODM_SKU;



    /**
     * 硬件序列号（如有）。仅限字母数字，不区分大小写。* 此字段始终被设置为 {@link 构建#未知}.
     * @deprecated 使用 {@link #取序列号()} 代替.
     **/
    @SuppressLint("HardwareIds")
    @Deprecated
    public static final String 序列号 = Build.SERIAL;


    @RequiresApi(api = VERSION_CODES.O)
    @RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
    public static String 取序列号() {
        return Build.getSerial();
    }


    /**
     * 此设备支持的 ABI 有序列表。列表中的第一个元素是最优先的 ABI。
     * <p>
     * 使用 {@link #支持_32_BIT_ABIS} 和 {@link #支持_64_BIT_ABIS}.
     */
    public static final String[] 支持_ABIS = Build.SUPPORTED_ABIS;

    /**
     * 此设备支持的 <b> 32位 </b> ABI的有序列表。 最优先的ABI是列表中的第一个元素。
     * <p>
     * 使用 {@link #支持_ABIS} 和 {@link #支持_64_BIT_ABIS}.
     */
    public static final String[] 支持_32_BIT_ABIS = Build.SUPPORTED_32_BIT_ABIS;

    /**
     * 此设备支持的 <b> 64位 </b> ABI的有序列表。最优先的ABI*是列表中的第一个元素。
     * <p>
     * 使用 {@link #支持_ABIS} 和 {@link #支持_32_BIT_ABIS}.
     */
    public static final String[] 支持_64_BIT_ABIS = Build.SUPPORTED_64_BIT_ABIS;


    /** 各种版本字符串 */
    public static class 版本 extends VERSION{

        /**
         * 底层源代码控制用于表示此构建的内部值。例如，Perforce变更列表编号或Git哈希值。
         */
        public static final String 内部版本号 = VERSION.INCREMENTAL;


        /**
         * 用户可见的版本字符串。例如，“1.0”或“3.4b5”或“bananas”。
         * <p>
         * 此字段是一个不透明字符串。不要假设其值具有任何特定结构，也不要假设来自不同版本的RELEASE值可以以某种方式排序。
         */
        public static final String 发布版本 = VERSION.RELEASE;


        /**
         * The version string.  May be {@link #发布版本} or {@link #CODENAME} if
         * not a final release build.
         */
        @RequiresApi(api = VERSION_CODES.R)
        public static final String 发布版本_或_开发代号 = VERSION.RELEASE_OR_CODENAME;

        /**
         * The version string we show to the user; may be {@link #RELEASE} or
         * a descriptive string if not a final release build.
         */
        @RequiresApi(api = VERSION_CODES.TIRAMISU)
        public static final String 发布版本_或_预览_显示屏 = VERSION.RELEASE_OR_PREVIEW_DISPLAY;


        /**
         * The base OS build the product is based on.
         */
        public static final String 基础_OS = VERSION.BASE_OS;

        /**
         * The user-visible security patch level. This value represents the date when the device
         * most recently applied a security patch.
         */
        public static final String 安全_补丁 = VERSION.SECURITY_PATCH;

        @RequiresApi(api = VERSION_CODES.S)
        public static final int 媒体_性能_类 = VERSION.MEDIA_PERFORMANCE_CLASS;


        public static final String SDK = VERSION.SDK;

        @SuppressLint("AnnotateVersionCheck")
        public static final int SDK_整数 = VERSION.SDK_INT;

        @SuppressLint("AnnotateVersionCheck")
        public static final int SDK_整数_全 = VERSION.SDK_INT_FULL;

        public static final int 预览_SDK_整数 = VERSION.PREVIEW_SDK_INT;

        public static final String 开发代号 = VERSION.CODENAME;
    }

    public static class 版本_代码 extends VERSION_CODES {

        /**
         * 当前开发构建的魔数版本号，尚未转为正式版本。
         * <p>
         * 此值必须与 VMRuntime.SDK_VERSION_CUR_DEVELOPMENT 匹配
         */
        public static final int 当前_开发版本 = VERSION_CODES.CUR_DEVELOPMENT;

        /**
         * Android 1 版本
         */
        public static final int Sdk1 = VERSION_CODES.BASE;
        /**
         * Android 1.1 版本
         */
        public static final int Sdk2 = VERSION_CODES.BASE_1_1;
        /**
         * Android 1.5 版本
         */
        public static final int Sdk3 = VERSION_CODES.CUPCAKE;
        /**
         * Android 1.6 版本
         */
        public static final int Sdk4 = VERSION_CODES.DONUT;
        /**
         * Android 2.0 版本
         */
        public static final int Sdk5 = VERSION_CODES.ECLAIR;
        /**
         * Android 2.0.1 版本
         */
        public static final int Sdk6 = VERSION_CODES.ECLAIR_0_1;
        /**
         * Android 2.1 版本
         */
        public static final int Sdk7 = VERSION_CODES.ECLAIR_MR1;
        /**
         * Android 2.2 版本
         */
        public static final int Sdk8 = VERSION_CODES.FROYO;
        /**
         * Android 2.3 版本
         */
        public static final int Sdk9 = VERSION_CODES.GINGERBREAD;
        /**
         * Android 2.3.3 版本
         */
        public static final int Sdk10 = VERSION_CODES.GINGERBREAD_MR1;
        /**
         * Android 3 版本
         */
        public static final int Sdk11 = VERSION_CODES.HONEYCOMB;
        /**
         * Android 3.1 版本
         */
        public static final int Sdk12 = VERSION_CODES.HONEYCOMB_MR1;
        /**
         * Android 3.2 版本
         */
        public static final int Sdk13 = VERSION_CODES.HONEYCOMB_MR2;
        /**
         * Android 4 版本
         */
        public static final int Sdk14 = VERSION_CODES.ICE_CREAM_SANDWICH;
        /**
         * Android 4.03 版本
         */
        public static final int Sdk15 = VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
        /**
         * Android 4.1 版本
         */
        public static final int Sdk16 = VERSION_CODES.JELLY_BEAN;
        /**
         * Android 4.2 版本
         */
        public static final int Sdk17 = VERSION_CODES.JELLY_BEAN_MR1;
        /**
         * Android 4.3 版本
         */
        public static final int Sdk18 = VERSION_CODES.JELLY_BEAN_MR2;
        /**
         * Android 4.4 版本
         */
        public static final int Sdk19 = VERSION_CODES.KITKAT;
        /**
         * Android 4.4W 版本
         */
        public static final int Sdk20 = VERSION_CODES.KITKAT_WATCH;
        /**
         * Android 5 版本
         */
        public static final int Sdk21 = VERSION_CODES.LOLLIPOP;
        /**
         * Android 5.1 版本
         */
        public static final int Sdk22 = VERSION_CODES.LOLLIPOP_MR1;
        /**
         * Android 6 版本
         */
        public static final int Sdk23 = VERSION_CODES.M;
        /**
         * Android 7 版本
         */
        public static final int Sdk24 = VERSION_CODES.N;
        /**
         * Android 7.1 版本
         */
        public static final int Sdk25 = VERSION_CODES.N_MR1;
        /**
         * Android 8 版本
         */
        public static final int Sdk26 = VERSION_CODES.O;
        /**
         * Android 8.1 版本
         */
        public static final int Sdk27 = VERSION_CODES.O_MR1;
        /**
         * Android 9 版本
         */
        public static final int Sdk28 = VERSION_CODES.P;
        /**
         * Android 10 版本
         */
        public static final int Sdk29 = VERSION_CODES.Q;
        /**
         * Android 11 版本
         */
        public static final int Sdk30 = VERSION_CODES.R;
        /**
         * Android 12 版本
         */
        public static final int Sdk31 = VERSION_CODES.S;
        /**
         * Android 12 L 版本
         */
        public static final int Sdk32 = VERSION_CODES.S_V2;
        /**
         * Android 13 版本
         */
        public static final int Sdk33 = VERSION_CODES.TIRAMISU;
        /**
         * Android 14 版本
         */
        public static final int Sdk34 = VERSION_CODES.UPSIDE_DOWN_CAKE;
        /**
         * Android 15 版本
         */
        public static final int Sdk35 = VERSION_CODES.VANILLA_ICE_CREAM;
        /**
         * Android 16 版本
         */
        public static final int Sdk36 = VERSION_CODES.BAKLAVA;
    }


    public static class 版本_代码_全 {

        /**
         * Android 1.0 版本
         */
        public static final int 基础 = Build.VERSION_CODES_FULL.BASE;
        /**
         * Android 1.1 版本
         */
        public static final int 基础_1_1 = Build.VERSION_CODES_FULL.BASE_1_1;
        /**
         * Android 3.0 版本
         */
        public static final int CUPCAKE = Build.VERSION_CODES_FULL.CUPCAKE;
        /**
         * Android 4.0 版本
         */
        public static final int DONUT = Build.VERSION_CODES_FULL.DONUT;
        /**
         * Android 5.0 版本
         */
        public static final int ECLAIR = Build.VERSION_CODES_FULL.ECLAIR;
        /**
         * Android 6.0 版本
         */
        public static final int ECLAIR_0_1 = Build.VERSION_CODES_FULL.ECLAIR_0_1;
        /**
         * Android 7.0 版本
         */
        public static final int ECLAIR_MR1 = Build.VERSION_CODES_FULL.ECLAIR_MR1;
        /**
         * Android 8.0 版本
         */
        public static final int FROYO = Build.VERSION_CODES_FULL.FROYO;
        /**
         * Android 9.0 版本
         */
        public static final int GINGERBREAD = Build.VERSION_CODES_FULL.GINGERBREAD;
        /**
         * Android 10.0 版本
         */
        public static final int GINGERBREAD_MR1 = Build.VERSION_CODES_FULL.GINGERBREAD_MR1;
        /**
         * Android 11.0 版本
         */
        public static final int HONEYCOMB = Build.VERSION_CODES_FULL.HONEYCOMB;
        /**
         * Android 12.0 版本
         */
        public static final int HONEYCOMB_MR1 = Build.VERSION_CODES_FULL.HONEYCOMB_MR1;
        /**
         * Android 13.0 版本
         */
        public static final int HONEYCOMB_MR2 = Build.VERSION_CODES_FULL.HONEYCOMB_MR2;
        /**
         * Android 14.0 版本
         */
        public static final int ICE_CREAM_SANDWICH = Build.VERSION_CODES_FULL.ICE_CREAM_SANDWICH;
        /**
         * Android 15.0 版本
         */
        public static final int ICE_CREAM_SANDWICH_MR1 = Build.VERSION_CODES_FULL.ICE_CREAM_SANDWICH_MR1;
        /**
         * Android 16.0 版本
         */
        public static final int JELLY_BEAN = Build.VERSION_CODES_FULL.JELLY_BEAN;
        /**
         * Android 17.0 版本
         */
        public static final int JELLY_BEAN_MR1 = Build.VERSION_CODES_FULL.JELLY_BEAN_MR1;
        /**
         * Android 18.0 版本
         */
        public static final int JELLY_BEAN_MR2 = Build.VERSION_CODES_FULL.JELLY_BEAN_MR2;
        /**
         * Android 19.0 版本
         */
        public static final int KITKAT = Build.VERSION_CODES_FULL.KITKAT;
        /**
         * Android 20.0 版本
         */
        public static final int KITKAT_WATCH = Build.VERSION_CODES_FULL.KITKAT_WATCH;
        /**
         * Android 21.0 版本
         */
        public static final int LOLLIPOP = Build.VERSION_CODES_FULL.LOLLIPOP;
        /**
         * Android 22.0 版本
         */
        public static final int LOLLIPOP_MR1 = Build.VERSION_CODES_FULL.LOLLIPOP_MR1;
        /**
         * Android 23.0 版本
         */
        public static final int M = Build.VERSION_CODES_FULL.M;
        /**
         * Android 24.0 版本
         */
        public static final int N = Build.VERSION_CODES_FULL.N;
        /**
         * Android 25.0 版本
         */
        public static final int N_MR1 = Build.VERSION_CODES_FULL.N_MR1;
        /**
         * Android 26.0 版本
         */
        public static final int O = Build.VERSION_CODES_FULL.O;
        /**
         * Android 27.0 版本
         */
        public static final int O_MR1 = Build.VERSION_CODES_FULL.O_MR1;
        /**
         * Android 28.0 版本
         */
        public static final int P = Build.VERSION_CODES_FULL.P;
        /**
         * Android 29.0 版本
         */
        public static final int Q = Build.VERSION_CODES_FULL.Q;
        /**
         * Android 30.0 版本
         */
        public static final int R = Build.VERSION_CODES_FULL.R;
        /**
         * Android 31.0 版本
         */
        public static final int S = Build.VERSION_CODES_FULL.S;
        /**
         * Android 32.0 版本
         */
        public static final int S_V2 = Build.VERSION_CODES_FULL.S_V2;
        /**
         * Android 33.0 版本
         */
        public static final int TIRAMISU = Build.VERSION_CODES_FULL.TIRAMISU;
        /**
         * Android 34.0 版本
         */
        public static final int UPSIDE_DOWN_CAKE = Build.VERSION_CODES_FULL.UPSIDE_DOWN_CAKE;
        /**
         * Android 35.0 版本
         */
        public static final int VANILLA_ICE_CREAM = Build.VERSION_CODES_FULL.VANILLA_ICE_CREAM;

        /**
         * Android 36.0 版本
         */
        public static final int BAKLAVA = Build.VERSION_CODES_FULL.BAKLAVA;

    }



    public static int 取主Sdk版本(int sdk整数全) {
        return Build.getMajorSdkVersion(sdk整数全);
    }

    public static int 取次Sdk版本(int sdk整数全) {
        return Build.getMinorSdkVersion(sdk整数全);
    }

    /** 构建类型，例如“user”或“eng”。 */
    public static final String 类型 = Build.TYPE;

    /** 用逗号分隔的标签描述构建，例如"unsigned,debug"。 */
    public static final String 标签 = Build.TAGS;

    /** 一个唯一标识此版本的字符串。请勿尝试解析此值。 */
    public static final String 指纹 = Build.FINGERPRINT;


    /** 该设备上已知问题的状态未知。 */
    @RequiresApi(api = Build.VERSION_CODES_FULL.BAKLAVA_1)
    public static final int 回移_修复_状态_未知 = Build.BACKPORTED_FIX_STATUS_UNKNOWN;

    /** 此设备上的已知问题已修复。 */
    @RequiresApi(api = Build.VERSION_CODES_FULL.BAKLAVA_1)
    public static final int 回移_修复_状态_固定 = Build.BACKPORTED_FIX_STATUS_FIXED;


    /**
     * 已知问题不适用于此设备。
     * <p>
     * 例如，如果问题仅影响特定品牌，其他品牌的设备将报告不适用。
     */
    @RequiresApi(api = Build.VERSION_CODES_FULL.BAKLAVA_1)
    public static final int 回移_修复_状态_不是_适用 = Build.BACKPORTED_FIX_STATUS_NOT_APPLICABLE;

    /** 此设备上的已知问题尚未修复。 */
    @RequiresApi(api = Build.VERSION_CODES_FULL.BAKLAVA_1)
    public static final int 回移_修复_状态_不是_固定 = Build.BACKPORTED_FIX_STATUS_NOT_FIXED;

    @RequiresApi(api = Build.VERSION_CODES_FULL.BAKLAVA_1)
    public static int 取回移修复状态(long id) {
        return Build.getBackportedFixStatus(id);
    }


    @RequiresApi(api = VERSION_CODES.Q)
    public static class 分区 {
        /** The name identifying the system partition. */
        public static final String PARTITION_NAME_SYSTEM = "system";
        /** @hide */
        public static final String PARTITION_NAME_BOOTIMAGE = "bootimage";
        /** @hide */
        public static final String PARTITION_NAME_ODM = "odm";
        /** @hide */
        public static final String PARTITION_NAME_OEM = "oem";
        /** @hide */
        public static final String PARTITION_NAME_PRODUCT = "product";
        /** @hide */
        public static final String PARTITION_NAME_SYSTEM_EXT = "system_ext";
        /** @hide */
        public static final String PARTITION_NAME_VENDOR = "vendor";

        private final String mName;
        private final String mFingerprint;
        private final long mTimeMs;

        private 分区(String name, String fingerprint, long timeMs) {
            mName = name;
            mFingerprint = fingerprint;
            mTimeMs = timeMs;
        }

        /** The name of this partition, e.g. "system", or "vendor" */
        public String getName() {
            return mName;
        }

        /** The build fingerprint of this partition, see {@link Build#FINGERPRINT}. */
        public String getFingerprint() {
            return mFingerprint;
        }

        /** The time (ms since epoch), at which this partition was built, see {@link Build#TIME}. */
        public long getBuildTimeMillis() {
            return mTimeMs;
        }

        @Override
        public boolean equals( Object o) {
            if (!(o instanceof 分区)) {
                return false;
            }
            分区 op = (分区) o;
            return mName.equals(op.mName)
                    && mFingerprint.equals(op.mFingerprint)
                    && mTimeMs == op.mTimeMs;
        }

        @Override
        public int hashCode() {
            return Objects.hash(mName, mFingerprint, mTimeMs);
        }
    }


    @RequiresApi(api = VERSION_CODES.Q)
    public static List<Partition> 取指纹分区() {
        return Build.getFingerprintedPartitions();
    }


    public static final long 时间 = Build.TIME;
    public static final String 用户 = Build.USER;
    public static final String 主机 = Build.HOST;

    /**
     * 返回无线电固件的版本字符串。如果无线电当前未开启，则可能返回 null。
     */
    public static String 取无线电版本() {
        return Build.getRadioVersion();
    }

}
