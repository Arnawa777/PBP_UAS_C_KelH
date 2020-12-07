package com.arnawajuan.rumah_makan_cilik.makanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arnawajuan.rumah_makan_cilik.MainActivity;
import com.arnawajuan.rumah_makan_cilik.R;
import com.arnawajuan.rumah_makan_cilik.SharePref;
import com.arnawajuan.rumah_makan_cilik.api.ApiClient;
import com.arnawajuan.rumah_makan_cilik.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuMakanan extends AppCompatActivity {
//    private ArrayList<Makanan> ListMakanan;
//    private ActivityMenuMakananBinding binding;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerAdapter;
    private SharePref sharePref;
    private ImageView backButton;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharePref = new SharePref(this);
        if(sharePref.loadDarkModeState()){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makanan);

        swipeRefresh = findViewById(R.id.swipeRefresh);

        swipeRefresh.setRefreshing(false);
        loadUser();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUser();
            }
        });


        backButton = findViewById(R.id.back_button_menu);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuMakanan.this, MainActivity.class));
            }
        });

    }

    @Override
    public void onResume()
    {
        swipeRefresh.setRefreshing(true);
        loadUser();
        super.onResume();
    }

    public void loadUser(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<FoodResponse> call = apiService.getAllFood();

        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                generateDataList(response.body().getFood());
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Toast.makeText(MenuMakanan.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<FoodDAO> foodList) {
        recyclerView = findViewById(R.id.FoodRecyclerView);
        recyclerAdapter = new RecyclerViewAdapter(this,foodList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MenuMakanan.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String queryString) {
//                recyclerAdapter.getFilter().filter(queryString);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String queryString) {
//                recyclerAdapter.getFilter().filter(queryString);
//                return false;
//            }
//        });
    }
}