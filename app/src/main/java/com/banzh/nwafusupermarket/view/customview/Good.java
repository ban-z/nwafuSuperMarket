package com.banzh.nwafusupermarket.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.banzh.nwafusupermarket.R;

public class Good extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    private Drawable imagesrc;

    public Good(Context context) {
        this(context, null);
    }

    public Good(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Good(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
        initAttrs(context, attrs);
    }

    private void initViews(Context context) {
        View.inflate(context, R.layout.layout_good_item, this);
        imageView = findViewById(R.id.img_good_item);
        textView = findViewById(R.id.tv_good_item);
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null){
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Good);
            imagesrc = array.getDrawable(R.styleable.Good_imgsrc);
            String titlecontent = array.getString(R.styleable.Good_tvtext);

            imageView.setImageDrawable(imagesrc);
            textView.setText(titlecontent);

            imageView.setImageDrawable(imagesrc);
            textView.setText(titlecontent);
            array.recycle();//防止内存泄漏
        }
    }
}
