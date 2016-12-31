package cameo.code.imageslider;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Md. Sifat-Ul Haque on 12/16/2016.
 */

public class SlidingFragment extends Fragment {

    private static final String IMAGE_URL = "sliding_image_url";
    private static final String IMAGE_RES = "sliding_image_res";
    private String  mImageUrl;
    private int mImageRes;
    private ImageView mSlidingImage;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static SlidingFragment create(String imageUrl) {
        SlidingFragment fragment = new SlidingFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public static SlidingFragment create(int imageRes) {
        SlidingFragment fragment = new SlidingFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_RES, imageRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments().getString(IMAGE_URL);
        mImageRes = getArguments().getInt(IMAGE_RES);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sliding_image, container, false);
        intiView(view);
        return view;
    }

    private void intiView(View view) {
        mSlidingImage = (ImageView) view.findViewById(R.id.iv_sliding_image);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mImageUrl != null)
            Picasso.with(getActivity()).load(mImageUrl).into(mSlidingImage);
        else
            Picasso.with(getActivity()).load(mImageRes).into(mSlidingImage);

        //setImage(mSlidingImage,mImageUrl);


        //Glide.with(getActivity()).load(mImageUrl).placeholder(R.drawable.audi_4).into(mSlidingImage);
    }

    public static void setImage(ImageView imageView, String url) {
        //Point screen = Utils.getScreenSize(imageView.getContext());
        imageView.setVisibility(View.VISIBLE);
        Picasso.with(imageView.getContext())
                .cancelRequest(imageView);
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.audi_4)
                .noFade()
                .stableKey(url)
                .fit()
                .centerCrop()
                .into(imageView);

    }
}
