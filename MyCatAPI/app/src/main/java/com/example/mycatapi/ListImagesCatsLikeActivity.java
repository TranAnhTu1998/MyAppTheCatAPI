package com.example.mycatapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ListImagesCatsLikeActivity extends AppCompatActivity {
    ArrayList<Cat> listCatsLike;
    RecyclerView recyclerViewListImageLikeCats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_images_cats_like);

        Toolbar toolbar = findViewById(R.id.id_toolbar_images_like_cats);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Images like of cats");
        toolbar.setNavigationIcon(R.drawable.icon_back);

        listCatsLike = MainActivity.listImagesLikeOfCats;
        //System.out.println("ListImage.listCats.. :" + listCatsLike.get(0).getLinkImageCat());

        ArrayList<Cat> listMostFavoriteCats = new ArrayList<Cat>();
        int limit = Math.min(10, listCatsLike.size());
        for(int i = 0; i < limit; i++){
            int indexSource = listCatsLike.size()-i-1;
            Cat newCat = listCatsLike.get(indexSource);
            listMostFavoriteCats.add(newCat);
        }

        //System.out.println("ListImageActi.ListMost..:" + listMostFavoriteCats.get(0).getLinkImageCat());

        recyclerViewListImageLikeCats = findViewById(R.id.id_recycler_view_image_likes_cats);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ListImagesCatsLikeActivity.this);
        recyclerViewListImageLikeCats.setLayoutManager(layoutManager);
        RecyclerViewAdapterImagesLikeCats adapter = new RecyclerViewAdapterImagesLikeCats(this, listMostFavoriteCats);
        recyclerViewListImageLikeCats.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
