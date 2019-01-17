package shoppingmall.guanxiang.com.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shoppingmall.guanxiang.com.shoppingmall.R;
import shoppingmall.guanxiang.com.shoppingmall.home.bean.ResultBeanData;
import shoppingmall.guanxiang.com.shoppingmall.utils.Constants;

/**
 * @author liming
 * @data 2019/1/17
 */
public class ChannelAdapter extends BaseAdapter {
    private List<ResultBeanData.ResultBean.ChannelInfoBean> datas;
    private Context mContext;

    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext=mContext;
        this.datas = channel_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon =  convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //更据位置得到对应的数据
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(channelInfoBean.getChannel_name());

        return convertView;
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
