package corp.kaustubh.com.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        recyclerView = findViewById(R.id.recyclerMovie);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getMovies();
    }

    private void getMovies() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResults> call = apiInterface.getAllMovies();
        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {

                MoviesResults movies = (MoviesResults) response.body().getResults();
                mAdapter = new Movies_Adapter(movies.Results, Movies.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {

            }
        });

    }


}
