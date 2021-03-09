package com.example.miriamstask;

import androidx.room.Entity;

@Entity(primaryKeys = {"uid", "showId"})
public class UserShowCrossRef {


    public long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getShowId() {
        return showId;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }

    public long showId;
}
