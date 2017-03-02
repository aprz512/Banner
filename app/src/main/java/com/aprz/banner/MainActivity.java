package com.aprz.banner;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aprz.banner.banner.Banner;
import com.aprz.banner.banner.BannerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BannerAdapter.OnBannerClickListener, ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> bannerUrls = new ArrayList<>();
        bannerUrls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        bannerUrls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        bannerUrls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        bannerUrls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        bannerUrls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        Banner banner = (Banner) findViewById(R.id.vp_demo);
        BannerAdapter adapter = new BannerAdapter(this, bannerUrls);
        adapter.setOnBannerClickListener(this);
        banner.setAdapter(adapter);
        banner.addOnPageChangeListener(this);
        banner.setCurrentItem(10000 * bannerUrls.size());
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, "position = " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 这里的 position 要转换一下
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
