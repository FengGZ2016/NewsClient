package example.newsclient.ui;

import android.widget.TextView;

import example.newsclient.R;

/**
 * 作者：国富小哥
 * 日期：2017/6/28
 * Created by Administrator
 */

public class NewsFragment extends BaseFragment{
    /** 新闻类别id */
    private String channelId;
    private TextView textView ;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        textView= (TextView) mView.findViewById(R.id.tv_01);
        textView.setText(channelId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }
}
