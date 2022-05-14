package com.example.cinema_mobile_app.utils;

import org.json.JSONObject;

public interface VolleyResponseListener<T> {
    void onResponse(T response);
    void onError(String error);
}
