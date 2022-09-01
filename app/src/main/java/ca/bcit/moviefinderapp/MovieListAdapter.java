package ca.bcit.moviefinderapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>  {

    private Activity context;
    private List<Movie> movieList;

    public MovieListAdapter(Activity context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(context)
                .inflate(R.layout.movie_list, parent, false);

        return new ViewHolder(cv);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        Movie currentItem = movieList.get(position);

        TextView movie_title = cardView.findViewById(R.id.title_output);
        TextView movie_year = cardView.findViewById(R.id.date_output);
        ImageView movie_poster = cardView.findViewById(R.id.poster_view);

        movie_title.setText(currentItem.getMovie_name());
        movie_title.setSelected(true);
        movie_year.setText(currentItem.getRelease_date());

        String imageUrl = currentItem.getPoster_url();

        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(movie_poster);
        }

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
