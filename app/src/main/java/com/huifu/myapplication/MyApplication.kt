package com.huifu.myapplication

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author pengkuanwang
 * @date 2019-12-23
 */
class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            /**
             * 下面两行代码必须在init之前使用,否则这些配置在init过程中则无效
             */
            ARouter.openLog()
            ARouter.openDebug()
        }
        /**
         * 官方推荐在Application中进行初始化
         */
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}