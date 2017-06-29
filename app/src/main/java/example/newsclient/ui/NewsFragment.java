package example.newsclient.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
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
    private List<NewsEntity.ResultBean> listDatas=new ArrayList<>();

    //下拉刷新控件
    private SpringView mSpringView;


    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void initData() {
        getServerData(true);
    }

    /** 要加载第几页数据 */
    private int pageNo = 1;
    /**
     * 获取服务器数据
     * @param refresh true表示下拉刷新，false表示加载下一页数据
     * */
    private void getServerData(final boolean refresh) {
        if (refresh)
            // 如果是下拉刷新
            pageNo = 1;


        //通过channelId获取请求数据的URL
       String url= UrlManager.getUrl(channelId, pageNo);
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
               listDatas = newsEntity.getResult();


                if (refresh) {
                    // 下拉刷新
                    showDatas(listDatas);
                } else {
                    // 上拉加载下一页数据
                    mNewsAdapter.appendDatas(listDatas);
                }
                pageNo++;       // 页码自增1

                //  隐藏SpringView的下拉和上拉显示
                mSpringView.onFinishFreshAndLoad();
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
        //首先显示轮播图
        // 轮播图在第一条新闻
        NewsEntity.ResultBean firstNews = mNewsEntity.get(0);
        //先判断有无轮播图
        if (firstNews.getAds()!=null&&firstNews.getAds().size()>0){
            //有轮播图
            //加载轮播图的布局文件
            View headerView= LayoutInflater.from(getContext()).inflate(R.layout.head_list,mListView,false);

            //查找轮播图控件
            SliderLayout sliderLayout= (SliderLayout) headerView.findViewById(R.id.slider_layout);
            //轮播图的数据源
            List<NewsEntity.ResultBean.AdsBean> adsBeanList=firstNews.getAds();
            //添加轮播图子界面
            for (int i=0;i<adsBeanList.size();i++){
                NewsEntity.ResultBean.AdsBean adsBean=adsBeanList.get(i);
                //一个TextSliderView表示一个子界面
                TextSliderView textSliderView=new TextSliderView(getContext());
                //显示轮播图的标题
                textSliderView.description(adsBean.getTitle())
                        .image(adsBean.getImgsrc());

                //添加一个子界面
                sliderLayout.addSlider(textSliderView);
            }

            //把轮播图添加到listView的头部
            mListView.addHeaderView(headerView);
        }else {
            //没有轮播图

        }
        // 重置列表的数据，刷新列表显示
        mNewsAdapter.setDatas(listDatas);


    }

    @Override
    public void initListener() {
        //给listView设置监听
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 列表添加了头部后，第一条新闻数据的索引是从1开始，所以要减1
                position--;
                NewsEntity.ResultBean resultBean=listDatas.get(position);

                Intent intent = new Intent(getActivity(), ShowNewsActivity.class);
                intent.putExtra("news", resultBean);    // 新闻数据传到新闻详情界面
                startActivity(intent);
            }
        });
    }

    @Override
    public void initView() {
        mListView= (ListView) mView.findViewById(R.id.list_view);
        //创建Adapter
        mNewsAdapter=new NewsAdapter(getContext(),listDatas);
        mListView.setAdapter(mNewsAdapter);
        initSpring();
    }

    /**
     * 初始化下拉刷新控件
     * */
    private void initSpring() {
        mSpringView= (SpringView) mView.findViewById(R.id.spring_view);
        //设置头部
        mSpringView.setHeader(new MeituanHeader(getContext()));
        //设置尾部
        mSpringView.setFooter(new DefaultFooter(getContext()));
        //设置样式
        mSpringView.setType(SpringView.Type.FOLLOW);

        //设置监听器
        mSpringView.setListener(new SpringView.OnFreshListener() {
            // 下拉，刷新第一页数据
            @Override
            public void onRefresh() {
                // 请求服务器第一页数据,然后刷新
                getServerData(true);
            }

            // 上拉，刷新下一页页数据
            @Override
            public void onLoadmore() {
                getServerData(false);
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }
}
