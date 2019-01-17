package shoppingmall.guanxiang.com.shoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import shoppingmall.guanxiang.com.shoppingmall.R;
import shoppingmall.guanxiang.com.shoppingmall.base.BaseFragment;
import shoppingmall.guanxiang.com.shoppingmall.community.Fragment.CommunityFragment;
import shoppingmall.guanxiang.com.shoppingmall.home.Fragment.HomeFragment;
import shoppingmall.guanxiang.com.shoppingmall.shoppingcart.Fragment.ShoppingCartFragment;
import shoppingmall.guanxiang.com.shoppingmall.type.Fragment.TypeFragment;
import shoppingmall.guanxiang.com.shoppingmall.user.Fragment.UserFragment;

public class MainActivity extends FragmentActivity {

    private FrameLayout frameLayout;
    private RadioGroup rg_main;
    /**
     * 多个frament的实例集合
     */
    private ArrayList<BaseFragment> fragments;

    private int position = 0;
    /**
     * 缓存的fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        rg_main = findViewById(R.id.rg_main);
        /**
         * 初始化fragment
         */
        initFragment();
        initListener();

    }

    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;

                        break;

                    case R.id.rb_community:
                        position = 2;

                        break;

                    case R.id.rb_cart:
                        position = 3;

                        break;

                    case R.id.rb_user:
                        position = 4;

                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置取fragment
                BaseFragment baseFragment = getFragment(position);

                switchFragment(tempFragment,baseFragment);

            }
        });

        rg_main.check(R.id.rb_home);
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if(tempFragment != nextFragment){
            tempFragment = nextFragment;
            if (nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if(!nextFragment.isAdded()){
                    //隐藏当前Fragment
                    if(fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else{
                    //隐藏当前Fragment
                    if(fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }

        }
    }

    private BaseFragment getFragment(int position) {
        if(fragments !=null && fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);

            return baseFragment;
        }
        return null;
    }

    /**
     * 添加的时候注意顺序
     */
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }


}
