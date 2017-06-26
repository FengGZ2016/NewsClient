package example.newsclient.ui;

import android.content.Intent;
import android.os.Bundle;

import example.newsclient.R;

public class StartActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Thread.sleep(1500);
                   enterGuiActivity();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }

    private void enterGuiActivity() {
        Intent intent=new Intent(StartActivity.this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_start;
    }
}
