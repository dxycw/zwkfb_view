package 商业.谷歌.安卓.材质.时间选择器;

interface TimePickerPresenter {

    /** Do any final initialization */
    void initialize();

    /** Refresh the data in the view based on the model */
    void invalidate();

    void hide();

    void show();
}
