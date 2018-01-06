package itg8.com.adminserviceapp.ticket.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import itg8.com.adminserviceapp.common.Logs;

/**
 * Created by Android itg 8 on 12/13/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        Logs.d("ViewPager  destroyItem Position:" + position);
//
//        container.removeView((View) object);
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return object == view;
//    }
}

