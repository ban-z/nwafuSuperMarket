package com.banzh.nwafusupermarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.data.ListGoodsItem;
import com.banzh.nwafusupermarket.databinding.LayoutGoodstypeRecycleviewItemBinding;
import com.banzh.nwafusupermarket.databinding.LayoutHomeListviewItemBinding;

import java.util.List;

public class RecyclerGoodsAdapter extends RecyclerView.Adapter<RecyclerGoodsAdapter.ViewHolder> {

    private List<ListGoodsItem> mListGoodsItems;
    Context mContext;

    public RecyclerGoodsAdapter(List<ListGoodsItem> listGoodsItems){
        mListGoodsItems = listGoodsItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_goodstype_recycleview_item,
                parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListGoodsItem item = mListGoodsItems.get(position);
        holder.img_cart_list_item.setImageDrawable(item.getDrawable());
        holder.tv_cart_list_item_title.setText(item.getTitle());
        holder.tv_cart_list_item_content.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return mListGoodsItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LayoutGoodstypeRecycleviewItemBinding itemBinding;

        ImageView img_cart_list_item;
        TextView tv_cart_list_item_title;
        TextView tv_cart_list_item_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);

            img_cart_list_item = itemBinding.imgCartItem;
            tv_cart_list_item_title = itemBinding.tvCartItemTitle;
            tv_cart_list_item_content = itemBinding.tvCartItemContent;
        }
    }
}
