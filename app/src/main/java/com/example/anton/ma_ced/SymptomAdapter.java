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
class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.View_Holder> {
    private List<Symptom> symptomList;
    Context context;


    public SymptomAdapter(List<Symptom> symptomList, Context context)
    {
    this.context = context;
    this.symptomList = symptomList;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptom_list, parent, false);
        return new View_Holder(itemView);
    }

    @Override
    public int getItemCount() {
        return symptomList.size();
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        String s= symptomList.get(position).getCalendar().getTime().toString();
        String s1 = symptomList.get(position).getSymptom();
            /*String s= "fdsg";
            String s1 = "sfdgd";*/
           holder.title.setText(s);
           holder.description.setText(s1);







    }

    public class View_Holder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView description;


        View_Holder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.sympomtitle);
            description = (TextView) itemView.findViewById(R.id.symptomdescription);

        }


    }
}