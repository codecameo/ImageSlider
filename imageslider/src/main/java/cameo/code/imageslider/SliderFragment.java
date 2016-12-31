package cameo.code.imageslider;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Md. Sifat-Ul Haque on 12/16/2016.
 */

public class SliderFragment extends Fragment implements ViewPager.OnPageChangeListener {


    private static final String ALL_IMAGE_URL = "all_sliding_image_url";
    private static final int SLIDER_TIME_INTERVAL = 2000;
    private static final String ALL_IMAGE_RES = "all_sliding_image_res";
    private ArrayList<String>  mImageUrls;
    private ArrayList<Integer>  mImageRes;
    private ViewPagerCustomDuration mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;
    private Handler mHandler;
    private TabIndicatorLayout mTabIndicator;
    private int mCount;

    private Runnable mChangeSlider = new Runnable() {
        @Override
        public void run() {
            mPager.setCurrentItem((mPager.getCurrentItem()+1) % mCount);
        }
    };

    public static SliderFragment createWithRes(ArrayList<Integer> imagesRes) {
        SliderFragment fragment = new SliderFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(ALL_IMAGE_RES, imagesRes);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static SliderFragment createWithPath(ArrayList<String> images) {
        SliderFragment fragment = new SliderFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ALL_IMAGE_URL, images);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrls = getArguments().getStringArrayList(ALL_IMAGE_URL);
        mImageRes = getArguments().getIntegerArrayList(ALL_IMAGE_RES);

        if (mImageUrls == null)
            mCount = mImageRes.size();
        else
            mCount = mImageUrls.size();

        mHandler = new Handler();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mPager = (ViewPagerCustomDuration) view.findViewById(R.id.vp_image_slider);
        mTabIndicator = (TabIndicatorLayout) view.findViewById(R.id.tab_indicator_frame);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
    }

    private void initViewPager() {

        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setScrollDurationFactor(2);
        mPager.setPageTransformer(true,new ZoomTransformation());
        mPager.addOnPageChangeListener(this);

        mTabIndicator.setViewPager(mPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("PageSelected","Position "+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("PageScrollState","State "+state);
        if (state == 1)
            mHandler.removeCallbacks(mChangeSlider);
        else if (state == 0)
            mHandler.postDelayed(mChangeSlider,SLIDER_TIME_INTERVAL);

    }

    /**
     * A simple pager adapter that represents 5 {@link SlidingFragment} objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (mImageUrls != null)
            return SlidingFragment.create(mImageUrls.get(position));
            else
                return SlidingFragment.create(mImageRes.get(position));
        }

        @Override
        public int getCount() {
            return mCount;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startSlider();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopSlider();
    }

    public void startSlider(){
        stopSlider();
        mHandler.postDelayed(mChangeSlider,SLIDER_TIME_INTERVAL);
    }

    public void stopSlider(){
        mHandler.removeCallbacks(mChangeSlider);
    }
}
