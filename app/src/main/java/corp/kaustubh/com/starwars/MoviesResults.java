package corp.kaustubh.com.starwars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResults {
    @Expose
    @SerializedName("results")
    public List<Movies_model> Results = null;

    public List<Movies_model> getResults() {
        return Results;
    }

    public void setResults(List<Movies_model> results) {
        Results = results;
    }
}
