package com.example.mycatapi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterImagesLikeCats extends RecyclerView.Adapter<RecyclerViewAdapterImagesLikeCats.ImagesLikeCatsViewHolder> {
    private ArrayList<Cat> listImagesCatsLike;
    private Context context;


    public RecyclerViewAdapterImagesLikeCats(Context context, ArrayList<Cat> listImagesCatsLike){
        this.listImagesCatsLike = listImagesCatsLike;
        this.context = context;
    }


    @NonNull
    @Override
    public ImagesLikeCatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_image_cat,parent,false);
        return new ImagesLikeCatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesLikeCatsViewHolder holder, int position) {
        ImageView imageViewLikeCats = holder.imageViewListCats;
        Picasso.with(context).load(listImagesCatsLike.get(position).getLinkImageCat()).into(imageViewLikeCats);
    }

    @Override
    public int getItemCount() {
        return listImagesCatsLike.size();
    }

    public class ImagesLikeCatsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewListCats;
        public ImagesLikeCatsViewHolder(View view){
            super(view);
            imageViewListCats = view.findViewById(R.id.id_imageview_like_cats);

        }
    }
}
