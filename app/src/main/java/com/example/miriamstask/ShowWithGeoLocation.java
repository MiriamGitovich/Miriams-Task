package com.example.miriamstask;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ShowWithGeoLocation {

        @Embedded
        public Show show;
        @Relation(parentColumn = "id", entityColumn = "showId", entity = GeoLocation.class)
        public List<GeoLocation> geoLocationsList;
}
