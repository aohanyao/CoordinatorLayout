package android.of.road.com.behavior;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jc on 2018-07-12.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    /**标题栏的高度*/
    private int mToolbarHeight = 0;

    public TranslucentBehavior(Context context, AttributeSet attrs) {
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

        // 计算alpha通道值
        float alpha = percent * 255;


        //设置背景颜色
        child.setBackgroundColor(Color.argb((int) alpha, 63, 81, 181));

        return true;
    }
}
