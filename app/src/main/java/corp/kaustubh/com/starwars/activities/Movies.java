package corp.kaustubh.com.starwars.activities;

import android.graphics.Movie;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

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

import java.util.ArrayList;
import java.util.List;

import corp.kaustubh.com.starwars.R;
import corp.kaustubh.com.starwars.adapter.Movies_Adapter;
import corp.kaustubh.com.starwars.model.Movies_model;
import corp.kaustubh.com.starwars.rest.ApiClient;
import corp.kaustubh.com.starwars.rest.NukeSSLCerts;
import corp.kaustubh.com.starwars.utils.Utils;

public class Movies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final int RETRYTIME = 90000;
    private static final String TAG = "Movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        recyclerView = findViewById(R.id.recyclerMovie);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getMovies();
    }


    private void getMovies() {
        try {
            JsonObjectRequest jsonMoviesRequest = new JsonObjectRequest(Request.Method.GET, Utils.FILMS_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObject=response;
                        Log.e(TAG, "onResponse: "+jsonObject.toString() );
                        JSONArray jsonArray=response.getJSONArray("results");
                        GsonBuilder gsonBuilder=new GsonBuilder();
                        Gson gson=gsonBuilder.create();
                        List<Movies_model> movies_model=gson.fromJson(jsonArray.toString(),new TypeToken<List<Movies_model>>(){}.getType());
                        mAdapter=new Movies_Adapter(movies_model,Movies.this);
                        recyclerView.setAdapter(mAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
