package shoppingmall.guanxiang.com.shoppingmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.youth.banner.transformer.ScaleInOutTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        }else if(viewType == CHANNEL){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType == ACT){
            return new ActViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));
        }else if(viewType == SECKLL){
            return new SeckillViewHolder(mContext,mLayoutInflater.inflate(R.layout.seckill_item,null));
        }else if(viewType == RECOMMEND){
            return new RecommendViewHolder(mContext,mLayoutInflater.inflate(R.layout.recommend_item,null));
        }else if(viewType == HOT){
            return new HotViewHolder(mContext,mLayoutInflater.inflate(R.layout.hot_item,null));
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
        }else if(getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) viewHolder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if(getItemViewType(position)==ACT){
            ActViewHolder actViewHolder = (ActViewHolder) viewHolder;
            actViewHolder.setData(resultBean.getAct_info());
        }else if(getItemViewType(position)==SECKLL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) viewHolder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        }else if(getItemViewType(position)==RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) viewHolder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder = (HotViewHolder) viewHolder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        public HotViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.tv_more_hot = itemView.findViewById(R.id.tv_more_hot);
            this.gv_hot = itemView.findViewById(R.id.gv_hot);

            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            //1.得到数据
            //设置适配器
            HotGridViewAdapter adapter = new HotGridViewAdapter(mContext,hot_info);
            gv_hot.setAdapter(adapter);
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{

        private final String TAG = RecommendViewHolder.class.getSimpleName();
        private Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;

        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = itemView.findViewById(R.id.gv_recommend);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            //1有数据了
            //设置适配器
            Log.e(TAG,"recommend_info="+recommend_info);
            adapter = new RecommendGridViewAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(adapter);
        }
    }


    class SeckillViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private Context mContext;
        private SeckillRecyclerViewAdapter adapter;

        /**
         * 还相差多少时间
         */
        private long dt=0;


        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                dt = dt-1000;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt <= 0){
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };


        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            //1得到数据
            //设置数据：文本和RecycleView的数据
            adapter =  new SeckillRecyclerViewAdapter(mContext,seckill_info.getList());
            rv_seckill.setAdapter(adapter);

            //设置布局管理齐
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //设置itemd的点击事件
            adapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext,"秒杀="+position, Toast.LENGTH_SHORT).show();
                }
            });

            //秒杀倒计时-毫秒
            dt = Integer.valueOf(seckill_info.getEnd_time())-Integer.valueOf(seckill_info.getStart_time());

            handler.sendEmptyMessageDelayed(0,1000);

        }

    }

    class ActViewHolder extends RecyclerView.ViewHolder{

        private  Context mContext;
        private ViewPager act_viewpager;
        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(60);
            act_viewpager.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new ScaleInOutTransformer());


            //1有数据了
            //2设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view == object;
                }

                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+act_info.get(position).getIcon_url()).into(imageView);

                    //添加到容器
                    container.addView(imageView);
                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                        }
                    });

                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;
        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.gv_channel = itemView.findViewById(R.id.gv_channel);
        }


        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据啦
            //设置GridView的适配器
            adapter = new ChannelAdapter(mContext,channel_info);
            gv_channel.setAdapter(adapter);
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
        return currentType;
    }

    /**
     * 总共有多少个Item
     * @return
     */
    @Override
    public int getItemCount() {
        return 6;
    }
}
