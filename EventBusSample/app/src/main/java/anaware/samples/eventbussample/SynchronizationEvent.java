package anaware.samples.eventbussample;

public class SynchronizationEvent {

    public String eventData;

    public SynchronizationEvent(String data) {
        this.eventData = data;
    }

    public String getEventData() {
        return eventData;
    }
}
