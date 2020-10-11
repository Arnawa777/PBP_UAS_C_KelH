package com.arnawajuan.tubes_uts.adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arnawajuan.tubes_uts.R;
import com.arnawajuan.tubes_uts.UpdateFragment;
import com.arnawajuan.tubes_uts.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.UserViewHolder> implements Filterable {

    private Context context;
    private List<Reservation> reservationList;
    private List<Reservation> reservationListFull = new ArrayList<> (  );

    public ReservationAdapter(Context context, List<Reservation> list) {
        this.context = context;
        this.reservationList = list;
        reservationListFull.addAll(reservationList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reservation, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.tvName.setText(reservation.getFullName());
        holder.tvDate.setText(reservation.getDate());
        holder.tvTime.setText(reservation.getTime());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    @Override
    public Filter getFilter() {
        return filterEmployee;
    }
    private Filter filterEmployee = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Reservation> filteredList = new ArrayList<>() ;

            if(charSequence == null || charSequence.length()==0){
                filteredList.addAll(reservationListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Reservation reservation : reservationListFull){
                    if(reservation.getFullName().toLowerCase().contains(filterPattern)){
                        filteredList.add(reservation);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            reservationList.clear();
            reservationList.addAll((List<Reservation>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvDate, tvTime;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.full_name_text);
            tvDate = itemView.findViewById(R.id.date_text);
            tvTime = itemView.findViewById(R.id.time_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Reservation reservation = reservationList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("reservation", reservation);
            UpdateFragment updateFragment = new UpdateFragment();
            updateFragment.setArguments(data);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, updateFragment)
                    .commit();
        }
    }
}



