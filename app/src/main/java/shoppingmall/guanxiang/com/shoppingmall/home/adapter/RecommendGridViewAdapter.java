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
public class RecommendGridViewAdapter extends BaseAdapter{
    private List<ResultBeanData.ResultBean.RecommendInfoBean> datas;
    private Context mContext;

    public RecommendGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;

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
           convertView = View.inflate(mContext, R.layout.item_recommend_grid_view,null);
           holder = new ViewHolder();
           holder.iv_recommend = convertView.findViewById(R.id.iv_recommend);
           holder.tv_name = convertView.findViewById(R.id.tv_name);
           holder.tv_price = convertView.findViewById(R.id.tv_price);

           convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure()).into(holder.iv_recommend);
        holder.tv_name.setText(recommendInfoBean.getName());
        holder.tv_price.setText("￥"+recommendInfoBean.getCover_price());
        return convertView;

    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
