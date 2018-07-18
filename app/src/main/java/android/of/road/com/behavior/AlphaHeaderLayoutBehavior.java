package android.of.road.com.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by jc on 2018-07-17.
 * Version:1.0
 * Description: 透明度变化的头布局
 * ChangeLog:
 */
public class AlphaHeaderLayoutBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    public AlphaHeaderLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        float percent = dependency.getY() / dependency.getHeight();
        if (percent >= 0.8f)
            percent = 1;

        child.setAlpha(percent);
        return true;
    }
}

