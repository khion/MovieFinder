package ca.bcit.moviefinderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText search_text;
    private RecyclerView recyclerView;
    private LottieAnimationView clapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout bgElement = (LinearLayout) findViewById(R.id.container);
        bgElement.setBackgroundColor(Color.rgb(0,0,0));

        search_text = findViewById(R.id.edit_Search);
        Button search_btn = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        clapper = findViewById(R.id.clapper_lottie);

        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);

        search_btn.setOnClickListener((View view) -> {
            String search_string = search_text.getText().toString();
            MovieDataService movieDataService = new MovieDataService(MainActivity.this);
            movieDataFetching(search_string, movieDataService);
        });
    }

    /**
     * Helper method for fetching movie data accepting the string input and
     * instantiated movieDataService object.
     * @param search_string as string input
     * @param movieDataService as movieDataService object
     */
    public void movieDataFetching(String search_string, MovieDataService movieDataService) {

        movieDataService.getMovieData(search_string, new MovieDataService.MovieResponse() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<Movie> movie_report) {
                if(movie_report != null) {
                    MovieListAdapter arrayAdapter = new MovieListAdapter(MainActivity.this,  movie_report);
                    recyclerView.setAdapter(arrayAdapter);
                    clapper.setVisibility(View.GONE);
                } else {
                    clapper.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}