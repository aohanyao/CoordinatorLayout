package android.of.road.com.behavior.user;

import android.content.Context;
import android.of.road.com.behavior.utils.DensityUtils;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class UserStartBehavior extends CoordinatorLayout.Behavior<RelativeLayout> {

    private int mMaxX = 0;
    private int mMaxY = 0;

    private int mDiffY = 0;
    private int mDiffX = 0;

    public UserStartBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        return dependency instanceof RelativeLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        if (mMaxX == 0 || mMaxY == 0) {
            //X轴
            mMaxX = dependency.getWidth() / 2 - child.getWidth() / 2;
            //Y轴
            mMaxY = DensityUtils.dp2px(parent.getContext(), 105f);

            //缩小的时候的Y轴
            mDiffY = mMaxY - DensityUtils.dp2px(parent.getContext(), 22f);
            // 缩小的时候的X轴
            mDiffX = mMaxX - DensityUtils.dp2px(parent.getContext(), 90f);
        }


        //计算百分比
        float percent = dependency.getY() / dependency.getHeight();
        if (percent >= 1) {
            percent = 1f;
        }


        child.setX(mMaxX - mDiffX * percent);
        child.setY(mMaxY - mDiffY * percent);

        return true;
    }
}
