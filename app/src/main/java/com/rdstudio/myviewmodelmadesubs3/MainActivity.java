package com.rdstudio.myviewmodelmadesubs3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private WeatherAdapter weatherAdapter;
    private EditText edtCity;
    private Button btnAddCity;
    private ProgressBar progressBar;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherAdapter = new WeatherAdapter();
        weatherAdapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(weatherAdapter);

        edtCity = findViewById(R.id.edt_city);
        progressBar = findViewById(R.id.progress_bar);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getWeathers().observe(this, getWeather);

        btnAddCity = findViewById(R.id.btn_city);
        btnAddCity.setOnClickListener(myListener);
    }

    private Observer<ArrayList<WeatherItems>> getWeather = new Observer<ArrayList<WeatherItems>>() {
        @Override
        public void onChanged(ArrayList<WeatherItems> weatherItems) {
            if (weatherItems != null){
                weatherAdapter.setData(weatherItems);
                // progress bar
                showLoading(false);
            }
        }
    };

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String city = edtCity.getText().toString();

            if (TextUtils.isEmpty(city)) return;

            mainViewModel.setWeather(city);
            //progress bar
            showLoading(true);
        }
    };

    // Loading bar
    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
