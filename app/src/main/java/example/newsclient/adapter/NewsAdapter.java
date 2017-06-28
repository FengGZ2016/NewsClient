package example.newsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.newsclient.R;
import example.newsclient.entity.NewsEntity;

/**
 * 作者：国富小哥
 * 日期：2017/6/28
 * Created by Administrator
 */

public class NewsAdapter extends BaseAdapter{

    //上下文
    private Context mContext;
    //数据源
    private List<NewsEntity.ResultBean> mResultBeanList;

    public NewsAdapter(Context mContext,List<NewsEntity.ResultBean> mResultBeanList){
        this.mContext=mContext;
        this.mResultBeanList=mResultBeanList;
    }

    @Override
    public int getCount() {
        return (mResultBeanList==null)?0:mResultBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //列表项数据
      NewsEntity.ResultBean resultBeanInfo=mResultBeanList.get(position);
       View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view = LayoutInflater.from(mContext).inflate(
                    R.layout.news_item1, parent,false);

            viewHolder=new ViewHolder();
            //绑定控件
            viewHolder.ivIcon= (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.tvTitle= (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tvSource= (TextView) view.findViewById(R.id.tv_source);
            viewHolder.tvComment= (TextView) view.findViewById(R.id.tv_comment);
            //将viewHolder存储在view中
            view.setTag(viewHolder);

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        // 4. 显示列表项中的子控件
        viewHolder.tvTitle.setText(resultBeanInfo.getTitle());           // 显示标题
        viewHolder.tvSource.setText(resultBeanInfo.getSource());           // 新闻来源
        viewHolder.tvComment.setText(resultBeanInfo.getReplyCount() + "跟帖"); // 新闻来源
        // 显示新闻图片
       Picasso.with(mContext).load(resultBeanInfo.getImgsrc()).into(viewHolder.ivIcon);

        return view;
    }

    class ViewHolder{

        ImageView ivIcon;
        TextView tvTitle;
        TextView tvSource;
        TextView tvComment;
    }
}
