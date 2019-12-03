package com.banzh.nwafusupermarket.view.viewpager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.banzh.nwafusupermarket.contants.commonContants;

import java.util.List;


public class ScrollerViewPager extends ViewPager {

    public ScrollerViewPager(@NonNull Context context) {
        super(context);
    }

    public ScrollerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == commonContants.SCROLL_VIEW_PAGER){
                setCurrentItem(getCurrentItem() + 1);
            }
        }
    };

    private List<View> views;
    private int count;
    private Thread scrollThread;
    private int SPACE;
    private boolean NORMAL = true;

    private void init(List<View> views, final int space, final OnViewPagerChangeListener listener){
        this.views = views;
        this.count = views.size();
        this.SPACE = space;

        setAdapter(new ScrollerPagerAdapter());
        setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % views.size());

        scrollThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        NORMAL = true;
                        Thread.sleep(SPACE * 1000);
                        handler.sendEmptyMessage(commonContants.SCROLL_VIEW_PAGER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        try {
                            NORMAL  = false;
                            Thread.sleep(Integer.MAX_VALUE);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        scrollThread.start();

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listener.onChange(position % count);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if ((!NORMAL && state != 1) || (NORMAL && state == 1)){
                    scrollThread.interrupt();
                }
            }
        });
    }

    private class ScrollerPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int temp = position % count;
            View view = views.get(temp);
            if (view.getParent() == container){
                container.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        }
    }
}
