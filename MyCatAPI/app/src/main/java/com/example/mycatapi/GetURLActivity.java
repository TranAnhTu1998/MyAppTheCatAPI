package com.example.mycatapi;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class GetURLActivity extends AppCompatActivity {
    EditText edtURL;
    Button bntCheckURL;
    TextView txtURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geturl);

        edtURL = findViewById(R.id.id_edittext_url);
        bntCheckURL = findViewById(R.id.id_button_check_url);
        txtURL = findViewById(R.id.id_textview_url);


        bntCheckURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetURL().execute("http://"+edtURL.getText().toString().trim());
            }
        });

    }

    class GetURL extends AsyncTask<String, String, String>{

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

            if(!s.isEmpty()){
                txtURL.append(s);
            }else{
                Toast.makeText(GetURLActivity.this,"URL error!!",Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }
}
