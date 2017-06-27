package example.newsclient.ui;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import example.newsclient.R;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;



    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
//当点击RadioButton时，ViewPager显示的子界面工切换
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radio_btn1:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.radio_btn2:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.radio_btn3:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.radio_btn4:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.radio_btn5:
                        mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        //当ViewPager子界面发生了改变时，要选中并高亮不同的RadioButton选项卡
       mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
           }

           @Override
           public void onPageSelected(int position) {
               switch (position){
                   case 0:
                       mRadioGroup.check(R.id.radio_btn1);
                       break;
                   case 1:
                       mRadioGroup.check(R.id.radio_btn2);
                       break;
                   case 2:
                       mRadioGroup.check(R.id.radio_btn3);
                       break;
                   case 3:
                       mRadioGroup.check(R.id.radio_btn4);
                       break;
                   case 4:
                       mRadioGroup.check(R.id.radio_btn5);
                       break;
               }
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
    }

    @Override
    public void initView() {
        mRadioGroup= (RadioGroup) findViewById(R.id.radio_group);
        initViewPager();
    }

    private void initViewPager() {
        mViewPager= (ViewPager) findViewById(R.id.view_pager);

        //fragment数组
        final List<Fragment> mFragmentList=new ArrayList<>();
        mFragmentList.add(new Fragment1());
        mFragmentList.add(new Fragment2());
        mFragmentList.add(new Fragment3());
        mFragmentList.add(new Fragment4());
        mFragmentList.add(new Fragment5());

        //适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}
