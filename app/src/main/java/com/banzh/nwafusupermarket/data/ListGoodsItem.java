package com.banzh.nwafusupermarket.data;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ListGoodsItem {

    private Drawable drawable;
    private String title;
    private String content;

    public ListGoodsItem(Drawable drawable, String title, String content) {
        this.drawable = drawable;
        this.title = title;
        this.content = content;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
