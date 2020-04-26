package com.example.mycatapi;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterCat extends RecyclerView.Adapter<RecyclerViewAdapterCat.CatViewHolder> {
    private Context context;
    private ArrayList<Cat> listCats;
    public RecyclerViewAdapterCat(Context context, ArrayList<Cat> arrayListCat){
        this.context = context;
        this.listCats = arrayListCat;
    }


    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cat,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        ImageView imageView = holder.imageView;
        Picasso.with(context).load(listCats.get(position).getLinkImageCat()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return listCats.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        Button bntLike;
        Button bntDislike;
        public CatViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.id_imageview_cat);
            bntLike = view.findViewById(R.id.id_like);
            bntLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    bntLike.setTextColor(Color.BLUE);
                    int positon = getAdapterPosition();

                    Cat newCat = listCats.get(positon);
                    MainActivity.listImagesLikeOfCats.add(newCat);
                    //System.out.println("Main.ListImages : " + MainActivity.listImagesLikeOfCats.get(0).getLinkImageCat());
                }
            });

            bntDislike = view.findViewById(R.id.id_dislike);
            bntDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bntDislike.setTextColor(Color.GRAY);
                }
            });
        }
    }

}
