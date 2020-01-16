package com.example.apilearning;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apilearning.data.model.Clouds;
import com.example.apilearning.data.model.Coord;
import com.example.apilearning.data.model.Main;
import com.example.apilearning.data.model.Sys;
import com.example.apilearning.data.model.Weather;
import com.example.apilearning.data.model.WeatherResponse;
import com.example.apilearning.data.model.Wind;
import com.example.apilearning.data.remote.WeatherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    public static String appId = "b6907d289e10d714a6e88b30761fae22";
    public static String lat = "35";
    public static String lon = "139";

    private WeatherService mWeatherService;

    private List<Weather> weathers;
    private Coord coord;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private WeatherResponse weatherResponse;

    private RecyclerView rcvWeather;

    private TextView txtCoord;
    private TextView txtMain;
    private TextView txtWind;
    private TextView txtClouds;
    private TextView txtSys;
    private TextView txtSOAR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherService = ApiUtils.getWeatherService();
        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {

        rcvWeather = findViewById(R.id.rcvWeather);

        txtCoord = findViewById(R.id.txtCoord);
        txtMain = findViewById(R.id.txtMain);
        txtWind = findViewById(R.id.txtWind);
        txtClouds = findViewById(R.id.txtClouds);
        txtSys = findViewById(R.id.txtSys);
        txtSOAR = findViewById(R.id.txtSOAR);


        //Kiểm tra phản hồi từ Server
        mWeatherService.getCurrentWeatherData(lat, lon, appId).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    weatherResponse = response.body();
                    assert response.body() != null;
                    coord = response.body().getCoord();
                    main = response.body().getMain();
                    wind = response.body().getWind();
                    clouds = response.body().getClouds();
                    sys = response.body().getSys();

                    txtCoord.setText("(Coord) " + "lon:" + coord.getLon().toString() + " lat: " + coord.getLat().toString());

                    weathers = weatherResponse.getWeather();
                    WeatherAdapter weatherAdapter = new WeatherAdapter(getApplicationContext(), weathers);
                    rcvWeather.setAdapter(weatherAdapter);
                    rcvWeather.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    txtMain.setText("Main: " + main.getTemp().toString() + "," + main.getPressure().toString() + "," + main.getHumidity().toString() + "," + main.getTempMin().toString() + "," + main.getTempMax().toString() + "," + main.getSeaLevel().toString() + "," + main.getGrndLevel().toString());
                    txtWind.setText("Wind: " + wind.getSpeed().toString() + "," + wind.getDeg().toString());
                    txtClouds.setText("Clouds: " + clouds.getAll().toString());
                    txtSys.setText("Sys: " + sys.getMessage().toString() + "," + sys.getCountry().toString() + "," + sys.getSunrise().toString() + "," + sys.getSunset().toString());
                    txtSOAR.setText("More info: " + weatherResponse.getBase().toString() + "," + weatherResponse.getDt().toString() + "," + weatherResponse.getId().toString() + "," + weatherResponse.getName().toString() + "," + weatherResponse.getCod().toString());


                }else {
                    Toast.makeText(MainActivity.this, "Response.code() != 200", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "erro");
            }
        });



    }

}
