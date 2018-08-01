package android.of.road.com.investment.behavior;

import android.content.Context;
import android.of.road.com.behavior.utils.DensityUtils;
import android.of.road.com.coordinatorlayout.R;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jc on 2018-08-01.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class MerchantInfoBehavior extends CoordinatorLayout.Behavior<View> {

    private TextView mImageView;
    private int mOffset = -1;
    private int mViewOffsetHeight = -1;


    /**
     * 优惠券
     */
    private LinearLayout llCard;
    /**
     * 根布局
     */
    private LinearLayout llMerchantRoot;

    /**
     * 优惠券的高度
     */
    private int mCardHeight = -1;
    /**
     * 商家推荐
     */
    private TextView mTvMerchantRecord;
    /**
     * 商检推荐高度
     */
    private int mTvMerchantRecordHeight = -1;

    public MerchantInfoBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (mImageView == null) {
            mImageView = parent.findViewById(R.id.vImage);
            llCard = parent.findViewById(R.id.llCard);
            llMerchantRoot = parent.findViewById(R.id.llMerchantRoot);
            mTvMerchantRecord = parent.findViewById(R.id.mTvMerchantRecord);

            mCardHeight = llCard.getHeight();
            mTvMerchantRecordHeight = mTvMerchantRecord.getHeight();
        }


        //--------------------上面的图片
        if (mOffset == -1) {
            mOffset = DensityUtils.dp2px(parent.getContext(), 72f);
            mViewOffsetHeight = mImageView.getHeight() - mOffset;
        }
        // 计算百分比
        float mTopImagePre = dependency.getY() / mViewOffsetHeight;
        if (mTopImagePre >= 1f) {
            mTopImagePre = 1f;
        }

        mImageView.setText("" + mTopImagePre);
        // 移动Y轴的距离
        llMerchantRoot.setY(-(mViewOffsetHeight * mTopImagePre));

        //--------------------上面的图片

        // -----------更改 优惠券的高度 start
        // 上面整块一起滑动
        float mCardPre = dependency.getY() / (mImageView.getHeight());
        if (mCardPre > 1) {
            mCardPre = 1f;
        }
        //优惠券
        llCard.setAlpha(1 - mCardPre);
        ViewGroup.LayoutParams layoutParams = llCard.getLayoutParams();
        layoutParams.height = (int) (mCardHeight - (mCardHeight * mCardPre));
        llCard.setLayoutParams(layoutParams);
        // -----------更改 优惠券的高度 start


        //-------------商家推荐 start
        // 参照物，模仿阻尼效果
        float mTopImageTagPre = dependency.getY() / mViewOffsetHeight;

        if (mTopImageTagPre > 1.2f) {// 滑动关闭

            // 减去各种各样的高度
            float mMearchantPre = (dependency.getY() - mViewOffsetHeight - mCardHeight) / mTvMerchantRecordHeight;
            if (mMearchantPre > 1) {
                mMearchantPre = 1f;
            }
            if (mMearchantPre < 0) {
                mMearchantPre = 0f;
            }
            mTvMerchantRecord.setText("" + mMearchantPre);

            //更改高度
            ViewGroup.LayoutParams layoutParams1 = mTvMerchantRecord.getLayoutParams();
            layoutParams1.height = (int) (mTvMerchantRecordHeight - (mTvMerchantRecordHeight * mMearchantPre));
            mTvMerchantRecord.setLayoutParams(layoutParams1);


            // 商家推荐  这几个文字

        }

        //-------------商家推荐 end


        return super.onDependentViewChanged(parent, child, dependency);
    }
}
