package android.of.road.com.behavior.user;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;

public class UserTabLayoutBehavior extends CoordinatorLayout.Behavior<TabLayout> {


    private int mMaxY = 0;

    private int mDiffY = 0;


    public UserTabLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TabLayout child, View dependency) {
        return dependency instanceof TabLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TabLayout child, View dependency) {
        //计算百分比
        float percent = dependency.getY() / dependency.getHeight();
        if (percent >= 1) {
            percent = 1f;
        }

        if (mMaxY == 0) {
            mMaxY = dependency.getBottom() - child.getHeight();
            mDiffY = (int) (mMaxY - child.getHeight() * 0.6f);
        }
        //todo 遗留tablayout悬停的问题

        child.setY(mMaxY - mDiffY * percent);

        return true;
    }
}
