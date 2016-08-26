package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.adapter.ViewPagerFragmentAdapter;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class FragmentViewPager extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = FragmentViewPager.class.getSimpleName();
    private TextView mViewPagerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPagerStatus = (TextView) findViewById(R.id.view_pager_status);
        mViewPagerStatus.setText(ViewPagerFragmentAdapter.PAGER_TABS[0]);
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected(),position:" + position);
        mViewPagerStatus.setText(ViewPagerFragmentAdapter.PAGER_TABS[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
