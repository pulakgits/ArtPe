package com.basetechz.showbox.E_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basetechz.showbox.models.child_model;
import com.basetechz.showbox.R;
import com.basetechz.showbox.VideoActivity;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class Recycler_Movie_Child_Adapter extends RecyclerView.Adapter<Recycler_Movie_Child_Adapter.ViewHolder> {

    Context context;
    private List<child_model> childItemList;

    public Recycler_Movie_Child_Adapter(Context context){
        this.context =context;
    }
    public  Recycler_Movie_Child_Adapter(Context context, List<child_model> childItemList ){
        this.context=context;
        this.childItemList=childItemList;
    }
    public void setChildItemList(List<child_model> childItemList) {
        this.childItemList = childItemList;
        this.childItemList.removeAll(Collections.singleton(null));
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        child_model model = childItemList.get(position);
        Picasso.get().load(model.getMovieImage()).into(holder.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoActivity.class);
                intent.putExtra("itemId",model.getMovieId());
                intent.putExtra("itemTxt",model.getMovieTxt());
                intent.putExtra("videoUrl",model.getMovieUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (childItemList != null){
            return childItemList.size();
        }else{
            return  0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }
}