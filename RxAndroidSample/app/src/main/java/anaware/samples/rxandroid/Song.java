package anaware.samples.rxandroid;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Song {

    @SerializedName("TrackId")
    String trackId;

    @SerializedName("Artist")
    String artist;

    @SerializedName("SongDate")
    Date songDate;

    @SerializedName("Title")
    String title;

    @SerializedName("TrackImage")
    String trackImage;

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Date getSongDate() {
        return songDate;
    }

    public void setSongDate(Date songDate) {
        this.songDate = songDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrackImage() {
        return trackImage;
    }

    public void setTrackImage(String trackImage) {
        this.trackImage = trackImage;
    }
}
