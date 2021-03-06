package android.of.road.com.coordinatorlayout;

import android.content.Intent;
import android.of.road.com.course1.Course1MainActivity;
import android.of.road.com.investment.InvestmentMainActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 透明
     *
     * @param view
     */
    public void translucentBehavior(View view) {
        startActivity(new Intent(this, TranslucentBehaviorActivity.class));
    }

    public void behaviorOverlapTop(View view) {
        startActivity(new Intent(this, BehaviorOverlapTopActivity.class));

    }

    public void transferHeader(View view) {
        startActivity(new Intent(this, TransferHeaderActivity.class));

    }

    public void apayBehavior(View view) {
        startActivity(new Intent(this, AlipayBehaviorActivity.class));
    }

    public void serInfo(View view) {
        startActivity(new Intent(this, UserInfoActivity.class));
    }

    public void course1Main(View view) {
        startActivity(new Intent(this, Course1MainActivity.class));

    }

    public void elme(View view) {
        startActivity(new Intent(this, InvestmentMainActivity.class));
    }

}
