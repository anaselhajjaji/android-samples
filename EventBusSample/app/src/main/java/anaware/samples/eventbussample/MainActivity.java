package anaware.samples.eventbussample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private Handler uiHandler = new Handler();
    private TextView textView;

    // Default event bus
    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register as a subscriber
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Register as a subscriber
        bus.unregister(this);
    }

    @Subscribe
    public void onEvent(final SynchronizationEvent event){
       uiHandler.post(new Runnable() {
           @Override
           public void run() {
               textView.setText("Event received!\r\n" + event.eventData);
           }
       });
    }
}
