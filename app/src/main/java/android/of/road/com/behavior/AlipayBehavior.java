package android.of.road.com.behavior;

import android.content.Context;
import android.of.road.com.behavior.utils.DensityUtils;
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
public class AlipayBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    /**
     * 下标
     */
    private int mPosition = -1;
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


    public AlipayBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        if (mPosition == -1) {//未初始化
            mPosition = Integer.parseInt((String) child.getTag());
            //计算出每个View的宽度
            mViewWidth = dependency.getWidth() / 4/*四个View*/;
            //高度
            mViewHeight = child.getHeight();
            //重新规划 宽度
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = mViewWidth;
            }
            child.setLayoutParams(layoutParams);

            // 总宽度 除以四。得出每一个子View的宽度，居中为
            //计算居中X轴
            mViewMaxX = mViewWidth * mPosition;
            //计算Y轴
            mViewMaxY = (int) (child.getY() + DensityUtils.dp2px(parent.getContext(), 50f));
        }

        //计算百分比  当前的百分比其实是没有减去状态栏的
        float mPercent = dependency.getY() / (dependency.getHeight()/* - ScreenUtils.getStatusHeight(parent.getContext())*/);
        if (mPercent >= 1f)
            mPercent = 1;

        // 动态更改 View的高度
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = (int) (mViewHeight - (mViewHeight /** 0.8f*/) * mPercent);
            layoutParams.width = (int) (mViewWidth - (mViewWidth * mPercent));
            child.setLayoutParams(layoutParams);
        }

        //更改 内部文字的透明底
        View mTextTitleView = child.getChildAt(1);
        if (mTextTitleView != null) {
            mTextTitleView.setAlpha(1 - (mPercent > 0.4 ? 1 : mPercent));
        }

        // 更改内部imageView的大小
        View mImageTitleView = child.getChildAt(0);
        if (mImageTitleView != null) {
            mImageTitleView.setScaleX(1 - (0.4f * mPercent));
            mImageTitleView.setScaleY(1 - (0.4f * mPercent));
        }


        //设置X轴坐标//没有计算状态栏的情况之下，滑动并不是完整的
        child.setX(mViewMaxX - (mViewMaxX - 50/*左边的距离*/) * mPercent);


        // 设置Y轴坐标
        child.setY(mViewMaxY - mViewMaxY * mPercent);


        return true;
    }
}
