package com.ithb.jeffry.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;

class Movie implements Parcelable {
    private int photo;
    private String title;
    private String year;
    private String releaseDate;
    private String language;
    private String runtime;
    private String genre;
    private String description;

    Movie() {
    }

    //setter and getter hasil generate
    int getPhoto() {
        return photo;
    }

    void setPhoto(int photo) {
        this.photo = photo;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String name) {
        this.title = name;
    }

    String getYear() {
        return year;
    }

    void setYear(String year) {
        this.year = year;
    }

    String getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    String getLanguage() {
        return language;
    }

    void setLanguage(String language) {
        this.language = language;
    }

    String getRuntime() {
        return runtime;
    }

    void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(photo);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(year);
        parcel.writeString(releaseDate);
        parcel.writeString(language);
        parcel.writeString(runtime);
        parcel.writeString(genre);
    }

    private Movie(Parcel in) {
        photo = in.readInt();
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
