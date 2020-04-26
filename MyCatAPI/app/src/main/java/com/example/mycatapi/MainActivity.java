package com.example.mycatapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//import okhttp3.OkHttpClient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jakewharton.picasso.OkHttp3Downloader;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button bntURL;
    Button bntImage;
    Button bntListCat;
    Spinner spnSpiner;
    Button bntShowImagesLikeCats;
    ImageView imageViewBackground;
    private String X_API_KEY = "16eb5159-e041-48d3-ae9f-ae414db1a2ad";
    private String LINK_HTTP_URL = "https://api.thecatapi.com/v1/images/search?limit=10&page=10&order=Desc";
    private String LINK_HTTP_URL_BREED = "https://api.thecatapi.com/v1/breeds/search?q=sib";
    private String LINK_HTTP_URL_BREED_ID = "https://api.thecatapi.com/v1/images/search?breed_ids=beng";
    private String LINK_HTTP_URL_BREED_ALL = "https://api.thecatapi.com/v1/breeds";
    private String LINK_HTTP_URL_BREED_BENG = "https://api.thecatapi.com/v1/images/search?breed_ids=beng&limit=20&page=18";
    private String LINK_HTTP_SOURCE= "https://api.thecatapi.com/v1/images/search";
    private String BREED_ID = "breed_ids";
    private String LIMIT = "limit";
    private String PAGE = "page";

    public static ArrayList<Cat> listImagesLikeOfCats;
    private String strJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listImagesLikeOfCats = new ArrayList<>();
        /*
        bntURL = findViewById(R.id.id_button_getUrl);
        bntURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent = new Intent(MainActivity.this,GetURLActivity.class);
                startActivity(itent);
            }
        });

        bntImage = findViewById(R.id.id_button_get_image_view);
        bntImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, GetImageActivity.class);
                startActivity(intent1);
            }
        });
        */


        List<String> listBreedsOfCats = new ArrayList<String>();
        InputStream inputStream = getResources().openRawResource(R.raw.breeds_of_cats);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        try {
            while((line = bufferedReader.readLine()) != null){
                listBreedsOfCats.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listBreedsOfCats);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnSpiner = findViewById(R.id.id_spinner);
        spnSpiner.setAdapter(dataAdapter);



        bntListCat = findViewById(R.id.id_button_list_cat);
        bntListCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strBreed = spnSpiner.getSelectedItem().toString().trim();
                System.out.println("MainActivity.strBreed:  " + strBreed);

                String strBreedId = null;
                String[] arrBreed = strBreed.split(" ");
                if(arrBreed.length % 2 == 1){
                    strBreedId = (arrBreed[0].substring(0,4)).toLowerCase().trim();
                }
                else{
                    if(strBreed.equals("Dragon Li")){
                        strBreedId = "lihu";
                    }else{
                        strBreedId = (arrBreed[0].substring(0,1) + arrBreed[1].substring(0,3)).toLowerCase().trim();
                    }
                }
                //System.out.println("MainActivity.strBreedId: " + strBreedId);
                String LINK = LINK_HTTP_SOURCE + "?" + BREED_ID + "=" + strBreedId + "&" + LIMIT + "=" + "80" + "&" + PAGE + "18";
                System.out.println("MAIN.LINK: " + LINK);
                new GetURL().execute(LINK);
                /*Intent intentLinkHttpURL = new Intent(MainActivity.this, ListCatsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("word", strJson);
                intentLinkHttpURL.putExtras(bundle);
                startActivity(intentLinkHttpURL);*/
            }
        });


        bntShowImagesLikeCats = findViewById(R.id.id_button_images_cats_like);
        bntShowImagesLikeCats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, ListImagesCatsLikeActivity.class);
                startActivity(intent3);
            }
        });


    }

    class GetURL extends AsyncTask<String, String, String> {

        okhttp3.OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        @Override
        protected String doInBackground(String... strings) {
            okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
            builder.url(strings[0]).get().addHeader("x-api-key",X_API_KEY);

            Request request = builder.build();

            try {
                Response response = okHttpClient.newCall(request).execute();
                return  response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(!s.isEmpty()){
                Intent intentLinkHttpURL = new Intent(MainActivity.this, ListCatsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("word", s);
                intentLinkHttpURL.putExtras(bundle);
                startActivity(intentLinkHttpURL);
            }else{
                Toast.makeText(MainActivity.this,"URL error!!",Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }
}
