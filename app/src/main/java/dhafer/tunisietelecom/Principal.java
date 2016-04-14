package dhafer.tunisietelecom;

import android.app.TabActivity;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TabHost;

public class Principal extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("bar Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Carte Tab");

        tab1.setIndicator("Table bar");
        tab1.setContent(new Intent(this, TableView.class));

      tab2.setIndicator("Carte Map");
     tab2.setContent(new Intent(this, Carte.class));


        tabHost.addTab(tab1);
      tabHost.addTab(tab2);


    }
}
