package me.darklost.driversimulation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.darklost.driversimulation.R;
import me.darklost.driversimulation.activity.ContentActivity;
import me.darklost.driversimulation.adapter.LightAdapter;
import me.darklost.driversimulation.fragment.base.BaseFragment;
import me.darklost.driversimulation.utils.MidPlayer;

/**
 * Created by dengke on 15/8/28.
 */
public class RawFragment extends BaseFragment implements  LightAdapter.onItemClickListner{

    MidPlayer mp;
    private List<Integer> raws;
    public static Fragment getInstance(){

        return  new RawFragment();

    }

    private  RecyclerView grid_recyclerview;

    private LightAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp=new MidPlayer(getActivity());
        raws=new ArrayList<Integer>();
        raws.add(R.raw.raw1);
        raws.add(R.raw.raw2);
        raws.add(R.raw.raw3);
        raws.add(R.raw.raw4);
        raws.add(R.raw.raw5);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.freeMidPlayer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }


    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart("RawFragmnet");
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageEnd("RawFragmnet");
    }

    @Override
    public void findView() {
        ContentActivity activity= (ContentActivity) getActivity();
        grid_recyclerview= (RecyclerView) activity.findViewById(R.id.grid_recyclerview);

        activity.getSupportActionBar().setTitle(R.string.raw_test);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity. getSupportActionBar().setDisplayShowHomeEnabled(true);


        // 创建一个线性布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(activity,4, LinearLayoutManager.VERTICAL, false);

        // 设置布局管理器
        grid_recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public void setListener() {
        adapter.setOnItemClickLisntner(this);
    }

    @Override
    public void initData() {
        String[] raws= getActivity().getResources().getStringArray(R.array.raw);
        adapter=new LightAdapter(Arrays.asList(raws));
        grid_recyclerview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        System.out.println("position" + position);
        try {
            mp.stopSound();
            mp.playMusic(raws.get(position));
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
