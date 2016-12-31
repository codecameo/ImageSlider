package cameo.code.slider;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cameo.code.imageslider.SliderFragment;

public class MainActivity extends AppCompatActivity {

    private SliderFragment mSliderFragment;
    //private ImageView mImageView;
    private ArrayList<String> mImagesUrl = new ArrayList<>();
    private ArrayList<Integer> mImagesRes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/7757.jpg");
        mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/6956.jpg");
        mImagesUrl.add("https://buyersguide.caranddriver.com/media/assets/submodel/1470.jpg");

        mImagesRes.add(R.drawable.mac);
        mImagesRes.add(R.drawable.acura);
        mImagesRes.add(R.drawable.mercedes);

        mSliderFragment = SliderFragment.createWithPath(mImagesUrl);
        //mSliderFragment = SliderFragment.createWithRes(mImagesRes);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.slider_panel, mSliderFragment);
        transaction.commit();
    }
}
