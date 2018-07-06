package com.example.anton.ma_ced;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Fragment class for each nav menu item
 */
public class StoolFragment extends Fragment {



    View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.stool_list, container, false);
        // Inflate the layout for this fragment
       /* FragmentActivity fragmentActivity = new FragmentActivity();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentActivity);
        recyclerView.setLayoutManager(layoutManager);

        List stoolList = Patient.instance().getStoolList();

        MainAdapter mAdapter = new MainAdapter(stoolList);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
        return v;
    }
}