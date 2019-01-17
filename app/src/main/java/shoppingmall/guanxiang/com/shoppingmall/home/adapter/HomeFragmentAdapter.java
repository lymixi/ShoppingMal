package shoppingmall.guanxiang.com.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

import shoppingmall.guanxiang.com.shoppingmall.R;
import shoppingmall.guanxiang.com.shoppingmall.home.bean.ResultBeanData;
import shoppingmall.guanxiang.com.shoppingmall.utils.Constants;

/**
 * @author liming
 * @data 2019/1/17
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /**
     * 广告条幅
     */
    public static final  int BANNER = 0 ;
    /**
     * 频道
     */
    public static final  int CHANNEL = 1 ;

    /**
     * 活动
     */
    public static final  int ACT = 2 ;
    /**
     * 秒杀类型
     */
    public static final  int SECKLL = 3 ;
    /**
     * 推荐类型
     */
    public static final  int RECOMMEND = 4 ;
    /**
     * 热卖类型
     */
    public static final  int HOT = 5 ;
    private final Context mContext;
    private final ResultBeanData.ResultBean resultBean;
    /**
     * 用他来出事化布局
     */
    private final LayoutInflater mLayoutInflater;


    /**
     * 当前类型
     */
    private int currentType = 0;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView
     * 创建ViewHolder
     * @param viewGroup
     * @param viewType  当前类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType == BANNER){
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }

        return null;
    }

    /**
     * 相当于getView中的绑定数据模块
     * @param viewHolder
     * @param position
     */

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position)==BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner数据

            //得到图片地址
            List<String> imagesUrl = new ArrayList<>();
            for(int i=0;i<banner_info.size();i++){
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+url).into(view);
                }
            });

            //设置item的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 得到类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKLL:
                currentType = SECKLL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;

        }
        return super.getItemViewType(position);
    }

    /**
     * 总共有多少个Item
     * @return
     */
    @Override
    public int getItemCount() {
        return 1;
    }
}
