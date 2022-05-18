package com.mvvm.example.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ResolvableApiException;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationAvailability;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

public class NewLocation {
    Activity context;

    public NewLocation(Activity context) {
        this.context = context;
    }

//    LocationRequest locationRequest;
//    int accuracy;
//
//    LocationCallBack locationCallBack;
//    public void getALocation(LocationCallBack locationCallBack,int updateInterval,int fastestInterval, int priority ,int accuracy) {
//        this.accuracy = accuracy;
//        this.locationCallBack = locationCallBack;
//
//        fusedLocationClient =  LocationServices.getFusedLocationProviderClient(context);
//        locationRequest = LocationRequest.create();
//        locationRequest.setInterval(updateInterval/*10000*/);
//        locationRequest.setFastestInterval(fastestInterval/*5000*/);
////        locationRequest.setSmallestDisplacement(20)
//        locationRequest.setPriority(priority/*LocationRequest.PRIORITY_HIGH_ACCURACY*/);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest);
//
//
//        SettingsClient client = LocationServices.getSettingsClient(context);
//        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
//
//        task.addOnSuccessListener( locationSettingsResponse -> {
//            startLocationUpdates();
//        });
//
//        task.addOnFailureListener( e -> {
//            if (e instanceof ResolvableApiException) {
//                // Location settings are not satisfied, but this can be fixed
//                // by showing the user a dialog.
//                try {
//                    // Show the dialog by calling startResolutionForResult(),
//                    // and check the result in onActivityResult().
//                    ResolvableApiException resolvable = (ResolvableApiException) e;
//                    if (context instanceof Activity ){
//                        resolvable.startResolutionForResult((Activity) context, 101);
//                    }
//                } catch (IntentSender.SendIntentException sendEx) {
//                    // Ignore the error.
//                    Log.i("test" ,"sendEx  "+sendEx);
//                }
//            }
//        });
//    }
//
//    LocationCallback locationCallback = new LocationCallback() {
//        @Override
//        public void onLocationResult(@NonNull LocationResult locationResult) {
//            super.onLocationResult(locationResult);
//
//            locationCallBack.newLocation(locationResult.getLastLocation());
//            stopLocationUpdates();
//
////            if (accuracy ==0 ){
////                locationCallBack.newLocation(locationResult.getLastLocation());
////                stopLocationUpdates();
////            }else{
////                if(locationResult.getLastLocation().getAccuracy() <=50){
////                    stopLocationUpdates();
////                }
////            }
//        }
//
//        @Override
//        public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
//            super.onLocationAvailability(locationAvailability);
//        }
//    };
//    FusedLocationProviderClient fusedLocationClient;
//
//    private void stopLocationUpdates() {
//        fusedLocationClient.removeLocationUpdates(locationCallback);
//    }
//
//    private void startLocationUpdates() {
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        fusedLocationClient.requestLocationUpdates(locationRequest,
//                locationCallback,
//                Looper.getMainLooper());
//    }
//
//    public interface LocationCallBack{
//        void newLocation(Location location);
//    }

}
