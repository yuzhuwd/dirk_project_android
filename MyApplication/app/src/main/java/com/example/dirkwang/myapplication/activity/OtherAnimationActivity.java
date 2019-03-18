package com.example.dirkwang.myapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dirkwang.myapplication.R;

public class OtherAnimationActivity extends AppCompatActivity {

    private TextView mFlashEntry;
    private ImageView mFlashView;
    private ImageView mMVPView;

    private TextView mBattleEntry;
    private TextView mBattle;

    private TextView mWinEntry;
    private TextView mWinNun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_animation);

        mFlashEntry = (TextView) findViewById(R.id.tv_flash_entry);
        mFlashView = (ImageView) findViewById(R.id.iv_flash);
        mMVPView = (ImageView) findViewById(R.id.iv_mvp);

        mBattleEntry = (TextView) findViewById(R.id.tv_battle_entry);
        mBattle = (TextView) findViewById(R.id.tv_battle);

        mWinEntry = (TextView) findViewById(R.id.tv_win_entry);
        mWinNun = (TextView) findViewById(R.id.tv_num);

        setFlashAnimation();
        setBattleAnimation();
        setWinAnimation();
    }

    //  闪烁动画
    private void setFlashAnimation() {
        mFlashEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlashView.setVisibility(View.VISIBLE);
                int mvp_width = mMVPView.getMeasuredWidth();
                int flash_width = mFlashEntry.getMeasuredWidth();
                int total_width = mvp_width + flash_width;
                Log.d("dirk", "宽度：" + total_width);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mFlashView, "translationX", -flash_width, mvp_width);
                objectAnimator.setDuration(500);
                objectAnimator.setRepeatCount(5);

                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mFlashView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                objectAnimator.start();
            }
        });
    }

    // 战斗力变化动画
    private void setBattleAnimation() {
        final int startSize = 40;
        int endSize = 50;
        final int startValue = 3000;
        final int endValue = 9245;

        final ValueAnimator animator_front = ValueAnimator.ofFloat(startSize, endSize);
        animator_front.setDuration(400);

        animator_front.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mBattle.setTextSize(TypedValue.COMPLEX_UNIT_PX, animatedValue);
            }
        });


        final ValueAnimator animator_middle = ValueAnimator.ofFloat(endSize, endSize);
        animator_middle.setDuration(800);

        animator_middle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float a = valueAnimator.getAnimatedFraction();
                int value = (int) (startValue + (endValue - startValue) * a);
                mBattle.setText(String.valueOf(value));
            }
        });

        final ValueAnimator animator_end = ValueAnimator.ofFloat(endSize, startSize);
        animator_end.setDuration(400);

        animator_end.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mBattle.setTextSize(TypedValue.COMPLEX_UNIT_PX, animatedValue);
            }
        });

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator_front, animator_middle, animator_end);

        mBattleEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //animator_front.start();
                animatorSet.start();
            }
        });
    }

    // 设置连胜
    private void setWinAnimation() {
        mWinEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = 0 - mWinNun.getMeasuredHeight();
                Log.d("dirk", "连胜动画：height:" + height);
                ObjectAnimator objectAnimator_front = ObjectAnimator.ofFloat(mWinNun, "translationY",  0, height);
                objectAnimator_front.setDuration(250);

                ObjectAnimator objectAnimator_middle = ObjectAnimator.ofFloat(mWinNun, "translationY",  -height, height);
                objectAnimator_middle.setDuration(500);
                objectAnimator_middle.setRepeatCount(4);
                final int[] count = {1, 2, 3, 4, 5};
                objectAnimator_middle.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        mWinNun.setText(String.valueOf(count[0]));
                        count[0] = count[0] + 1;
                    }
                });

                ObjectAnimator objectAnimator_end = ObjectAnimator.ofFloat(mWinNun, "translationY",  -height, 0);
                objectAnimator_end.setDuration(250);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(objectAnimator_front, objectAnimator_middle, objectAnimator_end);
                animatorSet.start();

/*                final TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -height, height);
                translateAnimation.setDuration(500);
                translateAnimation.setRepeatCount(4);
                final int[] count = {1, 2, 3, 4, 5};*/

/*                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mWinNun.setText("5");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        mWinNun.setText(String.valueOf(count[0]));
                        count[0] = count[0] + 1;
                    }
                });

                mWinNun.startAnimation(translateAnimation);*/
            }
        });
    }
}
