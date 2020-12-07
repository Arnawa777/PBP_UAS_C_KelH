package com.arnawajuan.rumah_makan_cilik.makanan;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.arnawajuan.rumah_makan_cilik.R;
import com.arnawajuan.rumah_makan_cilik.api.ApiClient;
import com.arnawajuan.rumah_makan_cilik.api.ApiInterface;
import com.arnawajuan.rumah_makan_cilik.model.Reservation;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMakanan extends DialogFragment {

    private static final String TAG = "UpdateFragment";
    TextInputEditText eTName, etPrice, etDesc;
    Button saveBtn, deleteBtn, cancelBtn;
    Reservation reservation;
    String id,name,desc;
    double price;

//    int tHour,tMinutes;
//    DatePickerDialog.OnDateSetListener mDateSetListener;

    public UpdateMakanan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_makanan, container, false);


        eTName = view.findViewById(R.id.update_foodname);
        etPrice = view.findViewById(R.id.update_price);
        etDesc = view.findViewById(R.id.update_desc);

        saveBtn = view.findViewById(R.id.btnU_update);
        deleteBtn = view.findViewById(R.id.btnU_delete);
        cancelBtn = view.findViewById(R.id.btnU_cancel);


        id = getArguments().getString("id", "");
        name = getArguments().getString("food_name", "");
        price = getArguments().getDouble("price", 0);
        desc = getArguments().getString("desc", "");

        eTName.setText(name);
        etPrice.setText(Double.toString(price));
        etDesc.setText(desc);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eTName.getText().toString().isEmpty())
                {
                    eTName.setError("Isikan dengan benar");
                    eTName.requestFocus();
                }
                else if(etPrice.getText().toString().isEmpty())
                {
                    etPrice.setError("Isikan dengan benar");
                    etPrice.requestFocus();
                }
                else if(etDesc.getText().toString().isEmpty())
                {
                    etDesc.setError("Isikan dengan benar", null);
                    etDesc.requestFocus();
                }
                else
                {
                    updateFood();
                }

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFood();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateMakanan.this).commit();
            }
        });

    }

    private void updateFood(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<FoodResponse> update = apiService.updateFood(id, eTName.getText().toString(),
                Double.parseDouble(etPrice.getText().toString()), etDesc.getText().toString(), "null");

        update.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), MenuMakanan.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateMakanan.this).commit();
            }
        });
    }


    private void deleteFood(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<FoodResponse> delete = apiService.deleteFood(id);

        delete.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), MenuMakanan.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal menghapus user", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), MenuMakanan.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

}