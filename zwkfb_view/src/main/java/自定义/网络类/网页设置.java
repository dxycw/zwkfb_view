package 自定义.网络类;

import 安卓.操作系统.构建;

public class 网页设置 {

    public static class Android {
        public final static String 夸克UA =
                "Mozilla/5.0 (Linux; U; Android " + 构建.版本.发布版本 + "; zh-CN; " + 构建.型号 + " Build/AP3A.240905.015.A1) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/123.0.6312.80 Quark/7.5.1.691 Mobile Safari/537.36";
        public final static String EdgUA =
                "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Mobile Safari/537.36 EdgA/131.0.0.0";
        public final static String 百度UA =
                "Mozilla/5.0 (Linux; Android " + 构建.版本.发布版本  + "; " + 构建.型号 + " Build/AP3A.240905.015.A1; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/97.0.4692.98 Mobile Safari/537.36 T7/13.75 SP-engine/2.81.0 matrixstyle/0 lite baiduboxapp/6.43.0.11 (Baidu; P1 15) NABar/1.0";
    }

    public static class IOS{

    }

    public static class MacOS{

    }

    public static class Windows {
        public final static String 夸克UA =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/1.9.5.161";
        public final static String EdgUA =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 Edg/132.0.0.0";
    }



}
