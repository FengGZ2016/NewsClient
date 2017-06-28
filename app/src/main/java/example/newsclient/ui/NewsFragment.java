package example.newsclient.ui;

import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

import example.newsclient.R;
import example.newsclient.adapter.NewsAdapter;
import example.newsclient.entity.NewsEntity;
import example.newsclient.util.UrlManager;

/**
 * 作者：国富小哥
 * 日期：2017/6/28
 * Created by Administrator
 */

public class NewsFragment extends BaseFragment{
    /** 新闻类别id */
    private String channelId;
    private ListView mListView;
    private NewsAdapter mNewsAdapter;


    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void initData() {
        getServerData();
    }

    /**
     * 获取服务器数据
     * */
    private void getServerData() {
        //通过channelId获取请求数据的URL
       String url= UrlManager.getUrl(channelId);
        HttpUtils httpUtils=new HttpUtils();
        //发送请求
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //成功返回数据
                String json = responseInfo.result;
                System.out.println("----服务器返回的json数据:" + json);

                // 替换json字符串中的新闻类别id
                json = json.replace(channelId, "Result");
                Gson gson = new Gson();
                NewsEntity newsEntity = gson.fromJson(json, NewsEntity.class);
                int count = newsEntity.getResult().size();
                System.out.println("--------解析：" + count + " 条新闻");
                // 列表显示的数据集合
                List<NewsEntity.ResultBean> listDatas = newsEntity.getResult();

                // 显示数据到列表中
                showDatas(listDatas);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                //请求失败
                e.printStackTrace();
            }
        });
    }

    /**
     * 显示数据到列表中
     *
     * @param mNewsEntity*/
    private void showDatas(List<NewsEntity.ResultBean> mNewsEntity) {
    //创建Adapter
        mNewsAdapter=new NewsAdapter(getContext(),mNewsEntity);
        mListView.setAdapter(mNewsAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        mListView= (ListView) mView.findViewById(R.id.list_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }
}
