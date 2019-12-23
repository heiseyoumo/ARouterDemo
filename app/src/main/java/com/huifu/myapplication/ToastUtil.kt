package com.huifu.myapplication

import android.widget.Toast

/**
 * @author pengkuanwang
 * @date 2019-12-23
 */
class ToastUtil {
    private lateinit var toast: Toast
    /**
     * 一般的toast提示信息
     * @param content 提示信息内容
     */
    fun showCustomToast(content: String) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.instance, content, Toast.LENGTH_LONG)
        } else {
            toast.setText(content)
        }
        toast.show()
    }
}