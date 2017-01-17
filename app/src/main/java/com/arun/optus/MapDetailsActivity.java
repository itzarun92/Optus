package com.arun.optus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arun.optus.DistanceMatrix.ApiClient;
import com.arun.optus.DistanceMatrix.DistanceInterface;
import com.arun.optus.DistanceMatrix.DistanceMatrix;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 16/1/17.
 */

public class MapDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_navigate;
    private TextView drivingTime,trainTime,tv_drivingTime,tv_trainTime;
    private Place fromPlace=null,toPlace=null;
    private String TAG="Map Details",drivingPolyline=null,trainPolyline=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_details);
        tv_drivingTime=(TextView)findViewById(R.id.tv_drivingTime);
        tv_trainTime=(TextView)findViewById(R.id.tv_trainTime);
        drivingTime=(TextView)findViewById(R.id.drivingTime);
        trainTime=(TextView)findViewById(R.id.trainTime);
        tv_trainTime.setVisibility(View.GONE);
        tv_drivingTime.setVisibility(View.GONE);
        btn_navigate=(Button)findViewById(R.id.btn_navigate);
        btn_navigate.setEnabled(false);
        btn_navigate.setOnClickListener(this);
        PlaceAutocompleteFragment fromLocation = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.edt_fromLocation);

        fromLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                fromPlace=place;
                // TODO: Get info about the selected place.
                Log.i(TAG, "fromLocation: " + place.getName());
                if(fromPlace !=null && toPlace!=null){
                    refreshTravelTimings();
                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        PlaceAutocompleteFragment toLocation = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.edt_toLocation);

        toLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                toPlace=place;
                // TODO: Get info about the selected place.
                Log.i(TAG, "toLocation: " + place.getId());
                if(fromPlace !=null && toPlace!=null){
                    refreshTravelTimings();
                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }

        });
    }

    public void refreshTravelTimings(){
        tv_trainTime.setVisibility(View.VISIBLE);
        tv_drivingTime.setVisibility(View.VISIBLE);
        btn_navigate.setEnabled(true);
        //Creating an object of our api interface
        DistanceInterface api = ApiClient.getClient().create(DistanceInterface.class);


         api.getDrivingTimings("place_id:"+fromPlace.getId(),"place_id:"+toPlace.getId()).enqueue(new Callback<DistanceMatrix>() {
            @Override
            public void onResponse(Call<DistanceMatrix> call, Response<DistanceMatrix> response) {
                DistanceMatrix distanceMatrix = response.body();
                if(distanceMatrix.getStatus().equalsIgnoreCase("OK") && distanceMatrix.getRoutes().size()>0 && distanceMatrix.getRoutes().get(0).getLegs().size()>0) {
                drivingTime.setText( distanceMatrix.getRoutes().get(0).getLegs().get(0).getDuration().getText()+" ("+ distanceMatrix.getRoutes().get(0).getLegs().get(0).getDistance().getText()+")");
                    drivingPolyline=distanceMatrix.getRoutes().get(0).getOverviewPolyline().getPoints();
                }else{
                    drivingTime.setText("Route not available");
                    drivingPolyline=null;
                }
            }

            @Override
            public void onFailure(Call<DistanceMatrix>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
        api.getTrainTimings("place_id:"+fromPlace.getId(),"place_id:"+toPlace.getId()).enqueue(new Callback<DistanceMatrix>() {
            @Override
            public void onResponse(Call<DistanceMatrix> call, Response<DistanceMatrix> response) {
                DistanceMatrix distanceMatrix = response.body();
                if(distanceMatrix.getStatus().equalsIgnoreCase("OK") && distanceMatrix.getRoutes().size()>0 && distanceMatrix.getRoutes().get(0).getLegs().size()>0) {
                    trainTime.setText( distanceMatrix.getRoutes().get(0).getLegs().get(0).getDuration().getText()+" ("+ distanceMatrix.getRoutes().get(0).getLegs().get(0).getDistance().getText()+")");
                    trainPolyline=distanceMatrix.getRoutes().get(0).getOverviewPolyline().getPoints();
                }else{
                    trainTime.setText("Route not available");
                    trainPolyline=null;
                }
            }

            @Override
            public void onFailure(Call<DistanceMatrix>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
    @Override
    public void onClick(View v) {
        Intent transitionIntent;
        switch (v.getId()){
            case R.id.btn_navigate:
                transitionIntent= new Intent(this,MapsActivity.class);
                transitionIntent.putExtra("drivingPolyline",drivingPolyline);
                transitionIntent.putExtra("trainPolyline",trainPolyline);
                startActivity(transitionIntent);
                break;

        }
    }
}
