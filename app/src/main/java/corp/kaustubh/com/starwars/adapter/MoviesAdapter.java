package corp.kaustubh.com.starwars.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import corp.kaustubh.com.starwars.R;
import corp.kaustubh.com.starwars.activities.CommonDetailsView;
import corp.kaustubh.com.starwars.model.MovieModel;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<MovieModel> movies;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movies_row,
                parent, false);
        return new ViewHolder(view);
    }

    public MoviesAdapter(List<MovieModel> movieModels, Context mContext) {
        super();
        this.movies = movieModels;
        this.context = mContext;
        Log.e("Movies_Adapter: ", movieModels.toString());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        try {
            holder.txtProducer.setText(movies.get(position).getProducer());
            holder.txtTitle.setText(movies.get(position).getTitle());
            holder.txtReleasedDate.setText(movies.get(position).getReleaseDate());
            holder.txtMovieNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<MovieModel> movieModel = new ArrayList<>();
                    movieModel.add(movies.get(position));
                    String videoCode = getVideoCode(holder.txtTitle.getText().toString());
                    Intent intent = new Intent(context, CommonDetailsView.class);
                    intent.putExtra(context.getResources().getString(R.string.video_key), videoCode);
                    intent.putParcelableArrayListExtra("moivedata", movieModel);
                    context.startActivity(intent);
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


    private String getVideoCode(String movieTitle) {
        if (movieTitle.equalsIgnoreCase(context.getResources().getString(R.string.AOC))) {
            return "gYbW1F_c9eM";
        } else if (movieTitle.equalsIgnoreCase(context.getResources().getString(R.string.empire_strikes_back))) {
            return "JNwNXF9Y6kY";
        } else if (movieTitle.equalsIgnoreCase(context.getResources().getString(R.string.new_hope))) {
            return "vZ734NWnAHA";
        } else if (movieTitle.equalsIgnoreCase(context.getResources().getString(R.string.phantom_menaced))) {
            return "bD7bpG-zDJQ";
        } else if (movieTitle.equalsIgnoreCase(context.getResources().getString(R.string.return_of_jedi))) {
            return "7L8p7_SLzvU";
        } else if (movieTitle.equalsIgnoreCase(context.getResources().getString(R.string.revenge_of_sith))) {
            return "5UnjrG_N8hU";
        } else if (movieTitle.equalsIgnoreCase(context.getResources().getString(R.string.the_force_awakens))) {
            return "sGbxmsDFVnE";
        }
        return "";
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
}
