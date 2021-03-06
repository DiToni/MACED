package com.example.anton.ma_ced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.PathInterpolator;

import java.util.List;

import static com.example.anton.ma_ced.R.*;

public class StoolList extends AppCompatActivity {
    private static final String SELECTED_ITEM = "arg_selected_item";

    List<Pain> painList;
    List<Stool> stoolList;
    List<Symptom> symptomList;

    MainAdapter stoolAdapter;
    PainAdapter adapter;
    SymptomAdapter symptomAdapter;
    RecyclerView recyclerView;



    private BottomNavigationView mBottomNav;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_stool_list);

        recyclerView = findViewById(R.id.recycler);
        System.out.println(recyclerView);
        painList  = Patient.instance().getPainList();
        stoolList = Patient.instance().getStoolList();
        symptomList = Patient.instance().getSymptomList();

        stoolAdapter = new MainAdapter(stoolList, getApplication());
        adapter = new PainAdapter(painList, getApplication());
        symptomAdapter = new SymptomAdapter(symptomList, getApplication());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        mBottomNav = (BottomNavigationView) findViewById(id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressLint("SetTextI18n")
    private void selectFragment(MenuItem item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.stuhl:
                frag = MenuFragment.newInstance("Stuhl", 123456, new StoolFragment());
                recyclerView.setAdapter(stoolAdapter);
                break;
            case R.id.schmerzen:
                frag = MenuFragment.newInstance("Schmerzen", 000000, new PainFragment());
                recyclerView.setAdapter(adapter);
                break;
            case R.id.symptome:
                frag = MenuFragment.newInstance("Symptome", 654321, new SymptomFragment());
                recyclerView.setAdapter(symptomAdapter);
                break;
        }

        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, frag
            );
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }

    public void onClickButtonPlus(final View openView){
        Intent intent;
        switch(mSelectedItem){
            case R.id.stuhl:
                intent = new Intent(getApplicationContext(), CreateStool.class);
                startActivity(intent);
                break;
            case R.id.schmerzen:
                intent = new Intent(getApplicationContext(), SchmerzDokumentation.class);
                startActivity(intent);
                break;
            case R.id.symptome:
                intent = new Intent(getApplicationContext(), SymptomeDokumentation.class);
                startActivity(intent);
                break;


        }
    }
}