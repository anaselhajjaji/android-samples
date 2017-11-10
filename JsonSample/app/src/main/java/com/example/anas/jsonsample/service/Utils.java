package com.example.anas.jsonsample.service;

/**
 * Created by anas on 10/11/2017.
 */

public class Utils {
    public static final String BASE_URL = "https://fantasy.premierleague.com/";
    //public static final String BASE_URL = "https://raw.githubusercontent.com/";

    public static JsonService getJsonService() {
        return RetrofitClient.getClient(BASE_URL).create(JsonService.class);
    }
}
