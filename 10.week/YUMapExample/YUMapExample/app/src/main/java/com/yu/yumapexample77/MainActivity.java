package com.yu.yumapexample77;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapViewDemoActivity";
    //HUAWEI map
    private HuaweiMap hMap;
    private MapView mMapView;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get mapview instance
        mMapView = findViewById(R.id.mapView);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        // please replace "Your API key" with api_key field value in
        // agconnect-services.json
        MapsInitializer.setApiKey("CgB6e3x9z1DQNO9PSRjcTTSNpNw3IhIoth3vgtdqDrU+FsCxewDHESpYutdRxKAby3fWNTufBSzGFrLb/Pwb6YnA");
        mMapView.onCreate(mapViewBundle);
        //get map instance
        mMapView.getMapAsync(this);

        applyPermission();
    }

    public void applyPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "sdk >= 23 M");
            // Check whether your app has the specified permission and whether the app operation corresponding to the permission is allowed.
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                // Request permissions for your app.
                String[] strings =
                        {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                // Request permissions.
                ActivityCompat.requestPermissions(this, strings, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION successful");

                // Enable the my-location layer.
                hMap.setMyLocationEnabled(true);
                // Enable the my-location icon.
                hMap.getUiSettings().setMyLocationButtonEnabled(true);

            } else {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSSION  failed");

            }
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addMarker(double lat, double lng, String title, String snip) {

        MarkerOptions options = new MarkerOptions()
                //.position(new LatLng(41.0055005, 28.7319876))
                .position(new LatLng(lat, lng))
                .title(title)
                .snippet(snip)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mymarker))
                .clusterable(true);

        hMap.addMarker(options);
    }

    public void addMarker(LatLng lng, String title, String snip) {
        addMarker(lng.latitude, lng.longitude, title, snip);
    }

    @Override
    public void onMapReady(HuaweiMap huaweiMap)
    {
        //get map instance in a callback method
        Log.d(TAG, "onMapReady: ");
        hMap = huaweiMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "sdk >= 23 M");
            // Check whether your app has the specified permission and whether the app operation corresponding to the permission is allowed.
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {

            }
            else
            {
                // Enable the my-location layer.
                hMap.setMyLocationEnabled(true);
                // Enable the my-location icon.
                hMap.getUiSettings().setMyLocationButtonEnabled(true);
            }
        }
        double lat = 41.0055005;
        double lng = 28.7319876;

        for(int i=0; i<10; i++)
        {
            addMarker(lat + 0.05 * i, lng + 0.05 * i, "Title " + i, "Snippet " + i);
        }

        hMap.setMarkersClustering(true);

        hMap.setOnMarkerClickListener(new HuaweiMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "onMarkerClick:" + marker.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        hMap.setOnMapClickListener(new HuaweiMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addMarker(latLng, "Selam", "Snip");
            }
        });

    }
}