package me.darklost.driversimulation.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.darklost.driversimulation.R;
import me.darklost.driversimulation.activity.base.ToolbarActivity;
import me.darklost.driversimulation.fragment.LightFragment;
import me.darklost.driversimulation.fragment.VoiceFragment;

/**
 * Created by dengke on 15/8/28.
 */
public class ContentActivity extends ToolbarActivity {
    public static final String CONTENT_TAG = "CONTENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int tag = getIntent().getIntExtra(CONTENT_TAG, 0);
        setContentView(R.layout.activity_content);
        switch (tag) {
            case 0:

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_activity, LightFragment.getInstance())
                        .commit();
                break;
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_activity, VoiceFragment.getInstance())
                        .commit();
                break;
        }
    }

    @Override
    public void findView() {

    }

    @Override
    public void setOnListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
