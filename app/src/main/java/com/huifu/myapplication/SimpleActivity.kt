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
    var age: Int = 0

    @Autowired
    lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        textView.text = "姓名:$name,年龄:$age,别名:$aliasName,年龄:$age"
        textView.append("\n\n\n\n")
        textView.append("姓名:${person.name},年龄:${person.age}")
    }
}