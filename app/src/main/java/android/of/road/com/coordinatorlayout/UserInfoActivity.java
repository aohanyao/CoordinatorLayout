package android.of.road.com.coordinatorlayout;

import android.of.road.com.behavior.fragment.UserInfoFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initView();
    }

    private void initView() {
        TabLayout tlUserInfo = findViewById(R.id.tlUserInfo);
        ViewPager vpUserInfo = findViewById(R.id.vpUserInfo);

        final List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(UserInfoFragment.newInstance());
        mFragments.add(UserInfoFragment.newInstance());
        mFragments.add(UserInfoFragment.newInstance());


        final String[] title = new String[]{"MEDIA", "ABOUT", "REVIEWS"};

        vpUserInfo.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });

        vpUserInfo.setOffscreenPageLimit(mFragments.size() * 2);
        tlUserInfo.setupWithViewPager(vpUserInfo);

    }
}
