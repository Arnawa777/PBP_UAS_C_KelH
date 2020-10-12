package com.arnawajuan.tubes_uts.makanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.arnawajuan.tubes_uts.MainActivity;
import com.arnawajuan.tubes_uts.R;
import com.arnawajuan.tubes_uts.SharePref;
import com.arnawajuan.tubes_uts.UserProfile;
import com.arnawajuan.tubes_uts.databinding.ActivityMenuMakananBinding;

import java.util.ArrayList;

public class MenuMakanan extends AppCompatActivity {
    private ArrayList<Makanan> ListMakanan;
    private ActivityMenuMakananBinding binding;
    private SharePref sharePref;
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharePref = new SharePref(this);
        if(sharePref.loadDarkModeState()){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_makanan);

        //get data mahasiswa
        ListMakanan = new DaftarMakanan().MAKANAN;

        //recycler view
        RecyclerView recyclerView = binding.recyclerViewMakanan;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapter(this, ListMakanan));


        backButton = findViewById(R.id.back_button_menu);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuMakanan.this, MainActivity.class));
            }
        });

    }
}