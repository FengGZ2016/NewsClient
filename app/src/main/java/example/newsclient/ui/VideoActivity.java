package example.newsclient.ui;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

import example.newsclient.R;

/**
 * 作者：国富小哥
 * 日期：2017/6/29
 * Created by Administrator
 *
 * 视频详情页面
 */

public class VideoActivity extends BaseActivity {

    private VideoView mVideoView;
    private ProgressBar mProgressBar;


    @Override
    public void initData() {
        //获取视频数据的url
        String video_url=getIntent().getStringExtra("video_url");
        //设置视频播放的路径
        mVideoView.setVideoPath(video_url);
        //设置监听器，监听缓冲状态
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           //视频缓冲完成
            @Override
            public void onPrepared(MediaPlayer mp) {
                //开始播放视频
                mVideoView.start();
                //隐藏缓冲进度条
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        mVideoView= (VideoView) findViewById(R.id.video_view);
        mProgressBar= (ProgressBar) findViewById(R.id.pb_01);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_video;
    }
}
