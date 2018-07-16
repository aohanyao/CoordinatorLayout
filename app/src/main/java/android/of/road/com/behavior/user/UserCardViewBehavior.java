package android.of.road.com.behavior.user;

import android.content.Context;
import android.of.road.com.behavior.utils.DensityUtils;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class UserCardViewBehavior extends CoordinatorLayout.Behavior<CardView> {

    private int mMaxY = 0;
    private int mMargin = 0;
    private int mViewHeight = 0;


    public UserCardViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CardView child, View dependency) {
        return dependency instanceof CardView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CardView child, View dependency) {
        if (mMaxY == 0) {
            mMaxY = DensityUtils.dp2px(parent.getContext(), 85);
            mMargin = DensityUtils.dp2px(parent.getContext(), 20);
            mViewHeight = child.getHeight();
        }


        //计算百分比
        float percent = dependency.getY() / dependency.getHeight();
        if (percent >= 1) {
            percent = 1f;
        }

        child.setY(mMaxY - percent * mMaxY);

        //更改高度和左右间距

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

        if (layoutParams != null) {
            layoutParams.leftMargin = (int) (mMargin - (mMargin * percent));
            layoutParams.rightMargin = (int) (mMargin - (mMargin * percent));

            // 更改
            layoutParams.height = (int) (mViewHeight - mViewHeight * percent);

            child.setLayoutParams(layoutParams);
        }


        return true;
    }
}
