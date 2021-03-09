package com.example.miriamstask;

import androidx.room.Entity;

@Entity(primaryKeys = {"uid", "showId"})
public class UserShowCrossRef {


    public long uid;
    public long showId;
}
