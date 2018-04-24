package com.gamma.billboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emers on 23/4/2018.
 */

public class Movie implements Parcelable {
    private String name;
    private String duration;
    private int img;
    private String desc;
    private boolean favorite;

    public Movie(String name, String duration, int img, String desc, boolean favorite){
        this.name = name;
        this.duration = duration;
        this.img = img;
        this.desc = desc;
        this.favorite = favorite;
    }

    protected Movie(Parcel in) {
        name = in.readString();
        duration = in.readString();
        img = in.readInt();
        desc = in.readString();
        favorite = in.readByte() != 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(duration);
        dest.writeInt(img);
        dest.writeString(desc);
        dest.writeByte((byte) (favorite ? 1 : 0));

    }

    public void readFromParcel(Parcel in){
        name = in.readString();
        duration = in.readString();
        img = in.readInt();
        duration = in.readString();
        favorite = in.readByte() != 0;
    }
}
