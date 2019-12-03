package com.banzh.nwafusupermarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.data.ListGoodsItem;

import java.util.List;

public class ListGoodsAdapter extends ArrayAdapter<ListGoodsItem> {

    private int resourceId;

    public ListGoodsAdapter(@NonNull Context context, int resource, @NonNull List<ListGoodsItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListGoodsItem goodsItem = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img_list_item = view.findViewById(R.id.img_list_item);
            viewHolder.tv_list_item_title = view.findViewById(R.id.tv_list_item_title);
            viewHolder.tv_list_item_content = view.findViewById(R.id.tv_list_item_content);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.img_list_item.setImageDrawable(goodsItem.getDrawable());
        viewHolder.tv_list_item_title.setText(goodsItem.getTitle());
        viewHolder.tv_list_item_content.setText(goodsItem.getContent());

        return view;
    }

    class ViewHolder{
        ImageView img_list_item;
        TextView tv_list_item_title;
        TextView tv_list_item_content;
    }
}
