package com.example.apilearning;

import com.example.apilearning.data.remote.RetrofitClient;
import com.example.apilearning.data.remote.WeatherService;

public class ApiUtils {

    // Đường dân full! https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22
    public static final String BASE_URL = "https://samples.openweathermap.org/";

    public static WeatherService getWeatherService() {
        return RetrofitClient.getClient(BASE_URL).create(WeatherService.class);
    }
}
