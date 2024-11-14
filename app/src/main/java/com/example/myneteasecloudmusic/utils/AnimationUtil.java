package com.example.myneteasecloudmusic.utils;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class AnimationUtil {

    private static ObjectAnimator animator3;

    // 缩放动画
    public static void setonlyAnimateView(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f).setDuration(200);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f).setDuration(200);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.8f);

                animator1.start();
                animator2.start();
                animator3.start();

                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator reverseAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1f).setDuration(200);
                        ObjectAnimator reverseAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1f).setDuration(200);
                        ObjectAnimator reverseAnimator3 = ObjectAnimator.ofFloat(view, "alpha", 0.8f, 1f);

                        reverseAnimator1.start();
                        reverseAnimator2.start();
                        reverseAnimator3.start();
                    }
                });
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.99f).setDuration(200);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.99f).setDuration(200);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.8f);

                animator1.start();
                animator2.start();
                animator3.start();

                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator reverseAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 0.99f, 1f).setDuration(200);
                        ObjectAnimator reverseAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 0.99f, 1f).setDuration(200);
                        ObjectAnimator reverseAnimator3 = ObjectAnimator.ofFloat(view, "alpha", 0.8f, 1f);

                        reverseAnimator1.start();
                        reverseAnimator2.start();
                        reverseAnimator3.start();
                    }
                });
                return true;
            }
        });
    }

    // 给有点击事件的view设置动画
    public static void setAnimateView(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f).setDuration(200);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f).setDuration(200);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.8f);

        animator1.start();
        animator2.start();
        animator3.start();

        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ObjectAnimator reverseAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1f).setDuration(200);
                ObjectAnimator reverseAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1f).setDuration(200);
                ObjectAnimator reverseAnimator3 = ObjectAnimator.ofFloat(view, "alpha", 0.8f, 1f);

                reverseAnimator1.start();
                reverseAnimator2.start();
                reverseAnimator3.start();
            }
        });
    }

    // 更快速q弹的，给播放页面准备的
    public static void setAnimateView2(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f).setDuration(50);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f).setDuration(50);

        animator1.start();
        animator2.start();

        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ObjectAnimator reverseAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f).setDuration(50);
                ObjectAnimator reverseAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f).setDuration(50);

                reverseAnimator1.start();
                reverseAnimator2.start();
            }
        });
    }
    // 震动动画
    public static void setShakeAnimateView(View view) {
        float shakeOffset = 5f;

        ValueAnimator shakeAnimator = ValueAnimator.ofFloat(0, shakeOffset, -shakeOffset, shakeOffset, 0);
        shakeAnimator.setDuration(100); // 动画持续时间
        shakeAnimator.setRepeatCount(3); // 重复次数
        shakeAnimator.setRepeatMode(ValueAnimator.REVERSE);

        shakeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                view.setTranslationX(value);
            }
        });

        shakeAnimator.start();
    }
}
