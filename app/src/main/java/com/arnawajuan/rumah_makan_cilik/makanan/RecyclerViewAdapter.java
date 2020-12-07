package com.arnawajuan.rumah_makan_cilik.makanan;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.arnawajuan.rumah_makan_cilik.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RoomViewHolder>implements Filterable {
    private String id;
    private List<FoodDAO> dataList;
    private List<FoodDAO> filteredDataList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<FoodDAO> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.filteredDataList = dataList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_recycler_view, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RoomViewHolder holder, int position) {
        final FoodDAO brg = filteredDataList.get(position);
        holder.twNama.setText(brg.getFood_name());
        holder.tvPrice.setText(Double.toString(brg.getPrice()));
        holder.tvDesc.setText(brg.getDescription());


        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                UpdateMakanan dialog = new UpdateMakanan();
                dialog.show(manager, "dialog");

                Bundle args = new Bundle();
                args.putString("id", brg.getId());
                args.putString("food_name", brg.getFood_name());
                args.putDouble("price", brg.getPrice());
                args.putString("image_food", brg.getImage_food());
                args.putString("desc", brg.getDescription());
                dialog.setArguments(args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twNama, tvPrice,tvDesc;
        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            twNama = itemView.findViewById(R.id.tvNama);
            tvPrice = itemView.findViewById(R.id.tvHarga);
            tvDesc = itemView.findViewById(R.id.tvDeskripsi);
            mParent = itemView.findViewById(R.id.ParentAdapter);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if (charSequenceString.isEmpty()) {
                    filteredDataList = dataList;
                } else {
                    List<FoodDAO> filteredList = new ArrayList<>();
                    for (FoodDAO foodDAO : dataList) {
                        if (foodDAO.getFood_name().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(foodDAO);
                        }
                        filteredDataList = filteredList;
                    }

                }
                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredDataList = (List<FoodDAO>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}