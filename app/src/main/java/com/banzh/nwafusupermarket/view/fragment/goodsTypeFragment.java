package com.banzh.nwafusupermarket.view.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.adapter.RecyclerGoodsAdapter;
import com.banzh.nwafusupermarket.data.ListGoodsItem;
import com.banzh.nwafusupermarket.data.creater.GoodsArrayList;
import com.banzh.nwafusupermarket.databinding.FragmentGoodsTypeBinding;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class goodsTypeFragment extends Fragment implements SurfaceHolder.Callback {

    private FragmentGoodsTypeBinding goodsTypeBinding;
    Context mContext;

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    RecyclerView rv_goodstype;
    LinearLayoutManager linearLayoutManager;
    RecyclerGoodsAdapter goodsAdapter;
    ImageButton btnSwitchVideo;
    NavigationView navGoodsType;

    ArrayList<ListGoodsItem> mListGoodsItems = new ArrayList<>();
    boolean video_flag = false;

    public goodsTypeFragment() {
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
        goodsTypeBinding = FragmentGoodsTypeBinding.inflate(inflater);
        return goodsTypeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }



    private void initView() {
        initListGoodsItems();
        //video-related
        surfaceView = goodsTypeBinding.fragmentGoodsVideo;
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        mediaPlayer = new MediaPlayer();

        /*
        播放视频相关代码
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });*/

        rv_goodstype = goodsTypeBinding.rvGoodstype;

        linearLayoutManager = new LinearLayoutManager(mContext);
        rv_goodstype.setLayoutManager(linearLayoutManager);
        goodsAdapter = new RecyclerGoodsAdapter(mListGoodsItems);
        rv_goodstype.setAdapter(goodsAdapter);

        /*
        播放视频相关代码
        btnSwitchVideo = goodsTypeBinding.btnSwitchVideo;
        btnSwitchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video_flag){
                    mediaPlayer.start();
                    video_flag = !video_flag;
                }else {
                        mediaPlayer.pause();
                        video_flag = !video_flag;
                }
            }
        });*/

        //初始化商品
        navGoodsType = goodsTypeBinding.navGoodsType;
        navGoodsType.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.good_item_food:

                        break;
                    case R.id.good_item_otherfood:

                        break;
                    case R.id.good_item_everydaygoods:

                        break;
                    case R.id.good_item_fresh:

                        break;
                    case R.id.good_item_drink:

                        break;
                }
                return true;
            }
        });
    }

    private void initListGoodsItems() {
        GoodsArrayList.createInitDataGoods(mListGoodsItems, getContext());
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.ouyangnana);
        mediaPlayer.reset();
        try {
            mediaPlayer.setDisplay(holder);
            mediaPlayer.setDataSource(getActivity(), uri);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
