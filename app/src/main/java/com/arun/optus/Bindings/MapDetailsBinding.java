package com.arun.optus.Bindings;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.arun.optus.MapsActivity;
import com.arun.optus.R;

/**
 * Created by root on 28/1/17.
 */

public class MapDetailsBinding extends BaseObservable {
    private Context context;
    public MapDetailsBinding(Context context)
    {
        this.context = context;
    }
    @Bindable
    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
        notifyPropertyChanged(BR.trainTime);
    }
    @Bindable
    public String getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(String drivingTime) {
        this.drivingTime = drivingTime;
        notifyPropertyChanged(BR.drivingTime);
    }

    @Bindable
    public Boolean getEnableNavigation() {
        return enableNavigation;
    }

    public void setEnableNavigation(Boolean enableNavigation) {
        this.enableNavigation = enableNavigation;
        notifyPropertyChanged(BR.enableNavigation);
    }

    public String getDrivingPolyline() {
        return drivingPolyline;
    }

    public void setDrivingPolyline(String drivingPolyline) {
        this.drivingPolyline = drivingPolyline;
    }

    public String getTrainPolyline() {
        return trainPolyline;
    }

    public void setTrainPolyline(String trainPolyline) {
        this.trainPolyline = trainPolyline;
    }
    private Boolean enableNavigation=false;
    private String drivingTime="";
    private String trainTime="";


    private String drivingPolyline;
    private String trainPolyline;
    public void onClickHandler(View v) {
        Intent transitionIntent;
        switch (v.getId()){
            case R.id.btn_navigate:
                transitionIntent= new Intent(context,MapsActivity.class);
                transitionIntent.putExtra("drivingPolyline",getDrivingPolyline());
                transitionIntent.putExtra("trainPolyline",getTrainPolyline());
                context.startActivity(transitionIntent);
                break;

        }
    }

}
