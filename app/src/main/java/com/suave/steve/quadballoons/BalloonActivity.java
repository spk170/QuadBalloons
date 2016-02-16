package com.suave.steve.quadballoons;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BalloonActivity extends AppCompatActivity  implements OnMapReadyCallback {


    private GoogleMap aMap;
    public static final double f2m = 0.3048;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_balloon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        if(aMap == null){
            aMap = googleMap;

        }
        LatLng lakeLagCenter = new LatLng(37.4225, -122.17574);
        double[] balloonLat = {37.4231, 37.42282, 37.42254, 37.42227, 37.42236, 37.42266, 37.42316,
                                37.42226, 37.42289, 37.4221};
        double[] balloonLng = {-122.17608, -122.17572, -122.1751, -122.17627, -122.17555, -122.17618,
                                -122.17533, -122.1759, -122.1764, -122.17543};
        double[] balloonAlt = {200, 150, 35, 120, 100, 50, 100, 20, 100, 75};
        double[] balloonRad = {9, 9, 15, 3, 9, 3, 15, 3, 9, 9};
        int nBalloons = 10;
        CircleOptions circleOptions = new CircleOptions()
                .center(lakeLagCenter)
                .radius(300*f2m); // In meters

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(lakeLagCenter));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.addCircle(circleOptions);

        for(int i = 0;i < nBalloons; i++){
            LatLng balloonLatLng = new LatLng(balloonLat[i],balloonLng[i]);
            circleOptions = new CircleOptions()
                    .center(balloonLatLng)
                    .radius(f2m*balloonRad[i])
                    .fillColor(Color.RED).strokeWidth(2);
            googleMap.addCircle(circleOptions);
            googleMap.addMarker(new MarkerOptions()
                    .position(balloonLatLng)
                    .title("Balloon #"
                            +new Integer(i+1).toString())
                    .snippet("Alt: "
                            +new Double(balloonAlt[i]).toString()
                            +" ft"));

        }
    }
}
