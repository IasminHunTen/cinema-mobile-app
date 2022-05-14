package com.example.cinema_mobile_app.services.cinema_server.schedule;


import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cinema_mobile_app.services.cinema_server.MyApiRequest;
import com.example.cinema_mobile_app.utils.VolleyResponseListener;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class Schedule extends MyApiRequest {

    public Schedule(Context context, String token){
        super(context, token);
        this.root_url += "schedule/";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void get(VolleyResponseListener<JSONArray> volleyResponseListener, @Nullable String date){
        String url;
        if (date == null)
            url = this.authorize(this.add_query(null));
        else
            url = this.authorize(this.add_query(this.mapFactory("date", date)));
        this.JsonArrayHttpReq(Request.Method.GET, url, null, volleyResponseListener);
    }
}
