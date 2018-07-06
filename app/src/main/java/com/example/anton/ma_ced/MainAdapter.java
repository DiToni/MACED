package com.example.anton.ma_ced;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by morpheus on 09.07.16.
 */
class MainAdapter extends RecyclerView.Adapter<MainAdapter.View_Holder> {
    private List<Stool> stoolList;
    private Context context;


    public MainAdapter(List<Stool> stoolList, Context context)
    {
        this.context = context;
        this.stoolList = stoolList;

    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stool_list, parent, false);
        return new View_Holder(itemView);
    }

    @Override
    public int getItemCount() {
        return stoolList.size();
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

            String s = Patient.instance().getStoolList().get(position).getTime();
            int s1 = Patient.instance().getStoolList().get(position).getScore();

            holder.title.setText(s);
            holder.description.setText(String.valueOf(s1));





    }

    public class View_Holder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView description;


        View_Holder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.stooltitle);
            description = (TextView) itemView.findViewById(R.id.stooldescription);

        }


    }
}