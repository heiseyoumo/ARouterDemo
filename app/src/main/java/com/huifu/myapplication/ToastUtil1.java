package com.huifu.myapplication;

import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author pengkuanwang
 * @date 2018/1/15
 */

public class ToastUtil1 {
    private static Toast toast;

    /**
     * 一般的toast提示
     *
     * @param textStr 提示信息
     */
    public static void showCustomToast(String textStr) {
        if (TextUtils.isEmpty(textStr)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(MyApplication.instance, textStr, Toast.LENGTH_SHORT);
        } else {
            toast.setText(textStr);
        }
        toast.show();
    }
}
