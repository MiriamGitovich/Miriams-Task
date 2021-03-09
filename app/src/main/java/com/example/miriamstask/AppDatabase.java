package com.example.miriamstask;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Show.class, UserShowCrossRef.class, GeoLocation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShowDao showDao();
}