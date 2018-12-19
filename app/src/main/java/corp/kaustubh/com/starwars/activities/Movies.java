package corp.kaustubh.com.starwars.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import corp.kaustubh.com.starwars.R;
import corp.kaustubh.com.starwars.adapter.MoviesAdapter;
import corp.kaustubh.com.starwars.model.MovieModel;
import corp.kaustubh.com.starwars.rest.ApiClient;
import corp.kaustubh.com.starwars.rest.NukeSSLCerts;
import corp.kaustubh.com.starwars.utils.Utils;

public class Movies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private final int RETRYTIME = 90000;
    private static final String TAG = "Movies";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        recyclerView = findViewById(R.id.recyclerMovie);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getMovies();
    }


    private void getMovies() {
        try {
            JsonObjectRequest jsonMoviesRequest = new JsonObjectRequest(Request.Method.GET, Utils.FILMS_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        disableProgressDialog();
                        JSONArray jsonArray = response.getJSONArray("results");
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        List<MovieModel> movies_model = gson.fromJson(jsonArray.toString(), new TypeToken<List<MovieModel>>() {
                        }.getType());
                        mAdapter = new MoviesAdapter(movies_model, Movies.this);
                        recyclerView.setAdapter(mAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "onErrorResponse: " + error.toString());
                }

            }) {
                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    Log.e(TAG, "parseNetworkResponse: " + response.toString());
                    return super.parseNetworkResponse(response);
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }

                @Override
                public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
                    retryPolicy = new DefaultRetryPolicy(RETRYTIME, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                    return super.setRetryPolicy(retryPolicy);
                }
            };
            NukeSSLCerts.nuke();
            jsonMoviesRequest.setShouldCache(false);
            ApiClient.getInstance().addToRequestqueue(jsonMoviesRequest);
            enableProgressDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void enableProgressDialog(){progressDialog=new ProgressDialog(this);
    progressDialog.setTitle("Loading");
    progressDialog.setMessage("Please Wait...");
    progressDialog.show();}
    private void disableProgressDialog(){
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


}
