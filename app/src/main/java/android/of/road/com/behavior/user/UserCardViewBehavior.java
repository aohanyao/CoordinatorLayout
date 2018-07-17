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
    private float mViewWidth = 0;
    private float mDiffWith = 0;

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
            mViewWidth = (int) (child.getWidth() * 0.9f);
            mDiffWith = (int) (child.getWidth() * 0.1f);
        }


        //计算百分比
        float percent = dependency.getY() / dependency.getHeight();
        if (percent >= 1) {
            percent = 1f;
        }
        if (percent <= 0) {
            percent = 0f;
        }

        child.setY(mMaxY - percent * mMaxY);

        //更改高度和左右间距

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

        if (layoutParams != null) {
            //TODO 这里有点抖动
            // 使用间距的时候会有点问题，更改为 修改宽度和X轴
            // 如果他是1
            float mOffsetWidth = mDiffWith * percent;
            layoutParams.width = (int) (mViewWidth + mOffsetWidth);
            try {
                child.setX((mDiffWith / 2) - mOffsetWidth / 2);
            } catch (Exception e) {
                e.printStackTrace();
            }

//
//            // 更改
            layoutParams.height = (int) (mViewHeight - mViewHeight * percent);
        }

        //获取的子view
        View childView = child.getChildAt(0);

        // 透明度渐变不是那么理想
        float alpha = percent * 2;
        if (alpha > 0.5) {
            alpha = 1;
        }
        childView.setAlpha(1 - alpha);

        return true;
    }
}
