package anaware.samples.eventbussample;

import android.content.Intent;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Start the synchronization service
        Intent serviceIntent = new Intent(this, SynchronizationService.class);
        serviceIntent.setAction(SynchronizationService.ACTION_DO_SOMETHING);
        serviceIntent.putExtra(SynchronizationService.EXTRA_PARAM1, "Some parameter passed to service.");
        startService(serviceIntent);
    }
}
