package me.darklost.driversimulation.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import me.darklost.driversimulation.R;


/**
 * Created by liurenqiu on 15/7/25.
 */
public abstract class ToolbarActivity extends AppCompatActivity  {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        findView();
        initData();
        setOnListener();

    }

    public abstract void findView();

    public abstract void setOnListener();

    public abstract void initData();


    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
