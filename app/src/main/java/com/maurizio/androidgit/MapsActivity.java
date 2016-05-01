package com.maurizio.androidgit;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Toast.makeText(MapsActivity.this, "onMapReady", Toast.LENGTH_LONG).show();

        mMap.setMyLocationEnabled(true);

        //----------------------------------Zooming camera to position user-----------------

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        final Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {

            //Toast.makeText(MapsActivity.this, "Started!! Your location: " + location.getLatitude() + " - " + location.getLongitude(), Toast.LENGTH_LONG).show();

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(14)                   // Sets the zoom
                    //.bearing(0)                // Sets the orientation of the camera to east
                    //.tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2500, new GoogleMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    //Toast.makeText(MapsActivity.this, "Finished!!", Toast.LENGTH_LONG).show();


                    BitmapDescriptor icon1 = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
                    BitmapDescriptor icon2 = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    BitmapDescriptor icon3 = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                    BitmapDescriptor icon4 = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET);

                    LatLng point1 = new LatLng(location.getLatitude() + 0.003, location.getLongitude() + 0.002);
                    Marker marker1 = mMap.addMarker(new MarkerOptions().icon(icon1).position(point1).title("Marcos").snippet("33 years old"));

                    LatLng point2 = new LatLng(location.getLatitude() + 0.001, location.getLongitude() - 0.002);
                    Marker marker2 = mMap.addMarker(new MarkerOptions().icon(icon2).position(point2).title("Vladimir").snippet("30 years old"));

                    LatLng point3 = new LatLng(location.getLatitude(), location.getLongitude() + 0.004);
                    Marker marker3 = mMap.addMarker(new MarkerOptions().icon(icon3).position(point3).title("Andrew").snippet("25 years old"));

                    LatLng point4 = new LatLng(location.getLatitude(), location.getLongitude() - 0.005);
                    Marker marker4 = mMap.addMarker(new MarkerOptions().icon(icon4).position(point4).title("Cynthia").snippet("28 years old"));

                }

                @Override
                public void onCancel() {
                    //Toast.makeText(MapsActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                }
            });

        }

        //----------------------------------Zooming camera to position user-----------------
    }
}