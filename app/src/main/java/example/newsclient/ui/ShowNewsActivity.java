package example.newsclient.ui;

import android.content.Intent;
import android.view.KeyEvent;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import example.newsclient.R;
import example.newsclient.entity.NewsEntity;

/**
 * 作者：国富小哥
 * 日期：2017/6/29
 * Created by Administrator
 *
 * 新闻展示
 */

public class ShowNewsActivity extends BaseActivity {
    private WebView mWebView;


    @Override
    public void initData() {
        Intent intent=getIntent();
        NewsEntity.ResultBean resultBean= (NewsEntity.ResultBean) intent.getSerializableExtra("news");
        String url=resultBean.getUrl();

        //设置浏览器的属性，让webview支持javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        //让目标网页仍然在当前webview显示
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                if(i == 100) {
                    //   Toast.makeText(ShowNewsActivity.this, "网页加载完成", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        mWebView= (WebView) findViewById(R.id.web_view);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if(mWebView.canGoBack())
            {
                mWebView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }

        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.activity_show;
    }
}
