package shoppingmall.guanxiang.com.shoppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import shoppingmall.guanxiang.com.shoppingmall.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //两秒钟进入主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //启动主界面
                //执行在主线程
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
