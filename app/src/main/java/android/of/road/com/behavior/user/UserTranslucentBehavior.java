package android.of.road.com.behavior.user;

import android.content.Context;
import android.graphics.Color;
import android.of.road.com.coordinatorlayout.R;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jc on 2018-07-12.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class UserTranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private int mToolbarHeight = 0;

    public UserTranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency instanceof TextView;
    }

    /**
     * 必须要加上  layout_anchor，对方也要layout_collapseMode才能使用
     */

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {

        // 初始化高度
        if (mToolbarHeight == 0) {
            mToolbarHeight = child.getBottom() * 2;//为了更慢的
        }
        //
        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY() / mToolbarHeight;

        //百分大于1，直接赋值为1
        if (percent >= 1) {
            percent = 1f;
        }
        if (percent > 0.4) {
            child.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        } else {
            child.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }


        // 到底顶部了，再计算透明度
        if (percent < 0.9) {
            percent = 0;
        }
        // 计算alpha通道值
        float alpha = percent * 255;
        // 255 不透明  0 透明
        // 展开的时候的比例是0
        Log.e("onDependentViewChanged", "percent:" + percent + "   alpha:" + alpha);


        //设置背景颜色
        child.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

        return true;
    }
}
