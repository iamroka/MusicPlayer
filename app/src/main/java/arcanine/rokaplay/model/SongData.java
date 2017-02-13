package arcanine.rokaplay.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Acer on 6/25/2016.
 *//*
 *
public class SongData {
    private long id;
    private String title;
    private String artist;
    private String duration;

    public SongData() {
    }

    public SongData(long id, String title, String artist, String duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
*/
public class SongData implements Parcelable {
    private long id;
    private String title;
    private String artist;
    private String duration;
    private String albumArt;

    public SongData() {
    }

    public SongData(long id, String title, String artist, String duration, String albumArt) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.albumArt=albumArt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    protected SongData(Parcel in) {
        id = in.readLong();
        title = in.readString();
        artist = in.readString();
        duration = in.readString();
        albumArt=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(duration);
        dest.writeString(albumArt);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SongData> CREATOR = new Parcelable.Creator<SongData>() {
        @Override
        public SongData createFromParcel(Parcel in) {
            return new SongData(in);
        }

        @Override
        public SongData[] newArray(int size) {
            return new SongData[size];
        }
    };
}