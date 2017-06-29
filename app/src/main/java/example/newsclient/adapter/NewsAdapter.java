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
        //获取item的类型
        int type=getItemViewType(position);
        if (type==TYPE_1){
            //一张图片的类型
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

        }else {
            //三张图片的类型
            NewsEntity.ResultBean resultBeanInfo=mResultBeanList.get(position);
            View view;
            ViewHolder2 viewHolder;
            if (convertView==null){
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.news_item2, parent,false);

                viewHolder=new ViewHolder2();

                //绑定控件
                viewHolder.mTextView_title= (TextView) view.findViewById(R.id.tv_news_title);
                viewHolder.mTextView_Comment= (TextView) view.findViewById(R.id.tv_comment_count);
                viewHolder.mImageView_one= (ImageView) view.findViewById(R.id.iv_01);
                viewHolder.mImageView_two= (ImageView) view.findViewById(R.id.iv_02);
                viewHolder.mImageView_three= (ImageView) view.findViewById(R.id.iv_03);
                //将viewHolder存储在view中
                view.setTag(viewHolder);

            }else {
                view=convertView;
                viewHolder= (ViewHolder2) view.getTag();
            }

            // 4. 显示列表项中的子控件
            viewHolder.mTextView_title.setText(resultBeanInfo.getTitle());           // 显示标题
            viewHolder.mTextView_Comment.setText(resultBeanInfo.getReplyCount() + "跟帖"); // 新闻来源
            // 显示3张新闻图片
            Picasso.with(mContext).load(resultBeanInfo.getImgsrc()).into(viewHolder.mImageView_one);
            Picasso.with(mContext).load(resultBeanInfo.getImgextra().get(0).getImgsrc()).into(viewHolder.mImageView_two);
            Picasso.with(mContext).load(resultBeanInfo.getImgextra().get(1).getImgsrc()).into(viewHolder.mImageView_three);

            return view;

        }

    }

    /* ---------------列表显示多种类型 -------------------*/
    private static final int TYPE_1=0;//一张图片的类型
    private static final int TYPE_3=1;//三张图片的类型

    /**
     *返回列表项属于哪一种类型的item
     * */
    @Override
    public int getItemViewType(int position) {
        //列表项对应的实体数据
        NewsEntity.ResultBean news= (NewsEntity.ResultBean) getItem(position);
        //判断是哪一种类型
        if (news.getImgextra()!=null&&news.getImgextra().size()>0){
            //三张图片的类型
            return TYPE_3;
        }else {
            return TYPE_1;
        }

    }

    /**
     *返回有多少种不同类型的列表项
     * */
    @Override
    public int getViewTypeCount() {
        return 2;
    }



     /*------- 列表显示多种类型 -----------------*/



    /**
     * 重置列表的所有的数据，并刷新列表显示
     * @param listDatas
     */
    public void setDatas(List<NewsEntity.ResultBean> listDatas) {
        mResultBeanList = listDatas;
        notifyDataSetChanged();     // 刷新列表
    }

    /** 追加数据，并刷新列表显示 */
    public void appendDatas(List<NewsEntity.ResultBean> listDatas) {
        mResultBeanList.addAll(listDatas);
        notifyDataSetChanged();     // 刷新列表
    }

    class ViewHolder{

        ImageView ivIcon;
        TextView tvTitle;
        TextView tvSource;
        TextView tvComment;

    }

    class ViewHolder2{
        TextView mTextView_title;
        TextView mTextView_Comment;
        ImageView mImageView_one;
        ImageView mImageView_two;
        ImageView mImageView_three;
    }
}
