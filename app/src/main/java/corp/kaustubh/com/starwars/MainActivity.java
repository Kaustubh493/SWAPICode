package corp.kaustubh.com.starwars;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_movies, btn_characters, btn_vehicles, btn_planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT();
    }

    private void INIT() {
        btn_movies = findViewById(R.id.btn_movies);
        btn_characters = findViewById(R.id.btn_characters);
        btn_vehicles = findViewById(R.id.btn_vehicles);
        btn_planets = findViewById(R.id.btn_planets);
        btn_movies.setOnClickListener(this);
        btn_characters.setOnClickListener(this);
        btn_vehicles.setOnClickListener(this);
        btn_planets.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.btn_movies:
                intent=new Intent(MainActivity.this,Movies.class);
                startActivity(intent);
                break;

            case R.id.btn_characters:
                intent=new Intent(MainActivity.this,Characters.class);
                startActivity(intent);
                break;

            case R.id.btn_vehicles:
                intent=new Intent(MainActivity.this,Vehicles.class);
                startActivity(intent);
                break;

            case R.id.btn_planets:
                intent=new Intent(MainActivity.this,Planets.class);
                startActivity(intent);
                break;
        }
    }


}
