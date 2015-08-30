package me.darklost.driversimulation.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liurenqiu on 15/8/8.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    Paint paint = new Paint();
    private int start;

    public MyItemDecoration(){
        this(0);
    }

    public MyItemDecoration(int start){
        this.start = start;
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        paint.setColor(Color.parseColor("#e5e5e5"));
        for (int i = start, size = parent.getChildCount(); i < size; i++) {
            View child = parent.getChildAt(i);
            c.drawLine(child.getLeft()+NineGridlayout.dip2px(parent.getContext(), 62), child.getBottom(), child.getRight(), child.getBottom(), paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
