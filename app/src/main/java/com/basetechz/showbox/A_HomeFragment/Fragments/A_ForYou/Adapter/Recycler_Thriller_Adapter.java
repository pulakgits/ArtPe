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

public class Recycler_Thriller_Adapter extends RecyclerView.Adapter<Recycler_Thriller_Adapter.ViewHolder> {

    Context context;
    ArrayList<movie_data_model> arrayList;
    public Recycler_Thriller_Adapter(Context context,ArrayList<movie_data_model> arrayList){
        this.context = context;
        this.arrayList =arrayList;
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
        movie_data_model model = arrayList.get(position);

        Picasso.get().load(model.getSf_Poster()).into(holder.Poster);
        holder.MovieTxt.setText(model.getSf_MovieTxt());
        holder.Views.setText(model.getSf_Views());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  static  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView Poster;
        TextView MovieTxt;
        TextView Views;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Poster = itemView.findViewById(R.id.sifiPoster);
            MovieTxt = itemView.findViewById(R.id.sifiMovieTxt);
            Views = itemView.findViewById(R.id.sifiViews);
        }
    }
}
