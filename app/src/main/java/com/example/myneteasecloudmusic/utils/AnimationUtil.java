package com.example.myneteasecloudmusic.utils;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationUtil {

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
}
