package corp.kaustubh.com.starwars;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("films/")
    Call<MoviesResults> getAllMovies();
}
