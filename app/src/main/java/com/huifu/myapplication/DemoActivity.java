package com.huifu.myapplication;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * @author pengkuanwang
 * @date 2019-12-23
 */
@Route(path = CoreRouterPath.DEMO)
public class DemoActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
    }
}
