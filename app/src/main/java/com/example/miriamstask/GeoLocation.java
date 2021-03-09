package com.example.miriamstask;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "geolocations")
public class GeoLocation {

    @PrimaryKey
    public long showGeoId;

    @ColumnInfo
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
    public long getShowGeoId() {
        return showGeoId;
    }

    public void setShowGeoId(long showGeoId) {
        this.showGeoId = showGeoId;
    }
}
