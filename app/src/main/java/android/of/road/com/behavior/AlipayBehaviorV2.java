package android.of.road.com.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by jc on 2018-07-13.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class AlipayBehaviorV2 extends CoordinatorLayout.Behavior<LinearLayout> {


    /**
     * X轴坐标
     */
    private float mViewMaxX = 0;

    /**
     * View的宽度
     */
    private int mViewWidth;
    /**
     * View的高度
     */
    private int mViewHeight;

    /**
     * Y轴的最大高度
     */
    private int mViewMaxY = 0;


    public AlipayBehaviorV2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        //计算出每个View的宽度
        mViewWidth = dependency.getWidth() / 4/*四个View*/;
        //高度
        mViewHeight = child.getHeight();

        //计算居中X轴,第一个  随便给的默认值
        mViewMaxX = 50;
        //计算Y轴 坐标系参考View的高度二分之一减去menu的高度除以2，刚好居中
        mViewMaxY = dependency.getHeight() / 2 - mViewHeight / 2;
        //重新规划 宽度
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = mViewWidth;
        }
        child.setLayoutParams(layoutParams);

        //计算百分比  当前的百分比其实是没有减去状态栏的
        float mPercent = dependency.getY() / (dependency.getHeight());
        if (mPercent >= 1f)
            mPercent = 1;
//        Log.e("onDependentViewChanged", "" + mPercent);
// 动态更改 View的高度
//        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = (int) (mViewHeight - (mViewHeight /** 0.8f*/) * mPercent);
            layoutParams.width = (int) (mViewWidth - (mViewWidth * mPercent));
            child.setLayoutParams(layoutParams);
        }

        //设置X轴坐标
        child.setX(mViewMaxX - mViewMaxX * mPercent);
        // 设置Y轴坐标
        child.setY(mViewMaxY - mViewMaxY * mPercent);

        return true;
    }
}
