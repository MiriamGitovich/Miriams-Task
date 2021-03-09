package com.example.miriamstask;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "shows")
public class GeoLocation {

    @ColumnInfo(name = "showId")
    public int showId;

    @ColumnInfo(name = "geo_restriction_latitude")
    public double geoRestrictionLatitude;

    @ColumnInfo(name = "geo_restriction_longitude")
    public double geoRestrictionLongitude;


    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public double getGeoRestrictionLatitude() {
        return geoRestrictionLatitude;
    }

    public void setGeoRestrictionLatitude(double geoRestrictionLatitude) {
        this.geoRestrictionLatitude = geoRestrictionLatitude;
    }

    public double getGeoRestrictionLongitude() {
        return geoRestrictionLongitude;
    }

    public void setGeoRestrictionLongitude(double geoRestrictionLongitude) {
        this.geoRestrictionLongitude = geoRestrictionLongitude;
    }
}
