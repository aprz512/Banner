package com.aprz.banner.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by aprz on 17-3-2.
 * -- hh --
 */

public class Banner extends ViewPager {

    private int mCount = 0;
    // 默认 3 秒
    private long mDelayTime = 3000;
    private Handler mHandler = new Handler();
    private boolean mTouchMode;

    private final Runnable mAutoScrollTask = new Runnable() {
        @Override
        public void run() {
            // 这里应该要考虑下内存泄露
            if (mCount > 1 && getContext() != null) {
                int currentItem = getCurrentItem();
                Log.e("aprz", "touchMode = " + mTouchMode);
                if (currentItem > 0 && !mTouchMode) {
                    currentItem++;
                    setCurrentItem(currentItem);
                }
                mHandler.postDelayed(this, mDelayTime);
            }
        }
    };

    public Banner(Context context) {
        super(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        mCount = adapter.getCount();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startScroll();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_HOVER_ENTER:
            case MotionEvent.ACTION_HOVER_MOVE:
                mTouchMode = true;
                stopScroll();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_HOVER_EXIT:
                mTouchMode = false;
                startScroll();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @SuppressWarnings("unused")
    public void setDelayTime(long delayTime) {
        mDelayTime = delayTime;
    }

    /**
     * 这两个方法也可以手动调用
     */
    public void startScroll() {
        mHandler.removeCallbacks(mAutoScrollTask);
        mHandler.postDelayed(mAutoScrollTask, mDelayTime);
    }

    public void stopScroll() {
        mHandler.removeCallbacks(mAutoScrollTask);
    }
}
