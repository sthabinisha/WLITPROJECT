package com.example.user.wlitlogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;import com.example.user.wlitlogin.Adapter.CustomAdapter;
import com.example.user.wlitlogin.Model.Items;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
private ArrayList<Items> itemsArrayList=new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        RecyclerView rv = (RecyclerView) findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        prepareData();
        adapter = new CustomAdapter(itemsArrayList);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(verticalLayoutmanager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {

                            case R.id.action_home:


                            case R.id.action_camera:
                                intent=new Intent(HomePage.this,Messgae.class);
                                startActivity(intent);


                            case R.id.action_profile:


                        }
                        return true;
                    }
                });
    }


    private void prepareData() {
        itemsArrayList.add(new Items("ram",R.drawable.dress));
        itemsArrayList.add(new Items("ram",R.drawable.dress));
        itemsArrayList.add(new Items("ram",R.drawable.dress));
        itemsArrayList.add(new Items("ram",R.drawable.dress));

    }
}

