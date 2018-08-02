package android.of.road.com.investment;

import android.of.road.com.coordinatorlayout.R;
import android.of.road.com.investment.fragment.ShopItemFragment;
import android.of.road.com.investment.fragment.dummy.DummyContent;
import android.of.road.com.investment.utls.StatusBarUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class InvestmentMainActivity extends AppCompatActivity implements ShopItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        setSupportActionBar(toolbar);

        initFragment();

    }

    private void initFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mShopItemFragment, new ShopItemFragment());


        fragmentTransaction.commit();


    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
