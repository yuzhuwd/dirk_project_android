package com.example.dirkwang.myapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dirkwang.myapplication.R;

public class AnimationDemoActivity extends AppCompatActivity {

    private ViewGroup mViewGroup;
    private ImageView mLightIv;
    private ImageView mStarIv;
    private ImageView mMvpIv;
    private View mGoalView;
    private TextView mEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);
        mViewGroup = (ViewGroup) findViewById(R.id.rl_animation_area);
        mLightIv = (ImageView) findViewById(R.id.iv_light);
        mStarIv = (ImageView) findViewById(R.id.iv_star);
        mMvpIv = (ImageView) findViewById(R.id.iv_mvp);
        mGoalView = findViewById(R.id.v_goal);
        mEntry = (TextView) findViewById(R.id.tv_entry);

        mEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OtherAnimationActivity.class);
                startActivity(intent);
            }
        });

        // 佛光旋转
        final Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_light);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnimation.setInterpolator(lin);

        // 星光散射
        final Animation starScale = AnimationUtils.loadAnimation(this, R.anim.star_anim);


        // 整体进场
        Animation areaInScale = AnimationUtils.loadAnimation(this, R.anim.in_anim);
        areaInScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("dirk", "动画开始！");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("dirk", "动画结束！");
                mLightIv.startAnimation(rotateAnimation);
                mStarIv.setVisibility(View.VISIBLE);
                mStarIv.startAnimation(starScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("dirk", "动画重置！");
            }
        });
        mViewGroup.startAnimation(areaInScale);

        // 离场动画


        starScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        float x_1 = mViewGroup.getX();
                        float y_1 = mViewGroup.getY();
                        int width_1 = mViewGroup.getMeasuredWidth();
                        int heigh_1 = mViewGroup.getMeasuredHeight();

                        float x_2 = mGoalView.getX();
                        float y_2 = mGoalView.getY();
                        int width_2 = mGoalView.getMeasuredWidth();
                        int heigh_2 = mGoalView.getMeasuredHeight();

                        int space_x = (int) ((x_2 + width_2 / 2) - (x_1 + width_1 / 2));
                        int space_y = (int) ((y_2 + heigh_2 / 2) - (y_1 + heigh_1 / 2));
                        Log.d("dirk", "起点:【" + x_1 + "," + y_1 + "】 终点:【" + x_2 + "," + y_2 + "】");
                        Log.d("dirk", "mViewGroup:【" + width_1 + "," + heigh_1 + "】  mGoalView:【" + width_2 + "," + heigh_2 + "】");
                        Log.d("dirk", "space_x:【" + space_x + "】  space_y:【" + space_y + "】");


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            runAnimator(x_1, y_1, x_2, y_2);
                        } else {
                            runAnimation(space_x, space_y);
                        }


                    }
                }, 2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private void runAnimation(int space_x, int space_y) {
        Animation areaOutScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_scale_anim);
        TranslateAnimation areaOutTranslate = new TranslateAnimation(0, space_x, 0, space_y);
        AnimationSet animationSet = new AnimationSet(this, null);
        animationSet.addAnimation(areaOutScale);
        animationSet.addAnimation(areaOutTranslate);

        animationSet.setDuration(800);
        mViewGroup.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewGroup.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Animation_GameOver", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void runAnimator(float x_1, float y_1, float x_2, float y_2) {
        Path path = new Path();
        path.moveTo(x_1, y_1);
        float control_x = (x_1 + x_2) / 2;
        float control_y = (y_1 + y_2) / 2;


        path.quadTo(control_x, 0, x_2 + mGoalView.getWidth() / 2 - mViewGroup.getWidth() / 2, y_2 + mGoalView.getHeight() / 2 - mViewGroup.getHeight() / 2);

/*        path.moveTo(x_1, y_1);
        path.quadTo(x_1 / 2, 0, 0, 0);*/

        ObjectAnimator animator = null;
        animator = ObjectAnimator.ofFloat(mViewGroup, mViewGroup.X, mViewGroup.Y, path);
        animator.setInterpolator(new LinearInterpolator());

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mViewGroup, "scaleX", 1f, 0.15f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mViewGroup, "scaleY", 1f, 0.15f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);
        animatorSet.play(animator).with(scaleX).with(scaleY);
        //animatorSet.play(animator);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                float x = mViewGroup.getX();
                float y = mViewGroup.getY();
                int width = mViewGroup.getWidth();
                int heigh = mViewGroup.getHeight();
                Log.d("dirk", "缩小后的终点:【" + x + "," + y + "】");
                Log.d("dirk", "缩小后的width:【" + width + "】  heigh:【" + heigh + "】");
                mViewGroup.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Animator_GameOver", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
