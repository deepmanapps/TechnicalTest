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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Carte extends Activity {
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            String data = loadJSONFromAsset();
            String contenu="";
            try {
                JSONObject jsonRootObject = new JSONObject(data);
                //Get the instance of JSONArray that contains JSONObjects
                JSONArray jsonArray = jsonRootObject.optJSONArray("emplacement");
                Integer count = 0;
                for(int i=0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String nom = jsonObject.optString("nombar");
                    final String longitude = jsonObject.optString("long").toString();
                    final String latitude = jsonObject.optString("lat").toString();
                    String photo = jsonObject.optString("photo").toString();
                    MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))).title(nom);
                    googleMap.addMarker(marker);
                }

            Toast.makeText(getApplicationContext(),contenu,Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("bar.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
