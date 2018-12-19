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

    private void INIT() {
        Button btn_movies = findViewById(R.id.btn_movies);
        Button btn_characters = findViewById(R.id.btn_characters);
        Button btn_vehicles = findViewById(R.id.btn_vehicles);
        Button btn_planets = findViewById(R.id.btn_planets);
        btn_movies.setOnClickListener(this);
        btn_characters.setOnClickListener(this);
        btn_vehicles.setOnClickListener(this);
        btn_planets.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {

            case R.id.btn_movies:
                intent = new Intent(MainActivity.this, Movies.class);

                break;

            case R.id.btn_characters:
                intent = new Intent(MainActivity.this, Characters.class);

                break;

            case R.id.btn_vehicles:
                intent = new Intent(MainActivity.this, Vehicles.class);

                break;

            case R.id.btn_planets:
                intent = new Intent(MainActivity.this, Planets.class);

                break;
        }
        startActivity(intent);
    }


}
