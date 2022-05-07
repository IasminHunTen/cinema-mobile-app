package com.example.cinema_mobile_app.utils.volleyListeners;

import org.json.JSONObject;

public interface VolleyResponseListener {
    void onResponse(JSONObject response);
    void onError(String errors);
}
