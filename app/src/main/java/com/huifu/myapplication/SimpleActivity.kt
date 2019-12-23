package com.huifu.myapplication

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * @author pengkuanwang
 * @date 2019-12-23
 */
@Route(path = CoreRouterPath.SIMPLE)
class SimpleActivity : BaseActivity() {

    @Autowired
    lateinit var name: String

    @Autowired
    lateinit var name1: String

    @Autowired
    lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        println("$name,$name1")
        println(person.name)
    }
}