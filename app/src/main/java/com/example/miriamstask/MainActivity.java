package com.example.miriamstask;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user1 = new User();
        user1.setUid(1);
        user1.setFirstName("Miriam");
        user1.setLastName("Al");
        User user2 = new User();
        user2.setUid(2);
        user2.setFirstName("David");
        user2.setLastName("Am");
        textView = (TextView) findViewById(R.id.text_view);

        GeoLocationApi geoLocationApi= new GeoLocationApi(this);
        Show show1 = new Show();
        show1.setShowId(1);
        show1.setShowName("The little boy");
        Show show2 = new Show();
        show2.setShowId(2);
        show2.setShowName("The big boy");

        UserShowCrossRef showsForUser1 =new UserShowCrossRef();
        showsForUser1.setUid(user1.getUid());
        showsForUser1.setShowId(show1.getShowId());

        UserShowCrossRef showsForUser2 =new UserShowCrossRef();
        showsForUser2.setUid(user1.getUid());
        showsForUser2.setShowId(show2.getShowId());

        UserShowCrossRef showsForUser3 =new UserShowCrossRef();
        showsForUser3.setUid(user2.getUid());
        showsForUser3.setShowId(show1.getShowId());


        geoLocationApi.insertShow(show1);
        geoLocationApi.insertShow(show2);
        geoLocationApi.insertUser(user1);
        geoLocationApi.insertUser(user2);
        Location geolocation = new Location("");
        geolocation.setLatitude(31.801843451822567);//Jerusalem
        geolocation.setLongitude( 35.21209708170776);
        Location geolocationN = new Location("");
        geolocationN.setLatitude(43.976985299884575);//Italy
        geolocationN.setLongitude(11.302294542984338);
        geoLocationApi.purchaseShow(user1.getUid(), "The little boy",  geolocation );
        geoLocationApi.purchaseShow(user1.getUid(), "The big boy",geolocation);
        geoLocationApi.purchaseShow(user2.getUid(), "The little boy",geolocation);
        geoLocationApi.purchaseShow(user2.getUid(), "The big boy",geolocationN);

        geoLocationApi.getMostPopularShowName();
        geoLocationApi.getMostPopularShowCount();

        //Location location = new Location();
        //location.setLatitude(31.80144241242889);
        //location.setLongitude( 35.209357795206095);
        //String g = geoLocationApi.purchaseShow(1,"The little boy", location);
      //  textView.setText(g);
    }


}


