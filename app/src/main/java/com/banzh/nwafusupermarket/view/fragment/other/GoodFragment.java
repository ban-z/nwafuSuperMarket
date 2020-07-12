package com.banzh.nwafusupermarket.view.fragment.other;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.data.ListGoodsItem;
import com.banzh.nwafusupermarket.data.creater.GoodsArrayList;
import com.banzh.nwafusupermarket.databinding.FragmentGoodBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GoodFragment extends Fragment {

    Context mContext;

    FragmentGoodBinding goodBinding;
    ImageView imgGood;
    TextView tvGoodName;
    Button btnAddCart;
    Button btnPayGood;
    BottomNavigationView bNav;

    private int goodId;
    ArrayList<ListGoodsItem> goods = new ArrayList<>();
    ListGoodsItem curItem;


    public GoodFragment() {
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
        goodBinding = FragmentGoodBinding.inflate(inflater);
        goodId = getArguments().getInt("goodId");
        return goodBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bNav = getActivity().findViewById(R.id.navigation_bottom);
        bNav.setVisibility(View.GONE);

        initGoodsData();
        curItem = goods.get(goodId);
        initView();
    }

    private void initGoodsData() {
        GoodsArrayList.createInitDataGoods(goods, getContext());
    }

    private void initView() {
        imgGood = goodBinding.imgGood;
        tvGoodName = goodBinding.tvGoodName;
        btnAddCart = goodBinding.btnAddCart;
        btnPayGood = goodBinding.btnPayGood;

        //init
        imgGood.setImageDrawable(curItem.getDrawable());
        tvGoodName.setText(curItem.getTitle());

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("goodId", goodId);
                NavHostFragment.findNavController(GoodFragment.this)
                        .navigate(R.id.action_goodFragment_to_cartFragment, bundle);
            }
        });

        btnPayGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未实现
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        bNav.setVisibility(View.VISIBLE);
    }
}
