package example.newsclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：国富小哥
 * 日期：2017/6/26
 * Created by Administrator
 */

public abstract class BaseActivity extends AppCompatActivity{

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(getLayoutResId());
//        initView();
//        initListener();
//       initData();
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView();
        initListener();
        initData();
    }

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    
    /**
     * 抽象方法
     * 子类必须实现，返回一个activity布局
     * */
    public abstract int getLayoutResId();
}
