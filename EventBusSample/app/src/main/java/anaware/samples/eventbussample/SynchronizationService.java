package anaware.samples.eventbussample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

public class SynchronizationService extends IntentService {

    // Service Actions
    public static final String ACTION_DO_SOMETHING = "START";

    // Parameters
    public static final String EXTRA_PARAM1 = "EXTRA.PARAM1";

    // Default event bus
    private EventBus bus = EventBus.getDefault();

    public SynchronizationService() {
        super("SynchronizationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DO_SOMETHING.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleAction(param1);
            } else  {
                Log.e(getClass().getSimpleName(), "Unknown action sent to the service: " + action);
            }
        }
    }

    /**
     * Handle action in the provided background thread with the provided
     * parameters.
     */
    private void handleAction(String param1) {
        Log.d(getClass().getSimpleName(), "Handle action with param: " + param1);
        while(true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Log.e(getClass().getSimpleName(), "Service error...", e);
            }
            SynchronizationEvent event = new SynchronizationEvent(new Date().toString());
            bus.post(event);
        }
    }
}
