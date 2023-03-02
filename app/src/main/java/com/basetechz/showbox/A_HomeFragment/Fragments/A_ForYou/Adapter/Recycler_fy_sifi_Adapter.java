package com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou.movie_data_model;
import com.basetechz.showbox.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recycler_fy_sifi_Adapter extends RecyclerView.Adapter<Recycler_fy_sifi_Adapter.ViewHolder> {

    Context context;
    ArrayList<movie_data_model> sfArrayList;
    public Recycler_fy_sifi_Adapter(Context context1,ArrayList<movie_data_model> sfArrayList){
        this.context = context1;
        this.sfArrayList = sfArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        movie_data_model movie_data_model = sfArrayList.get(position);

        Picasso.get().load(movie_data_model.getSf_Poster()).into(holder.sf_Poster);
        holder.sf_MovieTxt.setText(movie_data_model.getSf_MovieTxt());
        holder.sf_Views.setText(movie_data_model.getSf_Views());
    }

    @Override
    public int getItemCount() {
        return sfArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sf_Poster;
        TextView sf_MovieTxt;
        TextView sf_Views;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sf_Poster = itemView.findViewById(R.id.sifiPoster);
            sf_MovieTxt = itemView.findViewById(R.id.sifiMovieTxt);
            sf_Views = itemView.findViewById(R.id.sifiViews);
        }
    }
}