package com.rdstudio.myviewmodelmadesubs3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private ArrayList<WeatherItems> mData = new ArrayList<>();

    public void setData(ArrayList<WeatherItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_items, parent, false);
        return new WeatherViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaKota, tvTemperature, tvDescription;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaKota = itemView.findViewById(R.id.tv_nama_kota);
            tvTemperature = itemView.findViewById(R.id.tv_temp);
            tvDescription = itemView.findViewById(R.id.tv_desc);
        }

        void bind(WeatherItems weatherItems) {
            tvNamaKota.setText(weatherItems.getName());
            tvTemperature.setText(weatherItems.getTemperature());
            tvDescription.setText(weatherItems.getDescription());
        }
    }
}
