package example.newsclient.ui;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import example.newsclient.R;

public class GuideActivity extends BaseActivity {
    private ImageView mImageView_start;
    private Button mButton_start;
    //图片资源
    private int[] imagesArray = new int[] {
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };
    //图片的下标
    private int index=0;

    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==1){
                startAnimation();
            }
        return false;
        }
    });
    //
    private MediaPlayer mMediaPlayer;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(getLayoutResId());
//       // initView();
//        initHandler();
//    }

    private void initHandler() {

    }


    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        initHandler();
        mImageView_start= (ImageView) findViewById(R.id.image_gui);
        mButton_start= (Button) findViewById(R.id.btn_start);

        mButton_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        startAnimation();
    }

    private void startAnimation() {
        index++;
        index=index%imagesArray.length;
        mImageView_start.setBackgroundResource(imagesArray[index]);

        //将空间恢复为原来的大小的1倍倍
        mImageView_start.setScaleX(1.0f);
        mImageView_start.setScaleY(1.0f);

        mImageView_start.animate()
                .scaleX(1.2f)//宽度放大1.2倍
                .scaleY(1.2f)//高度放大1.2倍
                .setDuration(3000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //结束,延迟1秒后发消息
                        mHandler.sendEmptyMessageDelayed(1,1000);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //播放音频
        playBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止播放音频
        stopMusic();
    }

    private void playBackgroundMusic(){
        try {
            if(mMediaPlayer==null){
                mMediaPlayer=MediaPlayer.create(this, R.raw.new_version);
            }
            //引入MP3文件
           // AssetFileDescriptor descriptor=getAssets().openFd("new_version.mp3");
           // mMediaPlayer.setDataSource(descriptor.getFileDescriptor(),0L,descriptor.getLength());
            //循环
            mMediaPlayer.setLooping(true);
            //左声道右声道
            mMediaPlayer.setVolume(1.0f,1.0f);
            //开始播放
            mMediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopMusic(){
        if (mMediaPlayer!=null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer=null;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_guide;
    }
}
