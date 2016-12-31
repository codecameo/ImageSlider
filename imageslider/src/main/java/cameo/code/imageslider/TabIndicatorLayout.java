package cameo.code.imageslider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Md. Sifat-Ul Haque on 9/24/2016.
 */
public class TabIndicatorLayout extends FrameLayout {

    private ViewPager mViewPager;
    private int mFragmentCount;
    private LinearLayout mTabIndicatorHolder;
    private ArrayList<TabIndicator> mTabIndicators;
    private TabIndicator mFloatingTabIndicator,mSelectedTabIndicator;
    private int currentFragPosition = -1;

    public TabIndicatorLayout(Context context) {
        super(context);
        initLayout(context);
    }

    public TabIndicatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public TabIndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabIndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout(context);
    }


    public void setViewPager(ViewPager viewPager)
    {
        mViewPager = viewPager;
        mFragmentCount = viewPager.getAdapter().getCount();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
        mViewPager.addOnPageChangeListener(mPageChangeListener);

        if (mFragmentCount > 0 && viewPager != null && viewPager.getAdapter() != null)
        {
            addTabIndicator(mFragmentCount);
            mFloatingTabIndicator.setY(mTabIndicators.get(0).getY());
        }

    }

    private void initLayout(Context context) {

        setBackgroundColor(Color.TRANSPARENT);

        mTabIndicators = new ArrayList<>();

        mTabIndicatorHolder = new LinearLayout(context);
        mTabIndicatorHolder.setOrientation(LinearLayout.HORIZONTAL);
        mTabIndicatorHolder.setGravity(Gravity.CENTER);
        addView(mTabIndicatorHolder);

        mFloatingTabIndicator = getTabIndicator();
        //mFloatingTabIndicator.setBackgroundResource(R.drawable.tab_indicator_selector);
        addView(mFloatingTabIndicator);


        mFloatingTabIndicator.setSelected(true);


        LayoutParams layoutParams = (LayoutParams) mTabIndicatorHolder.getLayoutParams();
        layoutParams.height = (int) this.getResources().getDimension(R.dimen.indicator_radius);
        layoutParams.width = (int) this.getResources().getDimension(R.dimen.indicator_radius);

        mFloatingTabIndicator.setLayoutParams(layoutParams);

        layoutParams = new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mTabIndicatorHolder.setLayoutParams(layoutParams);
    }


    private void addTabIndicator(int count) {

        for (int i = 0; i < count; i++)
        {
            TabIndicator tabIndicator = getTabIndicator();
            mTabIndicators.add(tabIndicator);
            mTabIndicatorHolder.addView(tabIndicator);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
            layoutParams.setMargins(10, 10, 10, 10);
            layoutParams.height = (int) this.getResources().getDimension(R.dimen.indicator_radius);
            layoutParams.width = (int) this.getResources().getDimension(R.dimen.indicator_radius);
        }
    }


    private TabIndicator getTabIndicator() {
        TabIndicator tabIndicator = new TabIndicator(getContext());
        return tabIndicator;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFragmentCount > 0)
            mFloatingTabIndicator.setY(mTabIndicators.get(0).getY());
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d("Fragment","onPageScrolled Position "+position+" Position offset "+positionOffset+" positionOffsetPixels "+positionOffsetPixels);

            if (positionOffset != 0.0f)
            {
                if (mFloatingTabIndicator.getVisibility()== GONE)
                    mFloatingTabIndicator.setVisibility(VISIBLE);

                mSelectedTabIndicator.setSelected(false);
            }
            else
            {
                //Toast.makeText(this,"Zero",Toast.LENGTH_SHORT).show();
                mFloatingTabIndicator.setVisibility(View.GONE);
                configTabIndicator(position);
            }

            if (currentFragPosition != position)
            {
                //flag = false;
                currentFragPosition = position;
            }
            else
            {
                if (currentFragPosition >= mFragmentCount-1) return;

                float diff = mTabIndicators.get(currentFragPosition+1).getX() - mTabIndicators.get(currentFragPosition).getX();

                diff = diff*positionOffset;

                mFloatingTabIndicator.setX(mTabIndicators.get(currentFragPosition).getX()+diff);

            }


        }

        private void configTabIndicator(int position) {

            if (mSelectedTabIndicator != null && mSelectedTabIndicator.isSelected())
            {
                mSelectedTabIndicator.setSelected(false);
            }

            mSelectedTabIndicator = mTabIndicators.get(position);

            if (!mSelectedTabIndicator.isSelected())
                mSelectedTabIndicator.setSelected(true);
        }

        @Override
        public void onPageSelected(int position) {
            Log.d("Fragment","onPageSelected Position "+position);
            configTabIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("Fragment","onPageScrollStateChanged state "+state);

            if (state == 1)
            {
                //Log.d("Fragment","Index" + mSelectedTabIndicator+" X "+mSelectedTabIndicator.getX()+ " Y " +mSelectedTabIndicator.getY());
                //flag = true;
                mFloatingTabIndicator.setVisibility(View.VISIBLE);
            }
            else if (state == 0)
            {
                //Log.d("Fragment","Index" + mSelectedTabIndicator+" X "+mSelectedTabIndicator.getX()+ " Y " +mSelectedTabIndicator.getY());
                mFloatingTabIndicator.setVisibility(View.GONE);
            }
        }
    };
}
