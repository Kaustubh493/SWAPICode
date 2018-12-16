package corp.kaustubh.com.starwars.rest;

import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApiClient extends Application {
    private RequestQueue requestQueue;
    private static final String TAG = "ApiClient";
    private static ApiClient mInstance;
    public static synchronized ApiClient getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }
    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue=Volley.newRequestQueue(getApplicationContext());
        }
    return requestQueue;
    }

    public <T> void addToRequestqueue(Request<T> tRequest){
        tRequest.setTag(TAG);
        getRequestQueue().add(tRequest);
    }



}
