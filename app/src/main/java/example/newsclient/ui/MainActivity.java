package example.newsclient.ui;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.newsclient.R;
import example.newsclient.util.SlidingMenu;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private SlidingMenu mSlidingMenu;
    private Toolbar mToobar;


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
        initToobar();
        mSlidingMenu= (SlidingMenu) findViewById(R.id.sliding_menu);
        mRadioGroup= (RadioGroup) findViewById(R.id.radio_group);
        initViewPager();
    }

    private void initToobar() {
        mToobar= (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToobar);
        mToobar.setLogo(R.drawable.biz_video_list_item_tie);
        mToobar.setTitle("网易云");   // 通过代码设置才生效：app:title="toolbar"

        mToobar.setSubtitle("新闻");
        mToobar.setTitleTextColor(Color.RED);
        mToobar.setSubtitleTextColor(Color.YELLOW);

        // 显示标题栏左上角的返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 导航栏图标显示
        mToobar.setNavigationIcon(R.drawable.biz_video_list_play_icon_big);
        // 点击toolbar导航栏左上角的图标后，退出当前界面
        mToobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
                toggleMenu();
            }
        });
    }

    /**
     * 切换菜单
     * */
    public void toggleMenu()
    {
        mSlidingMenu.toggle();
    }

    // Toolbar标题栏右侧点击菜单:创建
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // res/memu/main_option.xml
        // 定义可点击的菜单项
        getMenuInflater().inflate(R.menu.main_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // Toolbar标题栏右侧点击菜单：点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==  R.id.menu_01){
            Toast.makeText(this, "menu_01", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.menu_02) {
            Toast.makeText(this, "menu_02", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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
