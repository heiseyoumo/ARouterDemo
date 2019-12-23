package com.huifu.myapplication

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 * @author pengkuanwang
 * @date 2019-12-23
 */
@Interceptor(priority = 8)
class UseIInterceptor : IInterceptor {
    override fun process(postcard: Postcard, callback: InterceptorCallback?) {
        Log.d(Constant.TAG, "UseIInterceptor 拦截器开始执行,线程名字:${Thread.currentThread().name}")
    }

    override fun init(context: Context?) {
        Log.d(Constant.TAG, "UseIInterceptor 拦截器  init")
    }
}