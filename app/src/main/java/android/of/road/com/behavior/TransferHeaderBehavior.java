package android.of.road.com.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jc on 2018-07-12.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class TransferHeaderBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private int mOriginalHeaderX = 0;
    private int mOriginalHeaderY = 0;


    public TransferHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        if (mOriginalHeaderX == 0) {
            this.mOriginalHeaderX = dependency.getWidth() / 2 - child.getWidth() / 2;
        }

        if (mOriginalHeaderY == 0) {
            mOriginalHeaderY = dependency.getHeight() - child.getHeight();
        }


        //X轴百分比
        float mPercentX = dependency.getY() / mOriginalHeaderX;
        if (mPercentX >= 1) {
            mPercentX = 1;
        }
        //Y轴百分比
        float mPercentY = dependency.getY() / mOriginalHeaderY;
        if (mPercentY >= 1) {
            mPercentY = 1;
        }
        Log.e("onDependentViewChanged", "mPercentX:" + mPercentX + "  mPercentY:" + mPercentY);

        float x = mOriginalHeaderX - mOriginalHeaderX * mPercentX;
        if (x <= child.getWidth()) {
            x = child.getWidth();
        }
        // TODO 头像的放大和缩小没做

        child.setX(x);
        child.setY(mOriginalHeaderY - mOriginalHeaderY * mPercentY);


        return true;
    }
}
