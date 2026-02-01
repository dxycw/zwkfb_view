package 安卓.os

import android.annotation.SuppressLint
import android.os.Build

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：构建
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 构建 : Build{
    constructor() : super()

    companion object{

        fun 架构(): Array<String> {
            return SUPPORTED_ABIS // 获取手机架构 例如：MTK
        }
        /**
         * 获取手机品牌
         */
        fun 品牌(): String? {
            return BRAND // 获取手机品牌 例如：Xiaomi
        }

        /**
         * 获取手机制造商
         */
        fun 制造商(): String? {
            return MANUFACTURER // 获取手机制造商 例如：Xiaomi
        }

        /**
         * 获取手机型号
         */
        fun 型号(): String? {
            return MODEL // 获取手机型号 例如：MI 6
        }

        /**
         * 获取设备序列号
         */
        @SuppressLint("HardwareIds")
        fun 序列号(): String? {
            return SERIAL // 获取设备序列号 例如：9d0c0d0c0d0c0d0c
        }
    }

    object 版本 {
        val Sdk : String = VERSION.SDK
        val Sdk版本号 : Int  = VERSION.SDK_INT

        fun 系统版本号(): String? {
            return VERSION.RELEASE // 获取系统版本号 例如：8.0
        }
    }

    object 版本码 {
        /**
         * Android 1 版本
         */
        const val Sdk1 : Int = VERSION_CODES.BASE
        /**
         * Android 1.1 版本
         */
        const val Sdk2 : Int = VERSION_CODES.BASE_1_1
        /**
         * Android 1.5 版本
         */
        const val Sdk3 : Int = VERSION_CODES.CUPCAKE
        /**
         * Android 1.6 版本
         */
        const val Sdk4 : Int = VERSION_CODES.DONUT
        /**
         * Android 2 版本
         */
        const val Sdk5 : Int = VERSION_CODES.ECLAIR
        /**
         * Android 2.0.1 版本
         */
        const val Sdk6 : Int = VERSION_CODES.ECLAIR_0_1
        /**
         * Android 2.1 版本
         */
        const val Sdk7 : Int = VERSION_CODES.ECLAIR_MR1
        /**
         * Android 2.2 版本
         */
        const val Sdk8 : Int = VERSION_CODES.FROYO
        /**
         * Android 2.3 版本
         */
        const val Sdk9 : Int = VERSION_CODES.GINGERBREAD
        /**
         * Android 2.3.3 版本
         */
        const val Sdk10 : Int = VERSION_CODES.GINGERBREAD_MR1
        /**
         * Android 3 版本
         */
        const val Sdk11 : Int = VERSION_CODES.HONEYCOMB
        /**
         * Android 3.1 版本
         */
        const val Sdk12 : Int = VERSION_CODES.HONEYCOMB_MR1
        /**
         * Android 3.2 版本
         */
        const val Sdk13 : Int = VERSION_CODES.HONEYCOMB_MR2
        /**
         * Android 4 版本
         */
        const val Sdk14 : Int = VERSION_CODES.ICE_CREAM_SANDWICH
        /**
         * Android 4.03 版本
         */
        const val Sdk15 : Int = VERSION_CODES.ICE_CREAM_SANDWICH_MR1
        /**
         * Android 4.1 版本
         */
        const val Sdk16 : Int = VERSION_CODES.JELLY_BEAN
        /**
         * Android 4.2 版本
         */
        const val Sdk17 : Int = VERSION_CODES.JELLY_BEAN_MR1
        /**
         * Android 4.3 版本
         */
        const val Sdk18 : Int = VERSION_CODES.JELLY_BEAN_MR2
        /**
         * Android 4.4 版本
         */
        const val Sdk19 : Int = VERSION_CODES.KITKAT
        /**
         * Android 4.4W 版本
         */
        const val Sdk20 : Int = VERSION_CODES.KITKAT_WATCH
        /**
         * Android 5 版本
         */
        const val Sdk21 : Int = VERSION_CODES.LOLLIPOP
        /**
         * Android 5.1 版本
         */
        const val Sdk22 : Int = VERSION_CODES.LOLLIPOP_MR1
        /**
         * Android 6 版本
         */
        const val Sdk23 : Int = VERSION_CODES.M
        /**
         * Android 7 版本
         */
        const val Sdk24 : Int = VERSION_CODES.N
        /**
         * Android 7.1 版本
         */
        const val Sdk25 : Int = VERSION_CODES.N_MR1
        /**
         * Android 8 版本
         */
        const val Sdk26 : Int = VERSION_CODES.O
        /**
         * Android 8.1 版本
         */
        const val Sdk27 : Int = VERSION_CODES.O_MR1
        /**
         * Android 9 版本
         */
        const val Sdk28 : Int = VERSION_CODES.P
        /**
         * Android 10 版本
         */
        const val Sdk29 : Int = VERSION_CODES.Q
        /**
         * Android 11 版本
         */
        const val Sdk30 : Int = VERSION_CODES.R
        /**
         * Android 12 版本
         */
        const val Sdk31 : Int = VERSION_CODES.S
        /**
         * Android 12 L 版本
         */
        const val Sdk32 : Int = VERSION_CODES.S_V2
        /**
         * Android 13 版本
         */
        const val Sdk33 : Int = VERSION_CODES.TIRAMISU
        /**
         * Android 14 版本
         */
        const val Sdk34 : Int = VERSION_CODES.UPSIDE_DOWN_CAKE
        /**
         * Android 15 版本
         */
        const val Sdk35 : Int = VERSION_CODES.VANILLA_ICE_CREAM
        /**
         * Android 16 版本
         */
        const val Sdk36 : Int = VERSION_CODES.BAKLAVA
    }

    object 版本码全 {
        /**
         * Android 1.0 版本
         */
        const val BASE = VERSION_CODES_FULL.BASE
        /**
         * Android 1.1 版本
         */
        const val BASE_1_1 = VERSION_CODES_FULL.BASE_1_1
        /**
         * Android 3.0 版本
         */
        const val CUPCAKE = VERSION_CODES_FULL.CUPCAKE
        /**
         * Android 4.0 版本
         */
        const val DONUT = VERSION_CODES_FULL.DONUT
        /**
         * Android 5.0 版本
         */
        const val ECLAIR = VERSION_CODES_FULL.ECLAIR
        /**
         * Android 6.0 版本
         */
        const val ECLAIR_0_1 = VERSION_CODES_FULL.ECLAIR_0_1
        /**
         * Android 7.0 版本
         */
        const val ECLAIR_MR1 = VERSION_CODES_FULL.ECLAIR_MR1
        /**
         * Android 8.0 版本
         */
        const val FROYO = VERSION_CODES_FULL.FROYO
        /**
         * Android 9.0 版本
         */
        const val GINGERBREAD = VERSION_CODES_FULL.GINGERBREAD
        /**
         * Android 10.0 版本
         */
        const val GINGERBREAD_MR1 = VERSION_CODES_FULL.GINGERBREAD_MR1
        /**
         * Android 11.0 版本
         */
        const val HONEYCOMB = VERSION_CODES_FULL.HONEYCOMB
        /**
         * Android 12.0 版本
         */
        const val HONEYCOMB_MR1 = VERSION_CODES_FULL.HONEYCOMB_MR1
        /**
         * Android 13.0 版本
         */
        const val HONEYCOMB_MR2 = VERSION_CODES_FULL.HONEYCOMB_MR2
        /**
         * Android 14.0 版本
         */
        const val ICE_CREAM_SANDWICH = VERSION_CODES_FULL.ICE_CREAM_SANDWICH
        /**
         * Android 15.0 版本
         */
        const val ICE_CREAM_SANDWICH_MR1 = VERSION_CODES_FULL.ICE_CREAM_SANDWICH_MR1
        /**
         * Android 16.0 版本
         */
        const val JELLY_BEAN = VERSION_CODES_FULL.JELLY_BEAN
        /**
         * Android 17.0 版本
         */
        const val JELLY_BEAN_MR1 = VERSION_CODES_FULL.JELLY_BEAN_MR1
        /**
         * Android 18.0 版本
         */
        const val JELLY_BEAN_MR2 = VERSION_CODES_FULL.JELLY_BEAN_MR2
        /**
         * Android 19.0 版本
         */
        const val KITKAT = VERSION_CODES_FULL.KITKAT
        /**
         * Android 20.0 版本
         */
        const val KITKAT_WATCH = VERSION_CODES_FULL.KITKAT_WATCH
        /**
         * Android 21.0 版本
         */
        const val LOLLIPOP = VERSION_CODES_FULL.LOLLIPOP
        /**
         * Android 22.0 版本
         */
        const val LOLLIPOP_MR1 = VERSION_CODES_FULL.LOLLIPOP_MR1
        /**
         * Android 23.0 版本
         */
        const val M = VERSION_CODES_FULL.M
        /**
         * Android 24.0 版本
         */
        const val N = VERSION_CODES_FULL.N
        /**
         * Android 25.0 版本
         */
        const val N_MR1 = VERSION_CODES_FULL.N_MR1
        /**
         * Android 26.0 版本
         */
        const val O = VERSION_CODES_FULL.O
        /**
         * Android 27.0 版本
         */
        const val O_MR1 = VERSION_CODES_FULL.O_MR1
        /**
         * Android 28.0 版本
         */
        const val P = VERSION_CODES_FULL.P
        /**
         * Android 29.0 版本
         */
        const val Q = VERSION_CODES_FULL.Q
        /**
         * Android 30.0 版本
         */
        const val R = VERSION_CODES_FULL.R
        /**
         * Android 31.0 版本
         */
        const val S = VERSION_CODES_FULL.S
        /**
         * Android 32.0 版本
         */
        const val S_V2 = VERSION_CODES_FULL.S_V2
        /**
         * Android 33.0 版本
         */
        const val TIRAMISU = VERSION_CODES_FULL.TIRAMISU
        /**
         * Android 34.0 版本
         */
        const val UPSIDE_DOWN_CAKE = VERSION_CODES_FULL.UPSIDE_DOWN_CAKE
        /**
         * Android 35.0 版本
         */
        const val VANILLA_ICE_CREAM = VERSION_CODES_FULL.VANILLA_ICE_CREAM
        /**
         * Android 36.0 版本
         */
        const val BAKLAVA = VERSION_CODES_FULL.BAKLAVA
    }

}