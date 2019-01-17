package shoppingmall.guanxiang.com.shoppingmall.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import shoppingmall.guanxiang.com.shoppingmall.R;

public class MainActivity extends Activity {

    private FrameLayout frameLayout;
    private RadioGroup rg_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        rg_main = findViewById(R.id.rg_main);

        rg_main.check(R.id.rb_home);

    }



}
