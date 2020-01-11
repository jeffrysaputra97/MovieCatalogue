package com.ithb.jeffry.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String photo;
    private String title;
    private String year;
    private String releaseDate;
    private String language;
    private String runtime;
    private String genre;
    private String description;

    public Movie() {
    }

    //setter and getter hasil generate
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(photo);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(year);
        parcel.writeString(releaseDate);
        parcel.writeString(language);
        parcel.writeString(runtime);
        parcel.writeString(genre);
    }

    private Movie(Parcel in) {
        photo = in.readString();
        title = in.readString();
        description = in.readString();
        year = in.readString();
        releaseDate = in.readString();
        language = in.readString();
        runtime = in.readString();
        genre = in.readString();
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
}
