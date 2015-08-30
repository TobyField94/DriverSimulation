package me.darklost.driversimulation.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.darklost.driversimulation.R;

/**
 * Created by dengke on 15/8/27.
 */
public class VoiceAdapter extends RecyclerView.Adapter<VoiceAdapter.ViewHolder>  {
    private List<String> mList;
    private onItemClickListner mlistner;
    public VoiceAdapter(List<String> mList)
    {
        this.mList=mList;
    }
    @Override
    public VoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_voice, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final VoiceAdapter.ViewHolder holder, final int position) {

        System.out.println(mList.get(position));
        holder.voice_item_text.setText(mList.get(position));
        holder.voice_item_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mlistner) {
                    mlistner.onItemClick(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }




    public static  class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView voice_item_text;
        public CardView voice_item_card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            voice_item_text = (TextView) itemView.findViewById(R.id.voice_item_text);
            voice_item_card_view = (CardView) itemView.findViewById(R.id.voice_item_card_view);
        }
    }

    public interface onItemClickListner {

        public void onItemClick(View v, int position);
    }

    public void setOnItemClickLisntner(onItemClickListner listner) {
        this.mlistner=listner;
    }
}
