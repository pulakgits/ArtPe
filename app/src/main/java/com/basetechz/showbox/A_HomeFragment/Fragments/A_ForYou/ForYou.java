package com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.basetechz.showbox.E_Adapter.Recycler_Movie_Child_Adapter;
import com.basetechz.showbox.E_Adapter.Recycler_Movie_Parent_Adapter;
import com.basetechz.showbox.ImageSlider;
import com.basetechz.showbox.Vie.FirebaseViewModel;
import com.basetechz.showbox.models.child_model;
import com.basetechz.showbox.SlideImgViewPagerAdapter;
import com.basetechz.showbox.databinding.FragmentForYouBinding;
import com.basetechz.showbox.models.parent_model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ForYou extends Fragment{

    public ForYou() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentForYouBinding binding;
    FirebaseFirestore database;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<slideImgModel> slideImgList = new ArrayList<>();
    private  Recycler_Movie_Parent_Adapter parentAdapter;

    // image slider variable
    SlideImgViewPagerAdapter adapter;
    private ImageSlider imageSlider;
    private ArrayList<String> idList= new ArrayList<>();
    Recycler_Movie_Parent_Adapter parent_adapter;
    private FirebaseViewModel firebaseViewModel;


    Recycler_Movie_Child_Adapter adapterChild;
    Recycler_Movie_Child_Adapter childAdapter;

    ArrayList<child_model> child_modelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForYouBinding.inflate(inflater,container,false);

        Toast.makeText(getContext(), "Create Activity", Toast.LENGTH_SHORT).show();

        binding.shimmer.startShimmer();
        ViewPager viewPager = binding.viewPagers.viewPager;
        database = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("SlideMovie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slideImgList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    slideImgModel slideImgModel = new slideImgModel();
                    slideImgModel.setSlideId(dataSnapshot.child("slideId").getValue(String.class));
                    slideImgModel.setSlideImg(dataSnapshot.child("slideImg").getValue(String.class));
                    slideImgModel.setSlideTxt(dataSnapshot.child("slideTxt").getValue(String.class));
                    slideImgModel.setVideoUrl(dataSnapshot.child("videoUrl").getValue(String.class));
                    slideImgList.add(slideImgModel);
                }

                binding.viewPagers.viewPager.setVisibility(View.VISIBLE);
                binding.viewPagers.wormDotsIndicator.setVisibility(View.VISIBLE);
                binding.parentRv.setVisibility(View.VISIBLE);
                binding.shimmer.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        adapter =  new SlideImgViewPagerAdapter(getContext(),slideImgList);
        binding.viewPagers.viewPager.setAdapter(adapter);
        binding.viewPagers.wormDotsIndicator.attachTo(binding.viewPagers.viewPager);
        // image slider
        // Create an instance of ImageSlider and pass it the ViewPager
        imageSlider = new ImageSlider(viewPager);
        // Start the automatic sliding timer
        imageSlider.startSlideTimer();



        // nested recycler view
        binding.parentRv.setHasFixedSize(true);
        binding.parentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        parent_adapter = new Recycler_Movie_Parent_Adapter(getContext());
        binding.parentRv.setAdapter(parent_adapter);
        firebaseViewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        firebaseViewModel.setReference("ForYou");
        firebaseViewModel.getAllData();

        firebaseViewModel.getParentModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<parent_model>>() {
            @Override
            public void onChanged(List<parent_model> parentModelList) {
                parent_adapter.setParentItemList(parentModelList);
                parent_adapter.notifyDataSetChanged();
            }
        });
        firebaseViewModel.getDatabaseErrorMutableLiveData().observe(getViewLifecycleOwner(), new Observer<DatabaseError>() {
            @Override
            public void onChanged(DatabaseError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        
//        firebaseDatabase.getReference().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                child_modelArrayList.clear();
//                for(DataSnapshot dataSnapshot:snapshot.child("Data").getChildren())
//                {
//                    child_model child_model=dataSnapshot.getValue(com.basetechz.showbox.models.child_model.class);
//                    child_modelArrayList.add(child_model);
//                }
//                childAdapter(child_modelArrayList);
//            }


//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        return binding.getRoot();
    }
    void childAdapter(ArrayList<child_model> child_modelArrayList)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.parentRv.setLayoutManager(layoutManager);
        childAdapter= new Recycler_Movie_Child_Adapter(getContext(),child_modelArrayList);
        binding.parentRv.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();

    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(), "Start Activity", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onResume() {
        super.onResume();
        imageSlider.startSlideTimer();
        Toast.makeText(getContext(), "Resume Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop the automatic sliding timer when the Activity is paused
        imageSlider.stopSlideTimer();
        Toast.makeText(getContext(), "Pause Activity", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getContext(), "Stop Activity", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getContext(), "Destroy Activity", Toast.LENGTH_SHORT).show();
    }
}