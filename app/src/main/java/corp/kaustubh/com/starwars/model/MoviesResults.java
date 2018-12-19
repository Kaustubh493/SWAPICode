package corp.kaustubh.com.starwars.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResults {
    @Expose
    @SerializedName("results")
    public List<MovieModel> Results;
    public List<MovieModel> getResults() {
        return Results;
    }
}
