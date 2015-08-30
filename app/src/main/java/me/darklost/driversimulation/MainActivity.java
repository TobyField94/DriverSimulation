package me.darklost.driversimulation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import me.darklost.driversimulation.activity.ContentActivity;
import me.darklost.driversimulation.activity.base.ToolbarActivity;
import me.darklost.driversimulation.adapter.MenuAdapter;
import me.darklost.driversimulation.view.FullyLinearLayoutManager;
import me.darklost.driversimulation.view.MyItemDecoration;
import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends ToolbarActivity implements MenuAdapter.onItemClickListner {

    private RecyclerView main_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public void findView() {
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        main_recyclerview = (RecyclerView) findViewById(R.id.menu_recyclerview);
        // 创建一个线性布局管理器
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);

        // 设置布局管理器
        main_recyclerview.setLayoutManager(layoutManager);
        main_recyclerview.addItemDecoration(new MyItemDecoration());
    }

    @Override
    public void setOnListener() {

    }


    @Override
    //初始化数据
    public void initData() {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("灯光模拟考试");
        arr.add("科目三考试手动播报");
        MenuAdapter adapter = new MenuAdapter(arr);
        adapter.setOnItemClickLisntner(this);
        main_recyclerview.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View v, int position) {
        System.out.println("position=" + position);
        Intent intent= new Intent(this, ContentActivity.class);

        intent.putExtra(ContentActivity.CONTENT_TAG,position);

        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.menu_about);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_about:
                System.out.println("About");
                final MaterialDialog dialog=new MaterialDialog(this);
                dialog.setTitle(R.string.about_us);
                dialog.setMessage("路考助手-是由D.K.开发的一款用于路考语音模拟的App\n\n联系方式：\nQQ:453523830\ntel:18670333743");
                dialog.setPositiveButton("知道了！", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
