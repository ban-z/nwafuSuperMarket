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
import com.banzh.nwafusupermarket.databinding.FragmentCartListBinding;

import java.util.List;

public class CartRecycleAdapter extends RecyclerView.Adapter<CartRecycleAdapter.ViewHolder> {

    private List<ListGoodsItem> mListGoodsItems;
    Context mContext;

    public CartRecycleAdapter(List<ListGoodsItem> listGoodsItems){
        mListGoodsItems = listGoodsItems;
    }

    @NonNull
    @Override
    public CartRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cart_list,
                parent,false);
        CartRecycleAdapter.ViewHolder viewHolder = new CartRecycleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecycleAdapter.ViewHolder holder, int position) {
        ListGoodsItem item = mListGoodsItems.get(position);
        holder.img_cart_good.setImageDrawable(item.getDrawable());
        holder.tv_cart_good_title.setText(item.getTitle());
        holder.tv_cart_good_content.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return mListGoodsItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        FragmentCartListBinding itemBinding;

        ImageView img_cart_good;
        TextView tv_cart_good_title;
        TextView tv_cart_good_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);

            img_cart_good = itemBinding.imgCartGood;
            tv_cart_good_title = itemBinding.tvCartGoodTitle;
            tv_cart_good_content = itemBinding.tvCartGoodContent;
        }
    }
}
