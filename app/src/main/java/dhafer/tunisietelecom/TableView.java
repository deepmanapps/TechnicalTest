package dhafer.tunisietelecom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class TableView extends Activity {
    TableLayout t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        TableLayout tl = (TableLayout) findViewById(R.id.tablayout);
        String data = loadJSONFromAsset();
        String contenu="";
        try {
            JSONObject  jsonRootObject = new JSONObject(data);
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("emplacement");
            Integer count = 0;
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nom = jsonObject.optString("nombar");
                final String longitude = jsonObject.optString("long").toString();
                final String latitude = jsonObject.optString("lat").toString();
                String photo = jsonObject.optString("photo").toString();
//*******************************
                TableRow tr = new TableRow(TableView.this);

                if (count % 2 != 0) tr.setBackgroundColor(Color.GRAY);
                tr.setId(100 + count);
                tr.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                ImageView photobar =new ImageView(TableView.this);
                photobar.setId(150+count);
                photobar.setImageResource(R.drawable.icbar1);
                tr.addView(photobar);
                //--------------------------
                TextView nombar = new TextView(TableView.this);
                nombar.setId(210 + count);
                nombar.setText(nom);
                nombar.setPadding(2, 0, 5, 0);
                nombar.setTextColor(Color.BLUE);
                nombar.setTextSize(25);
                tr.addView(nombar);
                tr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
Intent ii=new Intent(getApplicationContext(),map.class);
                        ii.putExtra("longitude",longitude);
                        ii.putExtra("latitude",latitude);
                        startActivity(ii);
                    }
                });
                tl.addView(tr, 0, new TableLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                count++;
                contenu += "nombar"+nom+" : \n longitude= "+ longitude +" \n Lattitude= "+ latitude +" \n repPhoto= "+ photo +" \n ";
            }

            Toast.makeText(getApplicationContext(),contenu,Toast.LENGTH_LONG).show();
        } catch (JSONException e) {e.printStackTrace();}
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
