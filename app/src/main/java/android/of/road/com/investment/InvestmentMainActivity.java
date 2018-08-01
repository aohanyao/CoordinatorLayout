package android.of.road.com.investment;

import android.of.road.com.coordinatorlayout.R;
import android.of.road.com.investment.utls.StatusBarUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class InvestmentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        setSupportActionBar(toolbar);

    }

}
