package anaware.memoryleaksample;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static class ExtendedUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e("UncaughtException", "Uncaught exception occured!", ex);
            if (ex.getClass().equals(OutOfMemoryError.class)) {
                try {
                    android.os.Debug.dumpHprofData(Environment.getExternalStorageDirectory().getPath() + "/dump.hprof");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set uncaught exception handler to the UI Thread
        Thread.currentThread().setUncaughtExceptionHandler(new ExtendedUncaughtExceptionHandler());

        // Invoke memory leak in a separate thread
        Thread runningThread = new Thread(new Runnable() {
            @Override
            public void run() {
                InvokeMemLeak();
            }});

        // Set uncaught exception to that thread too.
        runningThread.setUncaughtExceptionHandler(new ExtendedUncaughtExceptionHandler());
        runningThread.start();
    }

    private void InvokeMemLeak() {
        List<String> list = new ArrayList<String>();
        while (true){
            list.add("OutOfMemory is coming. OutOfMemory is coming. OutOfMemory is coming. OutOfMemory is coming...");
        }
    }
}
