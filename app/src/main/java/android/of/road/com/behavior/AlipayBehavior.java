package android.of.road.com.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by jc on 2018-07-13.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class AlipayBehavior extends CoordinatorLayout.Behavior<ImageView> {

    /**
     * 最大化的时候X轴的坐标轴
     */
    private int mMaxX = 0;
    /**
     * 最小化的时候X轴的坐标轴
     */
    private int mMixX = 0;
    private int mDiffX = 0;
    /**
     * 最大化的时候Y轴的坐标轴
     */
    private int mMaxY = 0;

    /**
     * 每个按钮之间的间距
     */
    private int mSpace = 40;

    /**
     * 左边第一个按钮的间距
     */
    private int mLeftSpace = 40;

    /**
     * 依赖View的宽度
     */
    private int mViewWidth = 0;
    /**
     * 依赖View的高度
     */
    private int mViewHeight = 0;
    /**
     * ImageView最大宽度和最小宽度的差值
     */
    private float mDifferenceWidth = 0;
    /**
     * ImageView最大高度和最高宽度的差值
     */
    private float mDifferenceHeight = 0;


    public AlipayBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        //初始化宽度
        if (mViewWidth == 0 || mViewHeight == 0) {
            mViewWidth = child.getWidth();
            mViewHeight = child.getHeight();
        }


        //计算差值
        if (mDifferenceWidth == 0) {
            mDifferenceWidth = child.getWidth() * 0.6f;
            mDifferenceHeight = child.getMaxHeight() * 0.6f;
        }

        //取出view中的tag下标
        int position = Integer.parseInt((String) child.getTag());

        // 平均宽度
        int averageWidth = dependency.getWidth() / 4/*总共放了4个view，所以分成了四份*/;
        if (mMaxX == 0) {
            //初始化X最大轴坐标
            // 总宽度 除以4份 除以二
            mMaxX = averageWidth * position + averageWidth / 2 - child.getWidth() / 2;
            //初始化X轴最小坐标轴

            // 最小宽度乘以下标 加上间距  最小宽度为0.6  goto 80 line
            mMixX = (int) ((child.getWidth() * 0.4f + mSpace) * position) + mLeftSpace/*返回按钮的宽度*/;
            mDiffX = mMaxX - mMixX;
        }

        //滑动的Y轴与高度的百分百比例值
        float percent = dependency.getY() / dependency.getHeight();
        if (percent >= 1)
            percent = 1;
        //初始化Y轴
        if (mMaxY == 0) {
            mMaxY = dependency.getHeight() / 2 - child.getHeight() / 2;
        }
        // 更改大小  宽度
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        //布局参数不为空
        if (layoutParams != null) {
            //计算百分百的差值
            layoutParams.height = (int) (mViewHeight - mDifferenceWidth * percent);
            layoutParams.width = (int) (mViewHeight - mDifferenceHeight * percent);
            child.setLayoutParams(layoutParams);
        }


        // X轴坐标，最终会减少到 开始的X轴坐标，这样是不正确的，我需要让他
        // 减少到最终的最终的X轴坐标，也就是说我需要再最开始的时候将开场和
        // 结束的X轴坐标都计算出来
        // TODO 剩余抖动的问题
        child.setX(mMaxX - mDiffX * percent);
        child.setY(mMaxY - mMaxY * percent + mSpace);


        return true;
    }
}
