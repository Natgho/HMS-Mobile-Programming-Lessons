package com.sezerbozkir.yalova_uni_lab_project;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final String TAG = "HatMesajlar";
    private HuaweiMap hMap;
    private MapView mMapView;
    JSONArray jsonArray;
    private Gson gson;
    private List<Stadium> stadiums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=stadiums_nfl&q=&facet=subsector&facet=primary_ty&facet=name1&facet=city&facet=state&facet=conference&facet=division&facet=roof_type";
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        stadiums = new ArrayList<Stadium>();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            Log.d(TAG, "onResponse: ");
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("records");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                // jo.getJSONObject("fields").getString("longitude")
                                Stadium tmpStad = gson.fromJson(jo.getJSONObject("fields").toString(), Stadium.class);
                                stadiums.add(tmpStad);
                                hMap.addMarker(new MarkerOptions().position(new LatLng(tmpStad.latitude, tmpStad.longitude)).title(tmpStad.name).clusterable(true));
                                Log.d(TAG, jo.toString());
                                Toast.makeText(MainActivity.this, "Doldu!", Toast.LENGTH_SHORT).show();
                                //hMap.addMarker(new MarkerOptions().position(new LatLng(48.891478, 2.334595)).title("Marker1").clusterable(true));
                            }
                            int lastStadium = stadiums.size()-1;
                            hMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(stadiums.get(lastStadium).latitude,
                                            stadiums.get(lastStadium).longitude),3));
                        } catch (JSONException e) {
                            Log.d(TAG, "onResponse: Hata");
                            e.printStackTrace();
                        }

                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Log.d(TAG, "onErrorResponse: Hata volleyden gelme");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

        mMapView = findViewById(R.id.mapView);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        MapsInitializer.setApiKey("CgB6e3x9tLhypzrfZKtdECrOKLskHk0XxtcUKUmp34NmJ1doBLQl9FtPokjBamyurC0g2L9rNSqNNmSDDWSmRx5p");
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(HuaweiMap map) {
        hMap = map;
    }
}