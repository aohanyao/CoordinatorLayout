package android.of.road.com.behavior.user;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 用户头像的Behavior
 */
public class UserHeaderBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private int mMaxX = 0;
    private int mDiffX = 0;
    private int mDiffSize = 0;
    private int mMaxY = 0;
    private int mDiffY = 0;


    private int mHeaderSize = 0;

    public UserHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof ImageView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        //初始化 目标最大的坐标轴
        if (mMaxX == 0 || mMaxY == 0) {
            mMaxX = dependency.getWidth() / 2 - child.getWidth() / 2;
            //X轴的差距
            mDiffX = mMaxX - 100/*暂时定死*/;
            //宽高的差距是
            mDiffSize = (int) (child.getWidth() * 0.4);

            // 暂时 赋值为一个自己的高度
            mMaxY = child.getHeight();
            mDiffY = (int) (child.getHeight());

            mHeaderSize = child.getWidth();

        }


        //计算百分比
        float percent = dependency.getY() / dependency.getHeight();
        if (percent >= 1) {
            percent = 1f;
        }


        //设置X轴坐标
        child.setX(mMaxX - mDiffX * percent);
        child.setY(mMaxY - mDiffY * percent);


        //宽高
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = (int) (mHeaderSize - mDiffSize * percent);
            layoutParams.height = (int) (mHeaderSize - mDiffSize * percent);

            child.setLayoutParams(layoutParams);
        }


        return true;
    }
}
