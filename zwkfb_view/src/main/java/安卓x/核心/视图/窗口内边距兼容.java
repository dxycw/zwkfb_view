package 安卓x.核心.视图;

import androidx.core.view.WindowInsetsCompat;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 创建时间：2025年11月18日.
 * <p>
 * 描述：窗口内边距兼容
 * @author dxyc
 */
public class 窗口内边距兼容 extends WindowInsetsCompat {

    public 窗口内边距兼容(@Nullable WindowInsetsCompat 源) {
        super(源);
    }

    public static final class 构建器 { // Builder
        private Builder m构建器;

        public 构建器() {
            this.m构建器 = new Builder();
        }

        public 构建器(@NonNull WindowInsetsCompat insets) {
            this.m构建器 = new Builder(insets);
        }


    }


}
