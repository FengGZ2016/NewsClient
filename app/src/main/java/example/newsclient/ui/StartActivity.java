package example.newsclient.ui;

import android.content.Intent;

import example.newsclient.R;

public class StartActivity extends BaseActivity {



    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
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
