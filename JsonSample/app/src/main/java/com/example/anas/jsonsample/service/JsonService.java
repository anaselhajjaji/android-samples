package com.example.anas.jsonsample.service;

import com.example.anas.jsonsample.model.JsonAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by anas on 10/11/2017.
 */
public interface JsonService {

    @GET("/drf/leagues-classic-standings/624264?phase=1&le-page=1&ls-page=1")
    Call<JsonAnswer> getAnswers();
}
