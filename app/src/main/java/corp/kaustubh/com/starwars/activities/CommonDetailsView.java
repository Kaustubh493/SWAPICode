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
    private YouTubePlayerView youTubePlayerView;
    private Bundle bundle;
    private ArrayList<MovieModel> movieModels;
    private static final String TAG = "CommonDetailsView";
    private String VIDEO_CODE;
    private TextView txtTitle;
    private TextView txtReleasedDate;
    private TextView txtOpenCrawl;
    private TextView txtDirector;
    private TextView txtProducer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common__details__view);
        bundle = getIntent().getExtras();
        movieModels = bundle.getParcelableArrayList("moivedata");
        try {
            VIDEO_CODE = bundle.getString(getResources().getString(R.string.video_key));
            Log.e(TAG, "onCreate: " + VIDEO_CODE);
            youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view_movies);
            youTubePlayerView.initialize(Config.API_KEY, this);
            init();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        txtTitle = (TextView) findViewById(R.id.txt_Title);
        txtDirector = (TextView) findViewById(R.id.txt_director);
        txtProducer = (TextView) findViewById(R.id.txt_Producer);
        txtOpenCrawl = (TextView) findViewById(R.id.txt_Opening_Crawl);
        txtReleasedDate = (TextView) findViewById(R.id.txt_Release_Date);

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
