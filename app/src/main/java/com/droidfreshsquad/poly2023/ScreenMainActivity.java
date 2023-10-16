package com.droidfreshsquad.poly2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.droidfreshsquad.poly2023.Adapter.CategoryAdapter;
import com.droidfreshsquad.poly2023.Adapter.ExploreAdapter;
import com.droidfreshsquad.poly2023.Domain.CategoryDomain;
import com.droidfreshsquad.poly2023.Domain.ExploreDomain;

import java.util.ArrayList;

public class ScreenMainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewExploreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_main);

        recyclerViewCategory();
        RecyclerViewExplore();
    }

    private void RecyclerViewExplore() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewExploreList = findViewById(R.id.recyclerview2);
        recyclerViewExploreList.setLayoutManager(linearLayoutManager);

        ArrayList<ExploreDomain> explore = new ArrayList<>();
        explore.add(new ExploreDomain("expore2","Paris","France"));
        explore.add(new ExploreDomain("expore2","Paris","France"));
        explore.add(new ExploreDomain("expore2","Paris","France"));

        adapter = new ExploreAdapter(explore);
        recyclerViewExploreList.setAdapter(adapter);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerview1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Flight","cat1"));
        category.add(new CategoryDomain("Hotel","cat2"));
        category.add(new CategoryDomain("Car","cat3"));
        category.add(new CategoryDomain("Train","cat4"));

        adapter=new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

}