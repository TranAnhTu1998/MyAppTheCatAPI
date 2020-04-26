package com.example.mycatapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

public class ListCatsActivity extends AppCompatActivity {
    RecyclerView rclvMyListCat;
    private String strJSon;
    private ArrayList<Cat> myListCats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cats);

        Toolbar toolbar = findViewById(R.id.id_toolbar_list_cats);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Images of cats");
        toolbar.setNavigationIcon(R.drawable.icon_back);

        Bundle bundle = getIntent().getExtras();
        strJSon = bundle.getString("word");
        //System.out.println("listCatsActi.strJSon = null??: " + (strJSon == null));

        myListCats = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJSon);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String strURL = jsonObject.getString("url");
                Cat newCatURL = new Cat(strURL);
                myListCats.add(newCatURL);
                /*
                String strCFA_URL = jsonObject.getString("cfa_url");
                Cat newCat_CFA_URL = new Cat(strCFA_URL);
                myListCats.add(newCat_CFA_URL);

                String strVetstreetUrl = jsonObject.getString("vetstreet_url");
                Cat newCatVetstreetUrl = new Cat(strVetstreetUrl);
                myListCats.add(newCatVetstreetUrl);

                String strVcahospitalsUrl = jsonObject.getString("vcahospitals_url");
                Cat newCatVcahospitalsUrl = new Cat(strVcahospitalsUrl);
                myListCats.add(newCatVcahospitalsUrl);

                String strWikipediaUrl = jsonObject.getString("wikipedia_url");
                Cat newCatWikipediaUrl = new Cat(strWikipediaUrl);
                myListCats.add(newCatWikipediaUrl);

                 */
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rclvMyListCat = findViewById(R.id.id_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rclvMyListCat.setLayoutManager(layoutManager);
        RecyclerViewAdapterCat adapterCat = new RecyclerViewAdapterCat(this, myListCats);
        rclvMyListCat.setAdapter(adapterCat);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
