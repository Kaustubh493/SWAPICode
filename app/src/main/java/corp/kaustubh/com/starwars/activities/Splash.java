package corp.kaustubh.com.starwars.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import corp.kaustubh.com.starwars.R;

public class Splash extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setStatusBarTranslucent();
        setContentView(R.layout.activity_splash);
        mediaPlayer = MediaPlayer.create(Splash.this, R.raw.saberon);
        mediaPlayer.start();
        int splashInterval = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* TODO Auto-generated method stub */
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                this.finish();
            }

            private void finish() {
                /* TODO Auto-generated method stub */

            }
        }, splashInterval);
    }

    protected void setStatusBarTranslucent() {
        if (true) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}
