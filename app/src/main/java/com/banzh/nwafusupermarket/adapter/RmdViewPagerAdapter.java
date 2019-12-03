package com.banzh.nwafusupermarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.banzh.nwafusupermarket.R;

import java.util.List;

public class RmdViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> datas;

    public RmdViewPagerAdapter(Context context, List<Integer> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        /*return datas.size();*/

        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //不加第三个参数会报错：原因待查
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_recommand_item, container, false);
        ImageView imageView = view.findViewById(R.id.img_recmmand_item);

        /*imageView.setImageResource(datas.get(position));*/
        imageView.setImageResource(datas.get(position % datas.size()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
