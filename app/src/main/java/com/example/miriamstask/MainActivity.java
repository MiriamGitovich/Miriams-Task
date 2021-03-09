package com.example.miriamstask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User();
        textView = (TextView) findViewById(R.id.text_view);

        GeoLocationApi geoLocationApi= new GeoLocationApi(this);
         geoLocationApi.getMostPopularShowName();
        //Location location = new Location();
        //location.setLatitude(31.80144241242889);
        //location.setLongitude( 35.209357795206095);
        //String g = geoLocationApi.purchaseShow(1,"The little boy", location);
      //  textView.setText(g);
    }


}


