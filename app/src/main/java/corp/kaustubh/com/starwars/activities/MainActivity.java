package corp.kaustubh.com.starwars.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import corp.kaustubh.com.starwars.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT();
    }

    void INIT() {
        Button btnMovies;
        btnMovies = findViewById(R.id.btn_movies);
        btnMovies.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        intent = new Intent(MainActivity.this, Movies.class);
        startActivity(intent);
    }


}
