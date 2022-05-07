package com.example.cinema_mobile_app.utils.volleyListeners;

import org.json.JSONObject;

public interface VolleyJSONArrayResponseListener extends VolleyResponseListener{
    @Override
    void onResponse(JSONObject response);

    @Override
    void onError(String errors);
}
