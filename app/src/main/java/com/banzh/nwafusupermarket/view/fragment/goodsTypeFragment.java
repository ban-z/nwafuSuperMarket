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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.adapter.RecyclerGoodsAdapter;
import com.banzh.nwafusupermarket.data.ListGoodsItem;
import com.banzh.nwafusupermarket.databinding.FragmentGoodsTypeBinding;

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

    List<ListGoodsItem> mListGoodsItems = new ArrayList<>();
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

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        rv_goodstype = goodsTypeBinding.rvGoodstype;

        linearLayoutManager = new LinearLayoutManager(mContext);
        rv_goodstype.setLayoutManager(linearLayoutManager);
        goodsAdapter = new RecyclerGoodsAdapter(mListGoodsItems);
        rv_goodstype.setAdapter(goodsAdapter);

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
        });
    }

    private void initListGoodsItems() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ListGoodsItem listGoodsItem1 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem1);
            ListGoodsItem listGoodsItem2 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem2);
            ListGoodsItem listGoodsItem3 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem3);
            ListGoodsItem listGoodsItem4 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem4);
            ListGoodsItem listGoodsItem5 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem5);
            ListGoodsItem listGoodsItem6 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem6);
            ListGoodsItem listGoodsItem7 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem7);
            ListGoodsItem listGoodsItem8 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem8);
            ListGoodsItem listGoodsItem9 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem9);
            ListGoodsItem listGoodsItem10 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem10);
            ListGoodsItem listGoodsItem11 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem11);
            ListGoodsItem listGoodsItem12 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem12);
            ListGoodsItem listGoodsItem13 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem13);
            ListGoodsItem listGoodsItem14 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem14);
            ListGoodsItem listGoodsItem15 = new ListGoodsItem(getContext().getDrawable(R.drawable.ic_launcher_foreground),
                    "标题", "内容内容内容......");
            mListGoodsItems.add(listGoodsItem15);
        }
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
