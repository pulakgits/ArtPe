package com.basetechz.showbox;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.basetechz.showbox.A_HomeFragment.a_HomeFragment;
import com.basetechz.showbox.B_SearchFragment.SearchFragment;
import com.basetechz.showbox.C_DownloadFragment.DownloadFragment;
import com.basetechz.showbox.D_LibraryFragment.LibraryFragment;
import com.basetechz.showbox.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FrameLayout content;

    int searchTextColor;
    ImageView closeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Get the search view instance
        SearchView searchView = findViewById(R.id.actionSearch);
        // Set the text color of the search query
         searchTextColor= getResources().getColor(R.color.white);
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(searchTextColor);
        searchEditText.setHintTextColor(searchTextColor);

        // Set the search icon
        searchView.setIconified(false);
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchIcon.setImageResource(R.drawable.baseline_search_24);

        closeButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeButton.setColorFilter(searchTextColor, PorterDuff.Mode.SRC_ATOP);
        // Add a listener to the search view to change the text color and close icon color when the query is submitted
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchEditText.setTextColor(searchTextColor);
                closeButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
                closeButton.setColorFilter(searchTextColor, PorterDuff.Mode.SRC_ATOP);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        // Set the query hint text color
        SpannableStringBuilder hintBuilder = new SpannableStringBuilder(searchView.getQueryHint());
        hintBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, hintBuilder.length(), 0);
        searchView.setQueryHint(hintBuilder);

        // Set the background color of the search view
        searchView.setBackgroundResource(R.drawable.search_view_bg);

        // bottom navigation items color & txt color set
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnView);
        @SuppressLint({"ResourceType", "UseCompatLoadingForColorStateLists"}) ColorStateList iconColorStateList = getResources().getColorStateList(R.drawable.bnview_item_color);
        @SuppressLint({"ResourceType", "UseCompatLoadingForColorStateLists"}) ColorStateList textColorStateList = getResources().getColorStateList(R.drawable.bnview_item_color_text);
        bottomNavigationView.setItemIconTintList(iconColorStateList);
        bottomNavigationView.setItemTextColor(textColorStateList);


        // click item perform operation
        binding.bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // find id for MenuItem
                int id = item.getItemId();
                if(id== R.id.home){
                   loadFragment(new a_HomeFragment(),false);
                } else if (id == R.id.search) {
                    loadFragment(new SearchFragment(),false);
                } else if (id== R.id.download) {
                    loadFragment(new DownloadFragment(),false);
                } else if (id==R.id.library) {
                    loadFragment(new LibraryFragment(),true);
                }
                return true;
            }
        });
        binding.bnView.setSelectedItemId(R.id.home);

    }
    private  void  loadFragment(Fragment fragment,boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(flag){
            ft.add(R.id.fragment_container,fragment);
        }else {
            ft.replace(R.id.fragment_container,fragment);
        }
        ft.commit();
    }
}