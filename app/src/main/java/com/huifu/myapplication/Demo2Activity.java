package com.huifu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * @author pengkuanwang
 * @date 2019-12-24
 */
@Route(path = CoreRouterPath.DEMO2)
public class Demo2Activity extends BaseActivity {
    public static final String RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo2);
    }

    public void onButtonClick(View view) {
        Intent data = new Intent();
        data.putExtra(RESULT, "success");
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
