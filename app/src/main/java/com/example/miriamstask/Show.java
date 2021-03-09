package com.example.miriamstask;

import android.location.Location;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shows")
public class Show implements Serializable{

    @PrimaryKey
    public long showId;

    @ColumnInfo(name = "show_name")
    public String showName;


    public void setShowId(int showId) {
        this.showId = showId;
    }

    public long getShowId() {
        return showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }




}
