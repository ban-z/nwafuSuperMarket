package com.banzh.nwafusupermarket.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.adapter.ListGoodsAdapter;
import com.banzh.nwafusupermarket.adapter.RmdViewPagerAdapter;
import com.banzh.nwafusupermarket.data.ListGoodsItem;
import com.banzh.nwafusupermarket.data.creater.GoodsArrayList;
import com.banzh.nwafusupermarket.databinding.FragmentHomeBinding;
import com.banzh.nwafusupermarket.databinding.FragmentHomeListHeaderBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding homeBinding;
    FragmentHomeListHeaderBinding homeListHeaderBinding;
    Context mContext;

    LinearLayout layoutListHeader;
    ViewPager viewPager;
    List<Integer> datas;
    LinearLayout layout_dot;
    TextView[] dot;
    Handler changePagerHander;
    ListView listViewGoods;

    TextView tvSerach;


    ArrayList<ListGoodsItem> listGoodsItems = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*mbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);*/
        homeBinding = FragmentHomeBinding.inflate(inflater);
        //inflate other layout
        homeListHeaderBinding = FragmentHomeListHeaderBinding.inflate(getLayoutInflater());
        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutListHeader = homeListHeaderBinding.layoutHomeListHeader;
        initViewPager(view);
        initListView();
        initView();
    }



    private void initView() {
        tvSerach = homeListHeaderBinding.titlebatTvSerach;
        tvSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_searchFragment);
            }
        });
    }

    private void initListView() {
        initGoodItems();
        ListGoodsAdapter mAdapter = new ListGoodsAdapter(getContext(), R.layout.layout_home_listview_item, listGoodsItems);
        listViewGoods = homeBinding.lvHomeGoods;
        listViewGoods.setAdapter(mAdapter);
        listViewGoods.addHeaderView(layoutListHeader);

        listViewGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListView与ArrayList的position差1
                ListGoodsItem item = listGoodsItems.get(position - 1);
                Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_LONG).show();

                // 跳转到商品页面
                // 携带商品信息
                Bundle bundle = new Bundle();
                bundle.putInt("goodId", position - 1);
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_goodFragment, bundle);
            }
        });
    }

    private void initGoodItems() {
        GoodsArrayList.createInitDataGoods(listGoodsItems, getContext());
    }

    private void initViewPager(View view) {
        /*viewPager = homeBinding.vpRecommand;
        layout_dot = homeBinding.layoutDot;*/
        viewPager = homeListHeaderBinding.vpRecommand;
        layout_dot = homeListHeaderBinding.layoutDot;

        datas = new ArrayList<>();
        datas.add(R.drawable.page1);
        datas.add(R.drawable.page2);
        datas.add(R.drawable.page3);
        datas.add(R.drawable.page4);
        datas.add(R.drawable.page5);

        final RmdViewPagerAdapter adapter = new RmdViewPagerAdapter(getActivity().getApplicationContext(), datas);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(100 * datas.size());
        addDot(100 % datas.size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        changePagerHander = new Handler();
        changePagerHander.postDelayed(new TimerRunnable(), 3000);
    }

    private void addDot(int postion) {
        dot = new TextView[datas.size()];
        layout_dot.removeAllViews();

        for (int i = 0; i< dot.length; i++){
            dot[i] = new TextView(mContext);
            dot[i].setText(Html.fromHtml("&#9673;"));
            dot[i].setTextSize(10);
            dot[i].setTextColor(mContext.getResources().getColor(R.color.darker_gray));
            layout_dot.addView(dot[i]);
        }

        if (postion >= 0){
            /*dot[postion].setTextColor(getResources().getColor(R.color.bule));*/
            dot[postion % datas.size()].setTextColor(mContext.getResources().getColor(R.color.bule));
        }
    }

    class TimerRunnable implements Runnable {

        @Override
        public void run() {
            int cur_postion = viewPager.getCurrentItem();
            viewPager.setCurrentItem(cur_postion + 1);
            if (changePagerHander != null){
                changePagerHander.postDelayed(this, 2000);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        changePagerHander = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        changePagerHander = null;
    }
}
