package example.newsclient.ui;

import android.os.Bundle;

import example.newsclient.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}
