package android.of.road.com.behavior.user;

import android.content.Context;
import android.of.road.com.behavior.utils.DensityUtils;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class UserActionBehavior extends CoordinatorLayout.Behavior<LinearLayout> {
    private float mViewWidth = 0;
    private float mDiffWidth = 0;
    private float mViewHeight = 0;


    public UserActionBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof LinearLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {

        if (mViewWidth == 0) {
            mViewWidth = (float) (dependency.getWidth() * 0.903);
        }


        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = (int) mViewWidth;
            child.setLayoutParams(layoutParams);
        }

        //计算百分比
        float percent = dependency.getY() / (child.getHeight() * 2);
        if (percent >= 1) {
            percent = 1f;
        }


        float alpha = percent /** 1.2f*/;
        if (alpha > 1) {
            alpha = 1;
//            child.setVisibility(View.GONE);
        }


        //设置透明度
        child.setAlpha(1 - alpha);
        if (child.getAlpha() <= 0.4) {
//            child.setVisibility(View.GONE);
        }
        if (child.getAlpha() > 0.4) {
//            child.setVisibility(View.VISIBLE);
        }
        //X轴
        child.setX(dependency.getWidth() * 0.048f);


        int mCardViewHeight = DensityUtils.dp2px(parent.getContext(), 235);
        float mCardPercent = dependency.getY() / dependency.getHeight();
        if (mCardPercent >= 1) {
            mCardPercent = 1;
        }
        child.setY(mCardViewHeight - mCardViewHeight * mCardPercent);
        return true;
    }
}
