package com.arun.optus;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arun.optus.Bindings.MapDetailsBinding;
import com.arun.optus.DistanceMatrixModel.ApiClient;
import com.arun.optus.DistanceMatrixModel.DistanceInterface;
import com.arun.optus.DistanceMatrixModel.DistanceMatrix;
import com.arun.optus.databinding.ActivityMapDetailsBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapDetailsActivity extends AppCompatActivity{
    private Place fromPlace=null,toPlace=null;
    public String TAG="Map Details";
    private MapDetailsBinding mapDetailsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMapDetailsBinding binding = DataBindingUtil.setContentView(this,  R.layout.activity_map_details);
        mapDetailsModel = new MapDetailsBinding(MapDetailsActivity.this);
        binding.setPresenter(mapDetailsModel);

        PlaceAutocompleteFragment fromLocation = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.edt_fromLocation);

        fromLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                fromPlace=place;
                if(fromPlace !=null && toPlace!=null){
                    refreshTravelTimings();
                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, getResources().getString(R.string.error_msg) + status);
            }
        });
        PlaceAutocompleteFragment toLocation = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.edt_toLocation);

        toLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                toPlace=place;
                if(fromPlace !=null && toPlace!=null){
                    refreshTravelTimings();
                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, getResources().getString(R.string.error_msg) + status);
            }

        });
    }

    public void refreshTravelTimings(){
        mapDetailsModel.setEnableNavigation(true);
        //Creating an object of our api interface
        DistanceInterface api = ApiClient.getClient().create(DistanceInterface.class);


         api.getDrivingTimings(getResources().getString(R.string.place_id_const)+fromPlace.getId(),getResources().getString(R.string.place_id_const)+toPlace.getId()).enqueue(new Callback<DistanceMatrix>() {
            @Override
            public void onResponse(Call<DistanceMatrix> call, Response<DistanceMatrix> response) {
                DistanceMatrix distanceMatrix = response.body();
                if(distanceMatrix.getStatus().equalsIgnoreCase(getResources().getString(R.string.ok_const)) && distanceMatrix.getRoutes().size()>0 && distanceMatrix.getRoutes().get(0).getLegs().size()>0) {
                    mapDetailsModel.setDrivingTime( distanceMatrix.getRoutes().get(0).getLegs().get(0).getDuration().getText()+" ("+ distanceMatrix.getRoutes().get(0).getLegs().get(0).getDistance().getText()+")");
                    mapDetailsModel.setDrivingPolyline(distanceMatrix.getRoutes().get(0).getOverviewPolyline().getPoints());
                }else{
                    mapDetailsModel.setDrivingTime(getResources().getString(R.string.route_not_available_msg));
                    mapDetailsModel.setDrivingPolyline(null);
                }
            }

            @Override
            public void onFailure(Call<DistanceMatrix>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
        api.getTrainTimings(getResources().getString(R.string.place_id_const)+fromPlace.getId(),getResources().getString(R.string.place_id_const)+toPlace.getId()).enqueue(new Callback<DistanceMatrix>() {
            @Override
            public void onResponse(Call<DistanceMatrix> call, Response<DistanceMatrix> response) {
                DistanceMatrix distanceMatrix = response.body();
                if(distanceMatrix.getStatus().equalsIgnoreCase(getResources().getString(R.string.ok_const)) && distanceMatrix.getRoutes().size()>0 && distanceMatrix.getRoutes().get(0).getLegs().size()>0) {
                    mapDetailsModel.setTrainTime( distanceMatrix.getRoutes().get(0).getLegs().get(0).getDuration().getText()+" ("+ distanceMatrix.getRoutes().get(0).getLegs().get(0).getDistance().getText()+")");
                    mapDetailsModel.setTrainPolyline(distanceMatrix.getRoutes().get(0).getOverviewPolyline().getPoints());
                }else{
                    mapDetailsModel.setTrainTime(getResources().getString(R.string.route_not_available_msg));
                    mapDetailsModel.setTrainPolyline(null);
                }
            }

            @Override
            public void onFailure(Call<DistanceMatrix>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

}
