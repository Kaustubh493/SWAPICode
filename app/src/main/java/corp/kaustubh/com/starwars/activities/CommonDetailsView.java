package corp.kaustubh.com.starwars.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import corp.kaustubh.com.starwars.R;
import corp.kaustubh.com.starwars.model.MovieModel;
import corp.kaustubh.com.starwars.utils.Config;

public class CommonDetailsView extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    MovieModel movieModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common__details__view);
        Bundle bundle = getIntent().getExtras();

        try {
            if (bundle != null) {
                if (bundle.containsKey("movieData")) {
                    movieModel = bundle.getParcelable("movieData");
                }

            }

            YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_view_movies);
            youTubePlayerView.initialize(Config.API_KEY, this);
            init();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        TextView txtTitle = findViewById(R.id.txt_Title);
        TextView txtDirector = findViewById(R.id.txt_director);
        TextView txtProducer = findViewById(R.id.txt_Producer);
        TextView txtOpenCrawl = findViewById(R.id.txt_Opening_Crawl);
        TextView txtReleasedDate = findViewById(R.id.txt_Release_Date);

        txtTitle.setText(movieModel.getTitle());
        txtOpenCrawl.setText(movieModel.getOpeningCrawl());
        txtReleasedDate.setText(movieModel.getReleaseDate());
        txtProducer.setText(movieModel.getProducer());
        txtDirector.setText(movieModel.getDirector());
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(getVideoCode(movieModel.getTitle()));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private String getVideoCode(String movieTitle) {
        if (movieTitle.equalsIgnoreCase(getResources().getString(R.string.AOC))) {
            return "gYbW1F_c9eM";
        } else if (movieTitle.equalsIgnoreCase(getResources().getString(R.string.empire_strikes_back))) {
            return "JNwNXF9Y6kY";
        } else if (movieTitle.equalsIgnoreCase(getResources().getString(R.string.new_hope))) {
            return "vZ734NWnAHA";
        } else if (movieTitle.equalsIgnoreCase(getResources().getString(R.string.phantom_menaced))) {
            return "bD7bpG-zDJQ";
        } else if (movieTitle.equalsIgnoreCase(getResources().getString(R.string.return_of_jedi))) {
            return "7L8p7_SLzvU";
        } else if (movieTitle.equalsIgnoreCase(getResources().getString(R.string.revenge_of_sith))) {
            return "5UnjrG_N8hU";
        } else if (movieTitle.equalsIgnoreCase(getResources().getString(R.string.the_force_awakens))) {
            return "sGbxmsDFVnE";
        }
        return "";
    }
}
