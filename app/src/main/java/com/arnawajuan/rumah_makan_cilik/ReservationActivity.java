package com.arnawajuan.rumah_makan_cilik;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arnawajuan.rumah_makan_cilik.adapter.ReservationAdapter;
import com.arnawajuan.rumah_makan_cilik.database.DatabaseClient;
import com.arnawajuan.rumah_makan_cilik.model.Reservation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReservationActivity extends AppCompatActivity {

    private FloatingActionButton tambahBtn;
    private SearchView pencarian;
    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        backButton = findViewById(R.id.back_button_reservation);
        tambahBtn= findViewById(R.id.btn_plus);
        refreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tambahBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFragment addFragment = new AddFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, addFragment)
                        .commit();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getReservations();
                refreshLayout.setRefreshing(false);
            }
        });

        getReservations();
        searchEmployee();


        backButton = findViewById(R.id.back_button_reservation);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, MainActivity.class));
            }
        });
    }

    private void searchEmployee() {
        pencarian = findViewById(R.id.kolom_pencarian);
        pencarian.setOnQueryTextListener (new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    private void getReservations() {
        class GetReservations extends AsyncTask<Void, Void, List<Reservation>> {

            @Override
            protected List<Reservation> doInBackground(Void... voids) {
                List<Reservation> reservationList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .reservationDao()
                        .getAll();
                return reservationList;
            }

            @Override
            protected void onPostExecute(List<Reservation> users) {
                super.onPostExecute(users);
                adapter = new ReservationAdapter(ReservationActivity.this, users);
                recyclerView.setAdapter(adapter);
                if (users.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetReservations get = new GetReservations();
        get.execute();
    }
}