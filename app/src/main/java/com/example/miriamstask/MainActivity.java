package com.example.miriamstask;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import android.widget.TextView;



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

        GeoLocationApi geoLocationApi = new GeoLocationApi(this);
        Show show1 = new Show();
        show1.setShowId(1);
        show1.setShowName("The little boy");
        Show show2 = new Show();
        show2.setShowId(2);
        show2.setShowName("The big boy");


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

        //1
        geoLocationApi.purchaseShow(user1.getUid(), "The little boy",  geolocation );
        geoLocationApi.purchaseShow(user1.getUid(), "The big boy",geolocation);
        geoLocationApi.purchaseShow(user2.getUid(), "The little boy",geolocation);
        geoLocationApi.purchaseShow(user2.getUid(), "The big boy",geolocationN);

        GeoLocation geoLocation1 = new GeoLocation();
        geoLocation1.setShowGeoId(111);
        geoLocation1.setShowId(show1.getShowId());
        geoLocation1.setGeoRestrictionLatitude(31.801843451822567);
        geoLocation1.setGeoRestrictionLongitude(35.21209708170776);
        GeoLocation geoLocation2 = new GeoLocation();
        geoLocation2.setShowGeoId(222);

        geoLocation2.setShowId(show2.getShowId());
        geoLocation2.setGeoRestrictionLatitude(31.801843451822567);
        geoLocation2.setGeoRestrictionLongitude(35.21209708170776);

        GeoLocation geoLocation3 = new GeoLocation();
        geoLocation3.setShowGeoId(333);

        geoLocation3.setShowId(show1.getShowId());
        geoLocation3.setGeoRestrictionLatitude(43.976985299884575);
        geoLocation3.setGeoRestrictionLongitude(11.302294542984338);

        //2
        geoLocationApi.showRestriction(show1.getShowId(), geoLocation1, false);
        geoLocationApi.showRestriction(show2.getShowId(), geoLocation2, false);
        geoLocationApi.showRestriction(show1.getShowId(), geoLocation3, true);


        //3
        geoLocationApi.viewShow(user1.getUid(), "The little boy",  geolocation );
        geoLocationApi.viewShow(user2.getUid(), "The little boy",  geolocation );
        geoLocationApi.viewShow(user2.getUid(), "The big boy",  geolocation );
        geoLocationApi.viewShow(user2.getUid(), "The little boy",  geolocationN );

        //4
        geoLocationApi.getMostPopularShowName();
        geoLocationApi.getMostPopularShowCount();



    }


}


