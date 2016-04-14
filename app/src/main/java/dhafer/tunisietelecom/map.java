package dhafer.tunisietelecom;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map extends Activity {
    private GoogleMap googleMap;
    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void initilizeMap() {
        String longi,lati;

            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                lati= null;
                longi= null;
            } else {
                longi= extras.getString("longitude");
                lati= extras.getString("latitude");
            }

        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(lati), Double.parseDouble(longi))).title("bar "+longi);

// adding marker
            googleMap.addMarker(marker);
            gps = new GPSTracker(map.this);
            if (gps.canGetLocation()) {
                double latit = gps.getLatitude();
                double longti = gps.getLongitude();
                MarkerOptions marker2 = new MarkerOptions().position(new LatLng(latit, longti)).title("Ma position actuelle  ");
                googleMap.addMarker(marker2);
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }}

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

}
