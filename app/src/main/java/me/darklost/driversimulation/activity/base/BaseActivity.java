package me.darklost.driversimulation.activity.base;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by dengke on 15/9/2.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onResume(this);
    }
}
