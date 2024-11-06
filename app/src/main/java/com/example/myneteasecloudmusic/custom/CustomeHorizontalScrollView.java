package com.example.myneteasecloudmusic.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;

public class CustomeHorizontalScrollView extends HorizontalScrollView {

    public CustomeHorizontalScrollView(Context context) {
        super(context);
    }

    public CustomeHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull MotionEvent ev) {
        // 检查滑动方向，如果是水平滑动，则拦截事件
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float deltaX = ev.getX() - ev.getHistoricalX(0);
            float deltaY = ev.getY() - ev.getHistoricalY(0);
            // 如果水平滑动距离大于垂直滑动距离，则拦截事件
            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
