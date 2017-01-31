package com.arun.optus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<LatLng> drivingPoints=null,trainPoints=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if(getIntent().getExtras().getString("drivingPolyline")!=null){
             drivingPoints=PolyUtil.decode(getIntent().getExtras().getString("drivingPolyline"));
        }

        if(getIntent().getExtras().getString("trainPolyline")!=null){
             trainPoints=PolyUtil.decode(getIntent().getExtras().getString("trainPolyline"));
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Polyline drivingPolyline = mMap.addPolyline(new PolylineOptions().color(Color.BLUE));
        Polyline trainPolyline = mMap.addPolyline(new PolylineOptions().color(Color.RED));
        if(drivingPoints!=null){
            drivingPolyline.setPoints(drivingPoints);
        }
        if(trainPoints!=null){
            trainPolyline.setPoints(trainPoints);
        }
        boolean hasPoints = false;
        Double maxLat = null, minLat = null, minLon = null, maxLon = null;
        List<LatLng> bothPolylinePoints;
        if(drivingPoints!=null){
           bothPolylinePoints = new ArrayList<LatLng>(drivingPoints);
            if(trainPoints!=null){
                bothPolylinePoints.addAll(trainPoints);
            }

        }else if(trainPoints!=null){
             bothPolylinePoints = new ArrayList<LatLng>(trainPoints);
        }else{
            bothPolylinePoints=new ArrayList<LatLng>();
        }

        for (LatLng coordinate : bothPolylinePoints) {
            // Find out the maximum and minimum latitudes & longitudes
            // Latitude
            maxLat = maxLat != null ? Math.max(coordinate.latitude, maxLat) : coordinate.latitude;
            minLat = minLat != null ? Math.min(coordinate.latitude, minLat) : coordinate.latitude;

            // Longitude
            maxLon = maxLon != null ? Math.max(coordinate.longitude, maxLon) : coordinate.longitude;
            minLon = minLon != null ? Math.min(coordinate.longitude, minLon) : coordinate.longitude;

            hasPoints = true;
        }
        if (hasPoints) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(new LatLng(maxLat, maxLon));
            builder.include(new LatLng(minLat, minLon));
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), getResources().getInteger(R.integer.map_camera_zoom)));
        }

    }
}
