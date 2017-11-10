package com.example.anas.jsonsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.anas.jsonsample.model.JsonAnswer;
import com.example.anas.jsonsample.service.JsonService;
import com.example.anas.jsonsample.service.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StandingsResultsAdapter mAdapter;
    private JsonService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = Utils.getJsonService();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new StandingsResultsAdapter();
        recyclerView.setAdapter(mAdapter);

        prepareData();
    }

    private void prepareData() {

        mService.getAnswers().enqueue(new Callback<JsonAnswer>() {
            @Override
            public void onResponse(Call<JsonAnswer> call, Response<JsonAnswer> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body());
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode  = response.code();
                    Log.e("MainActivity", "posts failed, status code: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<JsonAnswer> call, Throwable t) {
                Log.e("MainActivity", "error loading from API", t);
            }
        });
    }
}
