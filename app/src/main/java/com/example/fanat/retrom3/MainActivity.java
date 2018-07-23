package com.example.fanat.retrom3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fanat.retrom3.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public void vget() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL + "users/2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("snow", response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject2 = jsonObject.getJSONObject("data");

                    Log.d("snowP", "onResponse: " + jsonObject2.get("first_name"));
                    Log.d("snowP", "onResponse: " + jsonObject2.getString("last_name"));
                } catch (Exception e) {
                    Log.d("snow", "onResponse: " + e.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("snow", "onErrorResponse: " + error.getMessage().toString());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    public void vpost() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL + "users", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("snow", response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("snowP", "onResponse: " + jsonObject.getString("job"));
                    Log.d("snowP", "onResponse: " + jsonObject.getString("createdAt"));
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("snow", "onErrorResponse: " + error.getMessage().toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "the dark");
                params.put("job", "snow1");
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpost();
        vget();
    }

}
