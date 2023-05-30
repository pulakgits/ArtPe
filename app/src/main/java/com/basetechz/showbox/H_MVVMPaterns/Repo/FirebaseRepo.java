package com.basetechz.showbox.H_MVVMPaterns.Repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.basetechz.showbox.G_models.child_model;
import com.basetechz.showbox.G_models.parent_model;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepo {
    public DatabaseReference databaseReference;
    private final OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;

    // constructor of main class (FirebaseRepo)
    public FirebaseRepo(OnRealtimeDbTaskComplete onRealtimeDbTaskComplete){
        this.onRealtimeDbTaskComplete=onRealtimeDbTaskComplete;
    }

    public void getAllData(String reference){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(reference).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<parent_model> parentItemList = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    parent_model parent_model = new parent_model();
                    parent_model.setParentTitle(ds.child("parentTitle").getValue(String.class));
                    parent_model.setParentId(ds.child("parentId").getValue(String.class));
                    parent_model.setAcId(ds.getKey().toString());
                    parent_model.setRef(snapshot.getKey().toString());
                    GenericTypeIndicator<ArrayList<child_model>> genericTypeIndicator =
                            new GenericTypeIndicator<ArrayList<child_model>>() {};

                    parent_model.setChildItemList(ds.child(parent_model.getParentId()).getValue(genericTypeIndicator));
                    parentItemList.add(parent_model);
                }
                Log.d("TAG", "onDataChange: "+parentItemList);
                onRealtimeDbTaskComplete.onSuccess(parentItemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFailure(error);
            }
        });
    }
    public interface OnRealtimeDbTaskComplete{
        void onSuccess(List<parent_model> parentModelList);
        void onFailure(DatabaseError error);
    }
}

