package anaware.samples.rxandroid;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private android.support.v7.widget.RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

    // Background worker
    private Handler backgroundHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Start the background task
        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());

        // Initialize the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Show progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.loading_msg));
        progressDialog.show();

        // Fetch songs
        String url = getString(R.string.data_url_github);
        fetchSongs(url);
    }

    // Method that will create the observable in order to perform fetching songs
    void fetchSongs(String url) {
        songsObservable(url)
                // Run on a background thread
                .subscribeOn(HandlerScheduler.from(backgroundHandler))
                        // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Song>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(getClass().getSimpleName(), "onCompleted()");
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getClass().getSimpleName(), "onError()", e);
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Song> songs) {
                        recyclerView.setAdapter(new JsonAdapter(songs));
                    }
                });
    }

    // Observable class that will perform background task and notify the main UI
    static Observable<List<Song>> songsObservable(final String url) {
        return Observable.defer(new Func0<Observable<List<Song>>>() {
            @Override public Observable<List<Song>> call() {
                CloseableHttpClient httpClient = null;
                try  {
                    httpClient = HttpClientBuilder.create().build();
                    HttpGet httpget = new HttpGet(url);

                    CloseableHttpResponse response = null;
                    try {
                        response = httpClient.execute(httpget);
                        InputStream responseStream = response.getEntity().getContent();
                        return Observable.just(parseJson(responseStream));
                    }
                    catch(JsonIOException e1) {
                        return Observable.error(e1);
                    }
                    catch(JsonSyntaxException e2) {
                        return Observable.error(e2);
                    }
                    finally {
                        if (response != null) {
                            response.close();
                        }
                    }
                } catch (IOException e) {
                    return Observable.error(e);
                }
                finally {
                    if (httpClient != null) {
                        try {
                            httpClient.close();
                        } catch (IOException e) {
                            return Observable.error(e);
                        }
                    }
                }
            }
        });
    }

    // JSON parsing method using GSON
    private static List<Song> parseJson(InputStream rawJsonStream) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'hh:MM:ss");
        Gson gson = gsonBuilder.create();
        Type collectionType = new TypeToken<List<Song>>() {
        }.getType();
        List<Song> songs = gson.fromJson(new InputStreamReader(rawJsonStream), collectionType);
        return songs;
    }

    // HandlerThread that shall be used by RxAndroid
    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
