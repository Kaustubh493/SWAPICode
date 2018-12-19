package corp.kaustubh.com.starwars.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import corp.kaustubh.com.starwars.R;
import corp.kaustubh.com.starwars.model.MovieModel;
import corp.kaustubh.com.starwars.utils.Config;

public class CommonDetailsView extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private ArrayList<MovieModel> movieModels;
    private final String TAG = "CommonDetailsView";
    private String VIDEO_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common__details__view);
        Bundle bundle = getIntent().getExtras();

        try {
            if (bundle != null) {
                movieModels = bundle.getParcelableArrayList("moivedata");
                VIDEO_CODE = bundle.getString(getResources().getString(R.string.video_key));
            }
            Log.e(TAG, "onCreate: " + VIDEO_CODE);
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

        txtTitle.setText(movieModels.get(0).getTitle());
        txtOpenCrawl.setText(movieModels.get(0).getOpeningCrawl());
        txtReleasedDate.setText(movieModels.get(0).getReleaseDate());
        txtProducer.setText(movieModels.get(0).getProducer());
        txtDirector.setText(movieModels.get(0).getDirector());
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(VIDEO_CODE);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
