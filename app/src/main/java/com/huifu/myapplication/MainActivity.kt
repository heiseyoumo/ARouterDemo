package com.huifu.myapplication

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    val TAG: String = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setOnClickListener {
            /**
             * Activity的普通跳转
             */
            var bundle = Bundle()
            var person = Person("彭宽旺", 18)
            var person1 = Person("彭大爷1", 20)
            var person2 = Person("彭大爷2", 20)
            bundle.putString("name", person.name)
            bundle.putString("name1", person1.name)
            ARouter.getInstance()
                .build(CoreRouterPath.SIMPLE)
                /**
                 *或者是通过uri进行跳转build(Uri.parse(CoreRouterPath.SIMPLE))
                 */
                .with(bundle)
                .withSerializable("person", person2)
                .navigation()
        }

        textView1.setOnClickListener {
            ARouter.getInstance().build(CoreRouterPath.DEMO).navigation(this, object :
                NavigationCallback {
                override fun onLost(postcard: Postcard) {
                    /**
                     * 路径找不到会执行此方法,只会执行此方法
                     */
                    Log.d(TAG, "onLost")
                }

                override fun onFound(postcard: Postcard) {
                    /**
                     * 正常情况下和拦截器进行拦截都会执行此方法
                     */
                    Log.d(TAG, "onFound")
                }

                override fun onInterrupt(postcard: Postcard) {
                    /**
                     * 拦截器拦截会执行此方法
                     */
                    Log.d(TAG, "onInterrupt")
                }

                override fun onArrival(postcard: Postcard) {
                    /**
                     * 只有执行成功后才会执行此方法
                     */
                    Log.d(TAG, "onArrival")
                }

            })
        }
    }
}
