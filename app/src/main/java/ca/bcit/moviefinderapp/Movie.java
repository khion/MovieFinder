package ca.bcit.moviefinderapp;

public class Movie {
    private String movie_name;
    private String release_date;
    private String poster_url;

    public Movie() {
    }

    public Movie(String movie_name, String release_date, String poster) {
        this.movie_name = movie_name;
        this.release_date = release_date;
        this.poster_url = poster;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_name='" + movie_name + '\'' +
                ", release_date=" + release_date +
                ", poster_url='" + poster_url + '\'' +
                '}';
    }
}
