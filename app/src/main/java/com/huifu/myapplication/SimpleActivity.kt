package com.huifu.myapplication

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_simple.*

/**
 * @author pengkuanwang
 * @date 2019-12-23
 */
@Route(path = CoreRouterPath.SIMPLE)
class SimpleActivity : BaseActivity() {

    @Autowired
    lateinit var name: String

    @Autowired(name = "name1")
    lateinit var aliasName: String

    @Autowired
    lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        textView.append("name:$name")
        textView.append("\n\n")
        textView.append("name:$aliasName")
        textView.append("\n\n")
        textView.append("name3:${person.name},年龄:${person.age}")
    }
}