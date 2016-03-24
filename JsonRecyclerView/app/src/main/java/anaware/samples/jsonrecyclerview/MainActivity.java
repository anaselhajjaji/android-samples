package anaware.samples.jsonrecyclerview;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private android.support.v7.widget.RecyclerView.LayoutManager layoutManager;

    // The UI Handler
    private Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Initialize the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Show progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.loading_msg));
        progressDialog.show();

        // Fetch songs
        String url = getString(R.string.data_url_github);
        fetchSongs(url, progressDialog);
    }

    private void fetchSongs(String url, final ProgressDialog progressDialog) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(
                url,
                new BaseJsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, final Object response) {
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI
                                recyclerView.setAdapter(new JsonAdapter((List<Song>)response));
                                progressDialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, final Object errorResponse) {
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Failed to download data: " + errorResponse, Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        });
                    }

                    @Override
                    protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("yyyy-MM-dd'T'hh:MM:ss");
                        Gson gson = gsonBuilder.create();
                        Type collectionType = new TypeToken<List<Song>>() {
                        }.getType();
                        List<Song> songs = gson.fromJson(rawJsonData, collectionType);
                        return songs;
                    }
                });
    }
}
