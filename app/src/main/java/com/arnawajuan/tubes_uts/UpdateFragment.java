package com.arnawajuan.tubes_uts;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.arnawajuan.tubes_uts.database.DatabaseClient;
import com.arnawajuan.tubes_uts.model.Reservation;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.util.Date;

public class UpdateFragment extends Fragment {
    private static final String TAG = "UpdateFragment";
    TextInputEditText eTName;
    TextView tvDate,tvTime;
    Button saveBtn, deleteBtn, cancelBtn;
    Reservation reservation;

    int tHour,tMinutes;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        reservation = (Reservation) getArguments().getSerializable("reservation");
        eTName = view.findViewById(R.id.update_name);
        tvDate = view.findViewById(R.id.update_date);
        tvTime = view.findViewById(R.id.update_time);

        saveBtn = view.findViewById(R.id.btn_update);
        deleteBtn = view.findViewById(R.id.btn_delete);
        cancelBtn = view.findViewById(R.id.btn_cancel);

        try {
            if (reservation.getFullName() != null) {
                eTName.setText(reservation.getFullName());
                tvDate.setText(reservation.getDate());
                tvTime.setText(reservation.getTime());

            } else {
                eTName.setText("-");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservation.setFullName(eTName.getText().toString());
                reservation.setDate(tvDate.getText().toString());
                reservation.setTime(tvTime.getText().toString());
                update(reservation);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(reservation);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateFragment.this).commit();
            }
        });

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog, mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG,"onDateSet : dd/mm/yyyy : " + dayOfMonth + "/" +month+ "/" +year);

                String date = + dayOfMonth + "/" +month+ "/" +year;
                tvDate.setText(date);
            }
        };

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHour = hourOfDay;
                                tMinutes = minute;

                                String time = tHour + ":" + tMinutes;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try{
                                    Date date =f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    tvTime.setText(f12Hours.format(date));
                                }catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(tHour,tMinutes);
                timePickerDialog.show();
            }
        });
    }

    private void update(final Reservation reservation){
        class UpdateReservation extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .reservationDao()
                        .update(reservation);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Reservation updated", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateFragment.this).commit();
            }
        }

        UpdateReservation update = new UpdateReservation();
        update.execute();
    }

    private void delete(final Reservation reservation){
        class DeleteReservation extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .reservationDao()
                        .delete(reservation);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Reservation deleted", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateFragment.this).commit();
            }
        }

        DeleteReservation delete = new DeleteReservation();
        delete.execute();
    }
}
