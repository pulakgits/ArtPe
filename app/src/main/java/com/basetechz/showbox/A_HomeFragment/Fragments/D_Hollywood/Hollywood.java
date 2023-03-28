package com.basetechz.showbox.A_HomeFragment.Fragments.D_Hollywood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.basetechz.showbox.E_Adapter.Recycler_Movie_Parent_Adapter;
import com.basetechz.showbox.R;
import com.basetechz.showbox.Vie.FirebaseViewModel;
import com.basetechz.showbox.databinding.FragmentHollywoodBinding;
import com.basetechz.showbox.models.parent_model;
import com.google.firebase.database.DatabaseError;

import java.util.List;


public class Hollywood extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    FragmentHollywoodBinding binding;
    private FirebaseViewModel firebaseViewModel;
    private Recycler_Movie_Parent_Adapter parentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHollywoodBinding.inflate(getLayoutInflater());

        binding.hollywoodRecycler.setHasFixedSize(true);
        binding.hollywoodRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        parentAdapter = new Recycler_Movie_Parent_Adapter(getContext());
        binding.hollywoodRecycler.setAdapter(parentAdapter);
        firebaseViewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        firebaseViewModel.setReference("IndianFilm");
        firebaseViewModel.getAllData();
        firebaseViewModel.getParentModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<parent_model>>() {
            @Override
            public void onChanged(List<parent_model> parentModelList) {
                parentAdapter.setParentItemList(parentModelList);
                parentAdapter.notifyDataSetChanged();
            }
        });
        firebaseViewModel.getDatabaseErrorMutableLiveData().observe(getViewLifecycleOwner(), new Observer<DatabaseError>() {
            @Override
            public void onChanged(DatabaseError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}