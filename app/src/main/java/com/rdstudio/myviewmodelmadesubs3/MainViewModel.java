package com.rdstudio.myviewmodelmadesubs3;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {

    // Buat MainViewModel yang berisi variable global untuk API dan Mutable Live Data
    private static final String API_KEY = "7f141def8a6dd8d28e967e70315dbb4d";
    private MutableLiveData<ArrayList<WeatherItems>> listWeathers = new MutableLiveData<>();

    // Setter and Getter
    void setWeather(final String cities) {
        // request API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<WeatherItems> listItems = new ArrayList<>();
        String url = "http://api.openweathermap.org/data/2.5/group?id=" + cities + "&units=metric&appid=" + API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        WeatherItems weatherItems = new WeatherItems(weather);
                        listItems.add(weatherItems);
                    }

                    listWeathers.postValue(listItems);
                } catch (Exception e) {
                    Log.d("exception", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
    }

    LiveData<ArrayList<WeatherItems>> getWeathers() {
        return listWeathers;
    }


}
