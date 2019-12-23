package com.huifu.myapplication

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setOnClickListener {
            /**
             * Activity的普通跳转
             */
            var bundle = Bundle()
            var person = Person("彭宽旺", 18)
            var person1 = Person("彭大爷", 18)
            bundle.putString("name", person.name)
            bundle.putString("name1", person1.name)
            ARouter.getInstance().build(CoreRouterPath.SIMPLE).navigation()
        }
    }
}
