package com.example.cinema_mobile_app.services.cinema_server;

import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cinema_mobile_app.utils.RequestQueueSingleton;
import com.example.cinema_mobile_app.utils.VolleyResponseListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class MyApiRequest {
    protected String root_url, token;
    protected RequestQueueSingleton rqs;
    protected MyApiRequest(Context context, String token){
        this.root_url = "https://cinema-app-server.herokuapp.com/api/doc/";
        this.token = token;
        this.rqs = RequestQueueSingleton.getInstance(context);
    }


    protected String authorize(String url) {
        return url + "token=" + this.token;
    }

    protected String add_query(@Nullable Map<String, String> query_params){
        String url = this.root_url + '?';
        if (query_params == null)
            return url;
        for (Map.Entry<String, String> entry: query_params.entrySet())
            url += entry.getKey() + "=" + entry.getValue()+"&";
        return url;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected Map<String, String> mapFactory(String... args) {
        Iterator<String> it = Arrays.stream(args).iterator();
        Map<String, String> acc = new HashMap<>();
        while(it.hasNext())
            acc.put(it.next(), it.next());
        return acc;
    }

    protected void jsonObjectHttpReq(int method,
                           String url,
                           @Nullable Map<String, String> payload,
                           VolleyResponseListener<JSONObject> volleyResponseListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                method, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                }
        ){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mapFactory("accept", "application/json");
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return payload;
            }
        };
        this.rqs.addToRequestQueue(jsonObjectRequest);
    }

    protected void JsonArrayHttpReq(int method,
                                    String url,
                                    @Nullable Map<String, String> payload,
                                    VolleyResponseListener<JSONArray> volleyResponseListener){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                method, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.toString());
                    }
                }
        ){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mapFactory("accept", "application/json");
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return payload;
            }
        };
        this.rqs.addToRequestQueue(jsonArrayRequest);
    }
}
