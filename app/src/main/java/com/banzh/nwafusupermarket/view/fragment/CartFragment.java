package com.banzh.nwafusupermarket.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.adapter.CartRecycleAdapter;
import com.banzh.nwafusupermarket.data.ListGoodsItem;
import com.banzh.nwafusupermarket.data.creater.GoodsArrayList;
import com.banzh.nwafusupermarket.databinding.FragmentCartBinding;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private int goodId;
    Bundle arguments;

    FragmentCartBinding  cartBinding;
    RecyclerView cartRecycleView;
    CartRecycleAdapter myAdapter;
    LinearLayoutManager linearLayoutManager;

    ArrayList<ListGoodsItem> cartGoods = new ArrayList<>();
    ArrayList<ListGoodsItem> sourceGoods = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((arguments = getArguments()) == null){
            goodId = -1;
        }else {
            goodId = getArguments().getInt("goodId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cartBinding = FragmentCartBinding.inflate(inflater);
        return cartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCartData();
        initView();
    }

    private void initCartData() {
        if (goodId >= 0) {
            GoodsArrayList.createInitDataGoods(sourceGoods, getContext());
            cartGoods.add(sourceGoods.get(goodId));
        }
    }

    private void initView() {
        cartRecycleView = cartBinding.rvCartgoods;
        myAdapter = new CartRecycleAdapter(cartGoods);
        linearLayoutManager = new LinearLayoutManager(getContext());
        cartRecycleView.setLayoutManager(linearLayoutManager);
        cartRecycleView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}
