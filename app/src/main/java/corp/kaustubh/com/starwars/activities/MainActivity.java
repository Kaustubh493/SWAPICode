package corp.kaustubh.com.starwars.activities;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements  MoviesAdapter.Listener  {
    private RecyclerView recyclerView;
    private static final String TAG = "MainActivity";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerMovie);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getMovies();
        INIT();
    }

    void INIT() {
        
        
    }
    private void getMovies() {
        try {
            JsonObjectRequest jsonMoviesRequest = new JsonObjectRequest(Request.Method.GET, Utils.FILMS_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        disableProgressDialog();
                        JSONArray jsonArray = response.getJSONArray("results");
                        setAdapter(jsonArray);
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
                    return super.parseNetworkResponse(response);
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }

                @Override
                public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
                    retryPolicy = new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
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

    private void enableProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
    }

    private void disableProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void onChildClick(MovieModel movieModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movieData", movieModel);
        Intent intent = new Intent(MainActivity.this, CommonDetailsView.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void setAdapter(JSONArray jsonArray) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List<MovieModel> moviesModel = gson.fromJson(jsonArray.toString(), new TypeToken<List<MovieModel>>() {
        }.getType());
        RecyclerView.Adapter mAdapter = new MoviesAdapter(moviesModel, this);
        recyclerView.setAdapter(mAdapter);
    }
}
