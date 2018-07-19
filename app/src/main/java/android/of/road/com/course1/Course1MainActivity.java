package android.of.road.com.course1;

import android.content.Intent;
import android.of.road.com.coordinatorlayout.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Course1MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1_main);
    }

    /**
     * 第一步
     * 在XML中使用
     *
     * @param view
     */
    public void createCoordinatorLayout(View view) {
        //打开第一个页面
        openActivity(CreateCoordinatorLayoutActivity.class);
    }

    /**
     * 第二步
     *
     * @param view
     */
    public void createAppBarLayout(View view) {
        //打开第二个页面
        openActivity(CreateAppBarLayoutActivity.class);
    }

    public void openActivity(Class activity) {
        startActivity(new Intent(this, activity));

    }
}
