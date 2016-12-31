package cameo.code.imageslider;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Md. Sifat-Ul Haque on 9/23/2016.
 */

public class TabIndicator extends View {


    public TabIndicator(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.setBackgroundResource(R.drawable.tab_indicator_selector);
        this.setMinimumHeight(1000);
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec = MeasureSpec.makeMeasureSpec(heightMeasureSpec*3, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
