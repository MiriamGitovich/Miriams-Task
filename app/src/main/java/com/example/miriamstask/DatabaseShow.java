package com.example.miriamstask;

import android.content.Context;

import androidx.room.Room;

public class DatabaseShow {
    private Context mCtx;
    private static DatabaseShow mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseShow(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyTask").build();
    }

    public static synchronized DatabaseShow getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseShow(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
