package me.darklost.driversimulation.fragment.base;



import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println(this.getClass().getName() + " onCreate");


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        System.out.println(this.getClass().getName() + " onActivityCreated");
        findView();
        initData();
        setListener();
    }


    public abstract void findView();
    public abstract void setListener();
    public abstract void initData();

}
