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
 * @data 2019/1/18
 */
public class HotGridViewAdapter extends BaseAdapter{
    private final List<ResultBeanData.ResultBean.HotInfoBean> datas;
    private Context mContext;

    public HotGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        this.mContext = mContext;
        this.datas = hot_info;
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

        ViewHolder holder;
        if(convertView == null){
           convertView = View.inflate(mContext, R.layout.item_hot_grid_view,null);
           holder = new ViewHolder();
           holder.iv_hot = convertView.findViewById(R.id.iv_hot);
           holder.tv_name = convertView.findViewById(R.id.tv_name);
           holder.tv_price = convertView.findViewById(R.id.tv_price);

           convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure()).into(holder.iv_hot);
        holder.tv_name.setText(hotInfoBean.getName());
        holder.tv_price.setText("￥"+hotInfoBean.getCover_price());
        return convertView;

    }

    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
