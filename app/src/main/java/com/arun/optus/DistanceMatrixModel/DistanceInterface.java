package com.arun.optus.DistanceMatrixModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 17/1/17.
 */

public interface DistanceInterface {
    @GET("maps/api/directions/json?mode=driving&key=AIzaSyAf2aARrkVK8VcQB0RbAJAb1CohwIBKUoA")
    Call<DistanceMatrix> getDrivingTimings(@Query("origin") String source, @Query("destination") String destination);
    @GET("maps/api/directions/json?mode=transit&transit_mode=train&key=AIzaSyAf2aARrkVK8VcQB0RbAJAb1CohwIBKUoA")
    Call<DistanceMatrix> getTrainTimings(@Query("origin") String source, @Query("destination") String destination);
}
