package com.example.mycatapi;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GetImageActivity extends AppCompatActivity {
    ImageView imgImage;
    Button bntGetImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getimage);
        imgImage = findViewById(R.id.id_imageview);
        bntGetImage = findViewById(R.id.id_button_get_an_image);
        bntGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String httpURL = "https://lokeshdhakar.com/projects/lightbox2/images/image-3.jpg";
                new GetAnImage().execute(httpURL);
            }
        });
    }

    public class GetAnImage extends AsyncTask<String, Void, byte[]>{
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();


        @Override
        protected byte[] doInBackground(String... strings) {
            Request.Builder builder = new Request.Builder();
            builder.url(strings[0]);

            Request request = builder.build();

            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                return response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            if(bytes.length > 0){
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgImage.setImageBitmap(bitmap);
            }else{
                Toast.makeText(GetImageActivity.this, "Image error!!!", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(bytes);
        }
    }
}
