package kg.geekteck.filmsapp.data.models;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class Film {
    String id;
    String title;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("original_title_romanised")
    String originalTitleRomanised;
    String description;
    String director;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("movie_banner")
    String movieBanner;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitleRomanised() {
        return originalTitleRomanised;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Uri getMovieBanner() {
        return Uri.parse(movieBanner);
    }
}
