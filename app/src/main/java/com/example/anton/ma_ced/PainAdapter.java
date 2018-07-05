package com.example.anton.ma_ced;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by morpheus on 09.07.16.
 */
class PainAdapter extends RecyclerView.Adapter<PainAdapter.View_Holder> {
    private List<Pain> painList = Collections.emptyList();
    private Context context;

    public PainAdapter(List<Pain> painList, Context context)
    {
        this.context = context;
        this.painList = painList;

    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pain_list, parent, false);
        return new View_Holder(itemView);
    }

    @Override
    public int getItemCount() {
        return painList.size();
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(View_Holder holder, int position) {

            String s = Patient.instance().getPainList().get(position).getTime();
            int s1 = Patient.instance().getPainList().get(position).getScore();

            holder.title.setText(s);
            holder.description.setText(String.valueOf(s1));

            }






    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    public void insert(int position, Pain data) {
        //painList.add(position, data);
        notifyItemInserted(position);
    }


    public class View_Holder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView description;


        View_Holder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);

        }


    }

    /*public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item;
        public TextView item2;


        public ViewHolder(View itemView)
        {
            super(itemView);
            item = (TextView)itemView.findViewById(R.id.paintv1);
            item2 =(TextView) itemView.findViewById(R.id.paintv2);

        }

        @Override
        public void onCLick(View v){
            PainFragment PainFragment = new PainFragment();

            StoolList stoolList = new StoolList();
            stoolList.getSupportFragmentManager();
        }
    }*/
}