package example.newsclient.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

import example.newsclient.R;
import example.newsclient.adapter.VideoAdapter;
import example.newsclient.entity.VideoEntity;
import example.newsclient.util.UrlManager;

/**
 * 作者：国富小哥
 * 日期：2017/6/27
 * Created by Administrator
 */

public class Fragment2 extends BaseFragment{

    private RecyclerView mRecyclerView;
    private VideoAdapter mVideoAdapter;
    private List<VideoEntity.ResultBean> mResultBeanList;



    @Override
    public void initData() {
        //获取服务器数据
        getVideoDatas();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        mRecyclerView= (RecyclerView) mView.findViewById(R.id.vidiu_recycler_view);

    }

    @Override
    public int getLayoutId() {
       return R.layout.fragment2;
    }


    private void getVideoDatas() {
        HttpUtils utils = new HttpUtils();
        //发送请求视频的url
        utils.send(HttpRequest.HttpMethod.GET, UrlManager.VideoURL,
                new RequestCallBack<String>() {

                    @Override   // 请求成功
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        // （1）服务器返回的json数据
                        String json = responseInfo.result;
                        System.out.println("---------json: " + json);

                        // （2）解析json数据
                        // 替换json字符串中的新闻类别id
                        json = json.replace("V9LG4B3A0", "Result");
                        Gson gson = new Gson();         // 使用到反射  fastjson
                        VideoEntity entity = gson.fromJson(json, VideoEntity.class);
                        int count = entity.getResult().size();
                        System.out.println("--------解析：" + count + " 个视频");
                        //服务器返回的数据集合
                        mResultBeanList=entity.getResult();
                        //显示到列表上
                        showVideoList(mResultBeanList);

                    }

                    @Override   // 请求失败
                    public void onFailure(HttpException error, String msg) {
                        System.out.println("---------error: " + error);
                    }
                });
    }

    private void showVideoList(List<VideoEntity.ResultBean> resultBeanList) {
        //创建适配器
        mVideoAdapter=new VideoAdapter(getContext(),resultBeanList);
        //给recyclerview设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置适配器
        mRecyclerView.setAdapter(mVideoAdapter);
    }
}
