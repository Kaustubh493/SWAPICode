package corp.kaustubh.com.starwars.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import corp.kaustubh.com.starwars.R;
import corp.kaustubh.com.starwars.model.MovieModel;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<MovieModel> movies;
    private Listener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movies_row,
                parent, false);
        return new ViewHolder(view);
    }

    public MoviesAdapter(List<MovieModel> movieModels, Listener listener) {
        super();
        this.movies = movieModels;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
MovieModel movieModel = movies.get(position);
        try {
            holder.txtProducer.setText(movieModel.getProducer());
            holder.txtTitle.setText(movieModel.getTitle());
            holder.txtReleasedDate.setText(movieModel.getReleaseDate());
            holder.txtMovieNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onChildClick(movies.get(holder.getAdapterPosition()));
                }
            });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtTitle;
        TextView txtProducer;
        TextView txtReleasedDate;
        TextView txtMovieNav;

        ViewHolder(View v) {
            super(v);
            txtTitle = v.findViewById(R.id.txt_Title);
            txtProducer = v.findViewById(R.id.txt_Producer);
            txtReleasedDate = v.findViewById(R.id.txt_Release_Date);
            txtMovieNav = v.findViewById(R.id.txt_movie_nav);
        }
    }

    public interface Listener {
        void onChildClick(MovieModel movieModel);
    }
}
