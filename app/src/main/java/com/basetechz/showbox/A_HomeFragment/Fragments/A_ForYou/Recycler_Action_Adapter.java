package com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basetechz.showbox.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recycler_Action_Adapter extends RecyclerView.Adapter<Recycler_Action_Adapter.ViewHolder> {
    // create constructor of Recycler_Action_Adapter
    // initialise arraylist & context variable
    Context context;
    ArrayList<movie_data_model> arrayList = new ArrayList<>();
    public Recycler_Action_Adapter(Context context,ArrayList<movie_data_model> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        movie_data_model model = arrayList.get(position);

        Picasso.get().load(model.getSf_Poster()).into(holder.sf_Poster);
        holder.sf_MovieTxt.setText(model.getSf_MovieTxt());
        holder.sf_Views.setText(model.getSf_Views());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
