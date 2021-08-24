package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView view;
    private RequestQueue mreqest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=findViewById(R.id.textView);
       // Button button=findViewById(R.id.button1);

        mreqest= Volley.newRequestQueue(this);

    }

    @Override
    protected void onStart() {
        jasonParse();
        super.onStart();
    }

    private void jasonParse() {

        String Url="https://gorest.co.in/public/v1/posts";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("data");
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        int id = object.getInt("id");
                        int user_id=object.getInt("user_id");
                        String title=object.getString("title");
                        String body=object.getString("body");
                        view.append("id:"  + " " + String.valueOf(id) + "\n"+ "user_id: "  + String.valueOf(user_id)
                                + "\n\n" + "title: " +
                               title  + "\n\n" + "body: " + " " + body + "\n\n");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.setText(error.getMessage());
            }
        });
        mreqest.add(request);
    }
}