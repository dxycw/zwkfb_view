package 商业.谷歌.安卓.材质.时间选择器;

import android.text.InputFilter;
import android.text.Spanned;

/** A {@link InputFilter} that prevents a value bigger that {@code max} from being entered */
class MaxInputValidator implements InputFilter {
    private int max;

    public MaxInputValidator(int max) {
        this.max = max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    @Override
    public CharSequence filter(
            CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            StringBuilder builder = new StringBuilder(dest);
            builder.replace(dstart, dend, source.subSequence(start, end).toString());
            String newVal = builder.toString();
            int input = Integer.parseInt(newVal);
            if (input <= max) {
                return null;
            }
        } catch (NumberFormatException ok) {
            // Just ignored if we couldn't parse the number
        }
        return "";
    }
}
