package anaselhajjaji.android.samples.roboguice;

import com.google.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

@ContentView(R.layout.activity_main)
public class HelloAndroidActivity extends Activity {
	@InjectView(R.id.textViewSample)           	TextView textViewSample;
	@InjectResource(R.string.hello_world) 		String helloMsg;
	@Inject                            			LocationManager loc; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewSample.setText(helloMsg);
    }
}

