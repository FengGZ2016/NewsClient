package example.newsclient.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：国富小哥
 * 日期：2017/6/27
 * Created by Administrator
 */

public abstract class BaseFragment extends Fragment{
    private View mView;
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      if (mView==null){
          mView=LayoutInflater.from(mActivity).inflate(getLayoutId(),container,false);
          initView();
          initListener();
          initData();
      }
        return mView;
    }

    public abstract void initData();

    public abstract void initListener();


    public abstract void initView();


    public abstract int getLayoutId();

}
