package ca.bcit.moviefinderapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieDataService {
    Context context;
    private static final String OMDB_API_KEY = "f1fa29cc";
    List<Movie> movie_report = new ArrayList<>();

    public MovieDataService(Context context) {
        this.context = context;
    }

    public interface MovieResponse {
        void onError(String message);
        void onResponse(List<Movie> movie_report);
    }

    /**
     * Method for building the url.
     * @param dataInput text that is entered in the search bar
     * @return a url for request
     */
    private String urlBuilder(String dataInput) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.omdbapi.com")
                .appendPath("")
                .appendQueryParameter("s", dataInput)
                .appendQueryParameter("apikey", OMDB_API_KEY);
        return builder.build().toString();
    }

    /**
     * Method for fetching JSON data from requests. Creating Movie object and add to the request queue.
     * @param data_input as string input from user
     * @param movieResponse as an interface
     */
    public void getMovieData(String data_input, MovieResponse movieResponse) {
        String url = urlBuilder(data_input);
        movie_report.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray search_list = response.getJSONArray("Search");

                            for (int i = 0; i < search_list.length(); i++) {
                                Movie movie_object = new Movie();
                                JSONObject movie_from_api = (JSONObject) search_list.get(i);
                                movie_object.setMovie_name(movie_from_api.getString("Title"));
                                movie_object.setRelease_date(movie_from_api.getString("Year"));
                                movie_object.setPoster_url(movie_from_api.getString("Poster"));
                                movie_report.add(movie_object);
                            }

                            movieResponse.onResponse(movie_report);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", error.toString());
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        MovieSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

}
