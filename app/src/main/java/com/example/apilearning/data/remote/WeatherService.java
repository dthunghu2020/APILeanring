package com.example.apilearning.data.remote;

import com.example.apilearning.data.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    /*@GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<List<WeatherResponse>> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<List<WeatherResponse>> getAnswers(@Query("tagged") String tags);*/
}
