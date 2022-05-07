package com.example.cinema_mobile_app.services.cinema_server;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cinema_mobile_app.utils.RequestQueueSingleton;
import com.example.cinema_mobile_app.utils.volleyListeners.VolleyResponseListener;

import org.json.JSONObject;

import java.util.Map;

abstract class Request {
    protected String root_url, token;
    protected RequestQueueSingleton rqs;
    Request(Context context, String token){
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

    protected void httpRequest(VolleyResponseListener volleyResponseListener,
                               int method_request, String url, @Nullable JSONObject data){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                method_request,
                url,
                data,
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
        );
        this.rqs.addToRequestQueue(jsonObjectRequest);
    }
}
