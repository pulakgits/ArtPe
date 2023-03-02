package com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou.Adapter.Recycler_StockMarket_Adapter;
import com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou.Adapter.Recycler_Thriller_Adapter;
import com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou.Adapter.Recycler_fy_sifi_Adapter;
import com.basetechz.showbox.HorizontalSpacingItemDecoration;
import com.basetechz.showbox.databinding.FragmentForYouBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class ForYou extends Fragment {

    public ForYou() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentForYouBinding binding;
    FirebaseFirestore database;
    ArrayList<movie_data_model> arrayList1 = new ArrayList<>();
    ArrayList<movie_data_model> arrayList2 = new ArrayList<>();
    ArrayList<movie_data_model> arrayList3 = new ArrayList<>();
    ArrayList<movie_data_model> arrayList4 = new ArrayList<>();
    Recycler_fy_sifi_Adapter adapter1;
    Recycler_Thriller_Adapter adapter2;
    Recycler_StockMarket_Adapter adapter3;
    Recycler_Action_Adapter adapter4;
    int spanCount=3;
    int spacing=8;
    boolean includeEdge=true;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForYouBinding.inflate(inflater,container,false);
        binding.shimmer.startShimmer();

       //Set Linear Layout for SiFi Movie Section
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.sifiRecycler.addItemDecoration(new HorizontalSpacingItemDecoration(spanCount, spacing, includeEdge));
        binding.sifiRecycler.setLayoutManager(layoutManager1);

        //set Layout for Thriller Movie
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.ThrillerRecycler.addItemDecoration(new HorizontalSpacingItemDecoration(spanCount, spacing, includeEdge));
        binding.ThrillerRecycler.setLayoutManager(layoutManager2);

        //set Layout for StockMarket Movie
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.StockMarketRecycler.addItemDecoration(new HorizontalSpacingItemDecoration(spanCount, spacing, includeEdge));
        binding.StockMarketRecycler.setLayoutManager(layoutManager3);

        //set Layout for Action Movie
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.ActionMRecycler.addItemDecoration(new HorizontalSpacingItemDecoration(spanCount, spacing, includeEdge));
        binding.ActionMRecycler.setLayoutManager(layoutManager4);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Clear the existing data in the array list
        arrayList1.clear();
        // for generate random question under 9
        Random random = new Random();
        final int rand = random.nextInt(6);

        // database link with SiFi movie
        database = FirebaseFirestore.getInstance();
        database.collection("ForYouSiFi")
                .whereGreaterThanOrEqualTo("index", rand)
                .orderBy("index").limit(9).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                arrayList1.clear();
                if (queryDocumentSnapshots.getDocuments().size() < 6) {
                    database.collection("ForYouSiFi")
                            .whereGreaterThanOrEqualTo("index", rand)
                            .orderBy("index").limit(6).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        movie_data_model model1
                                                = snapshot.toObject(movie_data_model.class);
                                        assert model1 != null;
                                        model1.setSf_Id(snapshot.getId());
                                        arrayList1.add(model1);
                                    }
                                }
                            });
                } else {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        movie_data_model model1 = snapshot.toObject(movie_data_model.class);
                        assert model1 != null;
                        model1.setSf_Id(snapshot.getId());
                        arrayList1.add(model1);
                    }
                }
                binding.shimmer.stopShimmer();
                binding.shimmer.setVisibility(View.GONE);
                binding.sifiRecycler.setVisibility(View.VISIBLE);
                binding.sifiTxt.setVisibility(View.VISIBLE);
                adapter1.notifyDataSetChanged();
            }
        });
        adapter1 = new Recycler_fy_sifi_Adapter(getContext(), arrayList1);
        binding.sifiRecycler.setAdapter(adapter1);

        arrayList2.clear();
        // database link with ThrillerMovie movie
        database = FirebaseFirestore.getInstance();
        database.collection("ForYouThriller")
                .whereGreaterThanOrEqualTo("index", rand)
                .orderBy("index").limit(9)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (queryDocumentSnapshots.getDocuments().size() < 6) {
                    database.collection("ForYouThriller")
                            .whereGreaterThanOrEqualTo("index", rand)
                            .orderBy("index").limit(6).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        movie_data_model model2 =
                                                snapshot.toObject(movie_data_model.class);
                                        assert model2 != null;
                                        model2.setSf_Id(snapshot.getId());
                                        arrayList2.add(model2);
                                    }
                                }
                            });
                } else {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        movie_data_model model2 = snapshot.toObject(movie_data_model.class);
                        assert model2 != null;
                        model2.setSf_Id(snapshot.getId());
                        arrayList2.add(model2);
                    }
                }
                    binding.shimmer.stopShimmer();
                    binding.shimmer.setVisibility(View.GONE);
                    binding.ThrillerRecycler.setVisibility(View.VISIBLE);
                    binding.ThrillerTxt.setVisibility(View.VISIBLE);
                adapter2.notifyDataSetChanged();
            }
        });
        adapter2 = new Recycler_Thriller_Adapter(getContext(), arrayList2);
        binding.ThrillerRecycler.setAdapter(adapter2);

        arrayList3.clear();
        // database link with Stock Market Movie
        database = FirebaseFirestore.getInstance();
        database = FirebaseFirestore.getInstance();
        database.collection("ForYouStockMarket")
                .whereGreaterThanOrEqualTo("index",rand)
                .orderBy("index")
                .limit(9).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    if(queryDocumentSnapshots.getDocuments().size()<6){
                        database.collection("ForYouStockMarket")
                            .whereGreaterThanOrEqualTo("index",rand)
                            .orderBy("index").limit(6).get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                                        movie_data_model model3
                                                = snapshot.toObject(movie_data_model.class);
                                        assert model3 != null;
                                        model3.setSf_Id(snapshot.getId());
                                        arrayList3.add(model3);
                                    }
                                }
                            });
                }else {
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                        movie_data_model model3 = snapshot.toObject(movie_data_model.class);
                        assert model3 != null;
                        model3.setSf_Id(snapshot.getId());
                        arrayList3.add(model3);
                    }
                }
                    binding.shimmer.stopShimmer();
                    binding.shimmer.setVisibility(View.GONE);
                    binding.StockMarketRecycler.setVisibility(View.VISIBLE);
                    binding.StockMarketMovieTxt.setVisibility(View.VISIBLE);
                adapter3.notifyDataSetChanged();
            }
        });
        adapter3 = new Recycler_StockMarket_Adapter(getContext(), arrayList3);
        binding.StockMarketRecycler.setAdapter(adapter3);


        // database link with Stock Market Movie
        arrayList4.clear();
        database = FirebaseFirestore.getInstance();
        database = FirebaseFirestore.getInstance();
        database.collection("ForYouAction")
                .whereGreaterThanOrEqualTo("index",rand)
                .orderBy("index")
                .limit(9).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if(queryDocumentSnapshots.getDocuments().size()<6){
                            database.collection("ForYouAction")
                                    .whereGreaterThanOrEqualTo("index",rand)
                                    .orderBy("index").limit(6).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                                                movie_data_model model4
                                                        = snapshot.toObject(movie_data_model.class);
                                                assert model4 != null;
                                                model4.setSf_Id(snapshot.getId());
                                                arrayList4.add(model4);
                                            }
                                        }
                                    });
                        }else {
                            for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                                movie_data_model model4 = snapshot.toObject(movie_data_model.class);
                                assert model4 != null;
                                model4.setSf_Id(snapshot.getId());
                                arrayList4.add(model4);
                            }
                        }
                        binding.shimmer.stopShimmer();
                        binding.shimmer.setVisibility(View.GONE);
                        binding.ActionMRecycler.setVisibility(View.VISIBLE);
                        binding.ActionMovieTxt.setVisibility(View.VISIBLE);
                        adapter4.notifyDataSetChanged();
                    }
                });
        adapter4 = new Recycler_Action_Adapter(getContext(), arrayList4);
        binding.ActionMRecycler.setAdapter(adapter4);
    }
}