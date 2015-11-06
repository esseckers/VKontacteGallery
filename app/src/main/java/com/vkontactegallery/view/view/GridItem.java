package com.vkontactegallery.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class GridItem extends FrameLayout {
    public GridItem(Context context) {
        super(context);
    }

    public GridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
