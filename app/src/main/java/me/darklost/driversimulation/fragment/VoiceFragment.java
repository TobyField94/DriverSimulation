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
import me.darklost.driversimulation.adapter.VoiceAdapter;
import me.darklost.driversimulation.fragment.base.BaseFragment;
import me.darklost.driversimulation.utils.MidPlayer;

/**
 * Created by dengke on 15/8/28.
 */
public class VoiceFragment extends BaseFragment implements  VoiceAdapter.onItemClickListner{

    MidPlayer mp;
    private List<Integer> voices;
    public static Fragment getInstance(){

        return  new VoiceFragment();

    }

    private  RecyclerView grid_recyclerview;

    private VoiceAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp=new MidPlayer(getActivity());
        voices=new ArrayList<Integer>();
        voices.add(R.raw.voice_0);
        voices.add(R.raw.voice_1);
        voices.add(R.raw.voice_2);
        voices.add(R.raw.voice_3);
        voices.add(R.raw.voice_4);
        voices.add(R.raw.voice_5);
        voices.add(R.raw.voice_6);
        voices.add(R.raw.voice_7);
        voices.add(R.raw.voice_8);
        voices.add(R.raw.voice_9);
        voices.add(R.raw.voice_10);
        voices.add(R.raw.voice_11);
        voices.add(R.raw.voice_12);
        voices.add(R.raw.voice_13);
        voices.add(R.raw.voice_14);
        voices.add(R.raw.voice_15);
        voices.add(R.raw.voice_16);
        voices.add(R.raw.voice_17);
        voices.add(R.raw.voice_18);
        voices.add(R.raw.voice_19);
        voices.add(R.raw.voice_20);
        voices.add(R.raw.voice_21);
        voices.add(R.raw.voice_22);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart("VoiceFragmnet");
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageEnd("VoiceFragmnet");
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
    public void findView() {
        ContentActivity activity= (ContentActivity) getActivity();
        grid_recyclerview= (RecyclerView) activity.findViewById(R.id.grid_recyclerview);

        activity.getSupportActionBar().setTitle(R.string.voice_test);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity. getSupportActionBar().setDisplayShowHomeEnabled(true);


        // 创建一个线性布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(activity,7, LinearLayoutManager.VERTICAL, false);

        // 设置布局管理器
        grid_recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public void setListener() {
        adapter.setOnItemClickLisntner(this);
    }

    @Override
    public void initData() {
        String[] lights= getActivity().getResources().getStringArray(R.array.voice);
        adapter=new VoiceAdapter(Arrays.asList(lights));
        grid_recyclerview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        System.out.println("position" + position);
        try {
            mp.stopSound();
            mp.playMusic(voices.get(position));
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
