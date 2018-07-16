package com.tkachuk.testapp.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tkachuk.testapp.R;
import com.tkachuk.testapp.util.NotificationManager;

public class MainActivity extends AppCompatActivity implements ICallBack {

    private static final String TAG = MainActivity.class.getSimpleName();

    int pageCount = 1;

    private ViewPager pager;
    private MyFragmentStatePagerAdapter myFragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        myFragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myFragmentStatePagerAdapter);

        pageCount = getIntent().getIntExtra(getResources().getString(R.string.page_count), 1);
        myFragmentStatePagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(getIntent().getIntExtra(getResources().getString(R.string.page_number), 1));
    }

    @Override
    public void createFragment(){
        pageCount++;
        myFragmentStatePagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(pageCount);
        Log.d(TAG, "createFragment: pageCount = "+pageCount);
    }

    @Override
    public void deleteFragment() {
        if(pageCount>1){
            pageCount--;
            NotificationManager.delete(this, pageCount);
            myFragmentStatePagerAdapter.notifyDataSetChanged();
            pager.setCurrentItem(pageCount);
            Log.d(TAG, "deleteFragment: pageCount = "+pageCount);
        }
    }

    public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        MyFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return MyFragmentStatePagerAdapter.POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return SingleFragment.newInstance(position, pageCount);
        }

        @Override
        public int getCount() {
            return pageCount;
        }
    }
}
