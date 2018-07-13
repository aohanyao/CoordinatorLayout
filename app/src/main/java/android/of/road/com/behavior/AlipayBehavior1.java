package android.of.road.com.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by jc on 2018-07-13.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class AlipayBehavior1 extends CoordinatorLayout.Behavior<ImageView> {

    private int mStartX = 0;
    private int mStartY = 0;
    private int mSpace = 40;

    private int mViewWidth = 0;
    private int mViewHeight = 0;


    public AlipayBehavior1(Context context, AttributeSet attrs) {
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


        //下标
        int position = Integer.parseInt((String) child.getTag());

        int averageWidth = dependency.getWidth() / 4;
        //初始化X轴坐标
        if (mStartX == 0) {
            // 总宽度 除以4份 除以二

            mStartX = averageWidth * position + averageWidth / 2 - child.getWidth() / 2;
        }

        float percentX = dependency.getY() / mStartX;
        if (percentX >= 1)
            percentX = 1;


        //初始化Y轴
        if (mStartY == 0) {
            mStartY = dependency.getHeight() / 2 - child.getHeight() / 2;
        }
        float percentY = dependency.getY() / mStartY;
        if (percentY >= 1)
            percentY = 1;


        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();

        if (layoutParams != null) {
            layoutParams.height = mViewHeight - (int) (mViewHeight / 3 * caultSize(child, dependency, mViewHeight));
            layoutParams.width = mViewHeight - (int) (mViewWidth / 3 * caultSize(child, dependency, mViewWidth));

            child.setLayoutParams(layoutParams);
        }


        child.setX(mStartX - mStartX * percentX + mSpace);
        child.setY(mStartY - mStartY * percentY + mSpace);


        return true;
    }

    private float caultSize(ImageView child, View dependency, int size) {
        //view的大小

        float mPercentViewWidth = dependency.getY() / size;

        Log.e("onDependentViewChanged", "percentX:" + mPercentViewWidth);
        if (mPercentViewWidth <= 0.5) {
            mPercentViewWidth = 0.5f;
        }

        if (mPercentViewWidth >= 1) {
            mPercentViewWidth = 1f;
        }

        return mPercentViewWidth;
    }
}
