package example.newsclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.newsclient.R;
import example.newsclient.entity.VideoEntity;
import example.newsclient.ui.VideoActivity;

/**
 * 作者：国富小哥
 * 日期：2017/6/29
 * Created by Administrator
 */

public class VideoAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<VideoEntity.ResultBean> mResultBeanList;
    private  VideoHolder videoHolder;

    public VideoAdapter(Context mContext,List<VideoEntity.ResultBean> mResultBeanList){
        this.mContext=mContext;
        this.mResultBeanList=mResultBeanList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.video_item,parent,false);
        videoHolder=new VideoHolder(view);

        return videoHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        videoHolder= (VideoHolder) holder;
        final VideoEntity.ResultBean resultBean=mResultBeanList.get(position);

        //加载缩略图
        Picasso.with(mContext).load(resultBean.getCover()).into(videoHolder.ivVideoImage);
        // 显示标题
        videoHolder.tvVideoTitle.setText(resultBean.getTitle());
        // 显示视频播放时长
        String durationStr = DateFormat.format("mm:ss", resultBean.getLength() * 1000).toString();
        videoHolder.tvVideoDuration.setText(durationStr);
        // 显示播放次数
        videoHolder.tvPlayCount.setText(resultBean.getPlayCount() + "");

        //给item设置点击事件
        videoHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra("video_url", resultBean.getMp4_url());
                //跳转到视频播放页面
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mResultBeanList==null)?0:mResultBeanList.size();
    }

    class VideoHolder extends RecyclerView.ViewHolder{
        private ImageView ivVideoImage;
        private TextView tvVideoTitle;
        private TextView tvVideoDuration;
        private TextView tvPlayCount;

        public VideoHolder(View itemView) {
            super(itemView);

            ivVideoImage = (ImageView) itemView.findViewById(R.id.iv_video_image);
            tvVideoTitle = (TextView) itemView.findViewById(R.id.tv_video_title);
            tvVideoDuration = (TextView) itemView.findViewById(R.id.tv_video_duration);
            tvPlayCount = (TextView) itemView.findViewById(R.id.tv_play_count);
        }
    }
}
