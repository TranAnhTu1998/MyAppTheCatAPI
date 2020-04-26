package com.example.mycatapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetHttpURL {
    private Context mContext;
    private String mString = null;
    private String strJson;
    private int WAIT_TIME = 10000;
    public String getText(Context context, String http_url){
        this.mContext = context;
        new GetURL().execute(http_url);
        System.out.println("GetHttpURL.strJson = " + strJson);
        /*Thread wait = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        wait.start();*/
        return strJson;
    }

    class GetURL extends AsyncTask<String, String, String> {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        @Override
        protected String doInBackground(String... strings) {
            Request.Builder builder = new Request.Builder();
            builder.url(strings[0]);

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
            if(s != null){
                strJson = s;
                System.out.println("GetHttpURL.s : " + s);
                /*LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                View view = layoutInflater.inflate(R.layout.activity_main,null);
                Button bntListCat = view.findViewById(R.id.id_button_list_cat);
                bntListCat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentLinkHttpURL = new Intent(mContext, ListCatsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("word", strJson);
                        intentLinkHttpURL.putExtras(bundle);
                        mContext.startActivity(intentLinkHttpURL);
                    }
                });*/

            }else{
                Toast.makeText(mContext,"URL error!!",Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }
}
