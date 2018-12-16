package corp.kaustubh.com.starwars.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import corp.kaustubh.com.starwars.R;
import corp.kaustubh.com.starwars.utils.Config;

public class Common_Details_View extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
YouTubePlayerView   youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common__details__view);
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_view_movies);
        youTubePlayerView.initialize(Config.API_KEY,Common_Details_View.this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
