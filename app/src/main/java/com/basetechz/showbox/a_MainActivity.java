package com.basetechz.showbox;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Calendar;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.basetechz.showbox.A_HomeFragment.a_HomeFragment;
import com.basetechz.showbox.B_SearchFragment.SearchFragment;
import com.basetechz.showbox.C_DownloadFragment.DownloadFragment;
import com.basetechz.showbox.D_LibraryFragment.LibraryFragment;
import com.basetechz.showbox.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class a_MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FrameLayout content;

    int searchTextColor;
    ImageView closeButton;
    String greeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Time zone
        TimeZone timeZone = TimeZone.getDefault();
        Calendar calendar = Calendar.getInstance(timeZone);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);


        if (hour >= 5 && hour < 12) {
            greeting = "Good morning";
        } else if (hour >= 12 && hour < 18) {
            greeting = "Good afternoon";
        } else {
            greeting = "Good Evening";
        }


        // bottom navigation items color & txt color set
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnView);
        @SuppressLint({"ResourceType", "UseCompatLoadingForColorStateLists"})
        ColorStateList iconColorStateList = getResources().getColorStateList(R.drawable.bnview_item_color);
        @SuppressLint({"ResourceType", "UseCompatLoadingForColorStateLists"}) ColorStateList
                textColorStateList = getResources().getColorStateList(R.drawable.bnview_item_color_text);
        bottomNavigationView.setItemIconTintList(iconColorStateList);
        bottomNavigationView.setItemTextColor(textColorStateList);

        // click item perform operation
        binding.bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // find id for MenuItem
                int id = item.getItemId();
                if(id== R.id.home){
                    binding.toolBar.setVisibility(View.VISIBLE);
                    binding.userIcon.setVisibility(View.VISIBLE);
                    binding.toolTxt.setText(greeting);
                    loadFragment(new a_HomeFragment(),false);
                } else if (id == R.id.search) {
                    binding.toolBar.setVisibility(View.GONE);
                    loadFragment(new SearchFragment(),false);
                } else if (id== R.id.download) {
                    binding.toolBar.setVisibility(View.VISIBLE);
                    binding.userIcon.setVisibility(View.GONE);
                    binding.toolTxt.setText("Downloads");
                    loadFragment(new DownloadFragment(),false);
                } else if (id==R.id.library) {
                    binding.toolBar.setVisibility(View.VISIBLE);
                    binding.userIcon.setVisibility(View.GONE);
                    binding.toolTxt.setText("Library");
                    loadFragment(new LibraryFragment(),false);
                }
                return true;
            }
        });
        binding.bnView.setSelectedItemId(R.id.home);


    }

    private  void  loadFragment(Fragment fragment, boolean flag){
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