package shoppingmall.guanxiang.com.shoppingmall.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author liming
 * @data 2019/1/17
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initokhttpClient();
    }

    private void initokhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
