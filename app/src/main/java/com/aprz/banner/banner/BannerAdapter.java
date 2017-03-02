package com.aprz.banner.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aprz.banner.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by aprz on 17-3-2.
 * -- hh --
 */

public class BannerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<ImageView> mImageViews = new ArrayList<>();
    private ArrayList<String> mUrls;
    private OnBannerClickListener mOnBannerClickListener;

    public BannerAdapter(Context context, ArrayList<String> urls) {
        this.mContext = context;
        this.mUrls = urls;
        if (mUrls != null) {
            createImageViews(urls.size());
        }
    }

    @Override
    public final int getCount() {
        if (mUrls == null || mUrls.size() <= 0) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public final boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public final Object instantiateItem(ViewGroup container, int position) {
        Log.e("pos", position + "");
        final int realPosition = toRealPosition(position);
        Log.e("pos", realPosition + "");
        ImageView added = mImageViews.get(realPosition);
        // loadUrl
        ImageLoader.getInstance().displayImage(mUrls.get(realPosition), added,
                new DisplayImageOptions.Builder().showImageOnFail(R.mipmap.ic_launcher).build());
        container.addView(added);
        if (mOnBannerClickListener != null) {
            added.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnBannerClickListener.onClick(realPosition);
                }
            });
        }
        return added;
    }

    @Override
    public final void destroyItem(ViewGroup container, int position, Object object) {
        View removed = (View) object;
        removed.setOnClickListener(null);
        container.removeView(removed);
    }

    private void createImageViews(int size) {

        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            // viewpager 的layoutParams 默认为 MATCH_PARENT
            imageView.setLayoutParams(new ViewPager.LayoutParams());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViews.add(imageView);
        }

    }

    private int toRealPosition(int position) {
        int count = mUrls.size();
        return position % count;
    }

    public void setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        mOnBannerClickListener = onBannerClickListener;
    }

    public interface OnBannerClickListener {
        void onClick(int position);
    }
}
