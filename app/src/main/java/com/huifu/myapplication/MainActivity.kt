package com.huifu.myapplication

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = CoreRouterPath.MAIN)
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setOnClickListener {
            /**
             * Activity的普通跳转
             */
            ARouter.getInstance().build(CoreRouterPath.SIMPLE).navigation()
        }
    }
}
