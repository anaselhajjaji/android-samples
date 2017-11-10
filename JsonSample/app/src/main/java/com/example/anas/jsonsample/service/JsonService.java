package com.example.anas.jsonsample.service;

import com.example.anas.jsonsample.model.JsonAnswer;
import com.example.anas.jsonsample.model2.JsonAnswer2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by anas on 10/11/2017.
 */

public interface JsonService {

    @GET("/drf/leagues-classic-standings/624264")
    //@GET("/bahamas10/css-color-names/master/css-color-names.json")
    Call<JsonAnswer> getAnswers();
}
